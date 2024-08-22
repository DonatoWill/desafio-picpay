package com.picpay.challenge.wallet.app.rest

import com.picpay.challenge.wallet.app.dto.request.UpdateBalanceRequest
import com.picpay.challenge.wallet.app.dto.request.WalletRequest
import com.picpay.challenge.wallet.app.dto.response.WalletResponse
import com.picpay.challenge.wallet.domain.usecase.CreateWalletUseCase
import com.picpay.challenge.wallet.domain.usecase.UpdateBalanceUseCase
import jakarta.websocket.server.PathParam
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("wallet")
class WalletController(
    val createWalletUseCase: CreateWalletUseCase,
    val updateBalanceUseCase: UpdateBalanceUseCase
) {

    @PostMapping
    fun createWallet(@RequestBody walletRequest: WalletRequest): ResponseEntity<WalletResponse> {
        val wallet  = createWalletUseCase.create(walletRequest)
        return ResponseEntity.ok().body(wallet)
    }

    @PatchMapping("/{walletId}/deposit")
    fun deposit(@RequestBody updateBalanceRequest: UpdateBalanceRequest, @PathVariable("walletId") walletId: Long) : ResponseEntity<Unit> {
        val wallet  = updateBalanceUseCase.deposit(walletId, updateBalanceRequest)
        return ResponseEntity.ok().build()
    }

    @PatchMapping("/{walletId}/withdraw")
    fun withdraw(@RequestBody updateBalanceRequest: UpdateBalanceRequest, @PathVariable("walletId") walletId: Long) : ResponseEntity<Unit> {
        val wallet  = updateBalanceUseCase.withdraw(walletId, updateBalanceRequest)
        return ResponseEntity.ok().build()
    }

}