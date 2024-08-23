package com.picpay.challenge.wallet.domain.usecase

import com.picpay.challenge.wallet.app.dto.request.TransactionRequest
import com.picpay.challenge.wallet.app.dto.response.TransactionResponse
import com.picpay.challenge.wallet.app.dto.response.TransferResponse
import com.picpay.challenge.wallet.infrastructure.db.model.*
import com.picpay.challenge.wallet.infrastructure.db.repository.TransactionRepository
import com.picpay.challenge.wallet.infrastructure.db.repository.TransferRepository
import com.picpay.challenge.wallet.infrastructure.db.repository.WalletRepository
import com.picpay.challenge.wallet.commons.exception.WalletNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CreateTransactionUseCase(
    private val walletRepository: WalletRepository,
    private val transactionRepository: TransactionRepository,
    private val transferRepository: TransferRepository
) {

    @Transactional
    fun create(transactionRequest: TransactionRequest): TransferResponse {

        val transactionOut = Transaction(
            walletId = transactionRequest.payerWallet,
            amount = transactionRequest.amount,
            type = TransactionType.TRANSFER_OUT.name,
            status = TransactionStatus.COMPLETED.name
        )

        val transactionIn = Transaction(
            walletId = transactionRequest.payeeWallet,
            amount = transactionRequest.amount,
            type = TransactionType.TRANSFER_IN.name,
            status = TransactionStatus.COMPLETED.name
        )

        val walletPayer: Wallet = walletRepository.findById(transactionRequest.payerWallet)
            .orElseThrow{ throw WalletNotFoundException("Payer wallet not found.") }
        val walletPayee: Wallet = walletRepository.findById(transactionRequest.payeeWallet)
            .orElseThrow{ throw WalletNotFoundException("Payee wallet not found.") }

        val newTransaction: Transaction = transactionRepository.save(transactionOut)
        transactionRepository.save(transactionIn)
        walletRepository.save(walletPayer.debit(transactionRequest.amount))
        walletRepository.save(walletPayee.credit(transactionRequest.amount))

        transferRepository.save(Transfer(
            payerWallet = walletPayer.id,
            payeeWallet = walletPayee.id,
            transaction = newTransaction
        ))


        return TransferResponse(
            newTransaction.id,
            walletPayer.id,
            walletPayee.id,
            newTransaction.amount
        )
    }


}