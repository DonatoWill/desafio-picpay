package com.picpay.challenge.wallet.app.rest

import com.picpay.challenge.wallet.app.dto.request.UpdateBalanceRequest
import com.picpay.challenge.wallet.app.dto.request.WalletRequest
import com.picpay.challenge.wallet.app.dto.response.WalletResponse
import com.picpay.challenge.wallet.domain.usecase.CreateWalletUseCase
import com.picpay.challenge.wallet.domain.usecase.UpdateBalanceUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("wallet")
@Tag(name = "Wallet Controller APIs")
class WalletController(
    private val createWalletUseCase: CreateWalletUseCase,
    private val updateBalanceUseCase: UpdateBalanceUseCase
) {

    @PostMapping
    @Operation(summary = "Create a new wallet")
    fun createWallet(@RequestBody walletRequest: WalletRequest): ResponseEntity<WalletResponse> {
        val wallet  = createWalletUseCase.create(walletRequest)
        return ResponseEntity.ok().body(wallet)
    }

    @PatchMapping("/{walletId}/deposit")
    @Operation(summary = "Deposit amount into a wallet")
    fun deposit(@RequestBody updateBalanceRequest: UpdateBalanceRequest, @PathVariable("walletId") walletId: Long) : ResponseEntity<Unit> {
        updateBalanceUseCase.deposit(walletId, updateBalanceRequest)
        return ResponseEntity.ok().build()
    }

    @PatchMapping("/{walletId}/withdraw")
    @Operation(summary = "Withdraw amount into a wallet")
    fun withdraw(@RequestBody updateBalanceRequest: UpdateBalanceRequest, @PathVariable("walletId") walletId: Long) : ResponseEntity<Unit> {
        updateBalanceUseCase.withdraw(walletId, updateBalanceRequest)
        return ResponseEntity.ok().build()
    }

}