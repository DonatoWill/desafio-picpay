package com.picpay.challenge.wallet.domain.usecase

import com.picpay.challenge.wallet.app.dto.request.TransactionRequest
import com.picpay.challenge.wallet.app.dto.response.TransactionResponse
import com.picpay.challenge.wallet.infrastructure.db.model.Transaction
import com.picpay.challenge.wallet.infrastructure.db.model.Wallet
import com.picpay.challenge.wallet.infrastructure.db.repository.TransactionRepository
import com.picpay.challenge.wallet.infrastructure.db.repository.WalletRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class CreateTransactionUseCase(
    val walletRepository: WalletRepository,
    val transactionRepository: TransactionRepository
) {

    @Transactional
    fun create(transactionRequest: TransactionRequest): TransactionResponse {

        val transactionModel = Transaction(
            null,
            transactionRequest.payeeWallet,
            transactionRequest.payerWallet,
            transactionRequest.amount,
            LocalDateTime.now()
        )

        val newTransaction: Transaction = transactionRepository.save(transactionModel)

        val walletPayer: Wallet = walletRepository.findById(transactionRequest.payerWallet).get()
        val walletPayee: Wallet = walletRepository.findById(transactionRequest.payeeWallet).get()

        walletRepository.save(walletPayer.debit(transactionRequest.amount))
        walletRepository.save(walletPayee.credit(transactionRequest.amount))

        return TransactionResponse(newTransaction.id!!,
            newTransaction.payerWallet,
            newTransaction.payeeWallet,
            newTransaction.amount)
    }


}