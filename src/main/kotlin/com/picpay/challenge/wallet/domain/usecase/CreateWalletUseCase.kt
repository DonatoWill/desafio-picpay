package com.picpay.challenge.wallet.domain.usecase

import com.picpay.challenge.wallet.app.dto.request.WalletRequest
import com.picpay.challenge.wallet.app.dto.response.WalletResponse
import com.picpay.challenge.wallet.infrastructure.db.model.Wallet
import com.picpay.challenge.wallet.infrastructure.db.repository.WalletRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class CreateWalletUseCase(
    val walletRepository: WalletRepository
) {

    @Transactional
    fun create(walletRequest: WalletRequest): WalletResponse {
        val newWallet = walletRepository.save(Wallet(null, walletRequest.account, BigDecimal.ZERO))
        return WalletResponse(newWallet.id!!, newWallet.accountNumber, newWallet.balance)
    }

}