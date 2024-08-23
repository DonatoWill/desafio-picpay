package com.picpay.challenge.wallet.domain.usecase

import com.picpay.challenge.wallet.app.dto.request.UpdateBalanceRequest
import com.picpay.challenge.wallet.infrastructure.db.model.Transaction
import com.picpay.challenge.wallet.infrastructure.db.model.TransactionStatus
import com.picpay.challenge.wallet.infrastructure.db.model.TransactionType
import com.picpay.challenge.wallet.infrastructure.db.model.Wallet
import com.picpay.challenge.wallet.infrastructure.db.repository.TransactionRepository
import com.picpay.challenge.wallet.infrastructure.db.repository.WalletRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UpdateBalanceUseCase(
    private val walletRepository: WalletRepository,
    private val transactionRepository: TransactionRepository
) {

    @Transactional
    fun deposit(walletId: Long, updateBalanceRequest: UpdateBalanceRequest) {
        val wallet = walletRepository.findById(walletId).get()
        val newBalance = wallet.balance.plus(updateBalanceRequest.amount)
        walletRepository.save(Wallet(wallet.id, wallet.accountNumber, newBalance))

        val transactionModel = Transaction(
           walletId = walletId,
           amount = updateBalanceRequest.amount,
           type = TransactionType.DEPOSIT.name,
           status = TransactionStatus.COMPLETED.name
        )
        transactionRepository.save(transactionModel)
    }

    @Transactional
    fun withdraw(walletId: Long, updateBalanceRequest: UpdateBalanceRequest) {
        val wallet = walletRepository.findById(walletId).get()
        val newBalance = wallet.balance.minus(updateBalanceRequest.amount)
        walletRepository.save(Wallet(wallet.id, wallet.accountNumber, newBalance))
        val transactionModel = Transaction(
            walletId = walletId,
            amount = updateBalanceRequest.amount,
            type = TransactionType.WITHDRAW.name,
            status = TransactionStatus.COMPLETED.name
        )
        transactionRepository.save(transactionModel)
    }
}