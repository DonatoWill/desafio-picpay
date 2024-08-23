package com.picpay.challenge.wallet.app.rest

import com.picpay.challenge.wallet.app.dto.request.UpdateBalanceRequest
import com.picpay.challenge.wallet.app.dto.request.WalletRequest
import com.picpay.challenge.wallet.app.dto.response.WalletResponse
import com.picpay.challenge.wallet.domain.usecase.CreateWalletUseCase
import com.picpay.challenge.wallet.domain.usecase.UpdateBalanceUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("wallet")
class WalletController(
    private val createWalletUseCase: CreateWalletUseCase,
    private val updateBalanceUseCase: UpdateBalanceUseCase
) {

    @PostMapping
    fun createWallet(@RequestBody walletRequest: WalletRequest): ResponseEntity<WalletResponse> {
        val wallet  = createWalletUseCase.create(walletRequest)
        return ResponseEntity.ok().body(wallet)
    }

    @PatchMapping("/{walletId}/deposit")
    fun deposit(@RequestBody updateBalanceRequest: UpdateBalanceRequest, @PathVariable("walletId") walletId: Long) : ResponseEntity<Unit> {
        updateBalanceUseCase.deposit(walletId, updateBalanceRequest)
        return ResponseEntity.ok().build()
    }

    @PatchMapping("/{walletId}/withdraw")
    fun withdraw(@RequestBody updateBalanceRequest: UpdateBalanceRequest, @PathVariable("walletId") walletId: Long) : ResponseEntity<Unit> {
        updateBalanceUseCase.withdraw(walletId, updateBalanceRequest)
        return ResponseEntity.ok().build()
    }

}