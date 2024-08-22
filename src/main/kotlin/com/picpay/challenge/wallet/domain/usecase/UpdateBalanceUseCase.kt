package com.picpay.challenge.wallet.domain.usecase

import com.picpay.challenge.wallet.app.dto.request.UpdateBalanceRequest
import com.picpay.challenge.wallet.infrastructure.db.model.Wallet
import com.picpay.challenge.wallet.infrastructure.db.repository.WalletRepository
import org.springframework.stereotype.Service

@Service
class UpdateBalanceUseCase(
    val walletRepository: WalletRepository
) {

    fun deposit(walletId: Long, updateBalanceRequest: UpdateBalanceRequest) {
        val wallet = walletRepository.findById(walletId).get()
        val newBalance = wallet.balance.plus(updateBalanceRequest.amount)
        walletRepository.save(Wallet(wallet.id, wallet.accountNumber, newBalance))
    }

    fun withdraw(walletId: Long, updateBalanceRequest: UpdateBalanceRequest) {
        val wallet = walletRepository.findById(walletId).get()
        val newBalance = wallet.balance.minus(updateBalanceRequest.amount)
        walletRepository.save(Wallet(wallet.id, wallet.accountNumber, newBalance))
    }
}