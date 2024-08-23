package com.picpay.challenge.wallet.app.rest

import com.picpay.challenge.wallet.app.dto.request.TransactionRequest
import com.picpay.challenge.wallet.app.dto.response.TransactionResponse
import com.picpay.challenge.wallet.app.dto.response.TransferResponse
import com.picpay.challenge.wallet.domain.usecase.CreateTransactionUseCase
import com.picpay.challenge.wallet.domain.usecase.FindTransactionsUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("wallet")
@Tag(name = "Transaction Controller APIs")
class TransactionController(
    private val createTransactionUseCase: CreateTransactionUseCase,
    private val findTransactions: FindTransactionsUseCase
) {

    @PostMapping("/transactions")
    @Operation(summary = "Create a new transaction between two wallets")
    fun createTransaction(@RequestBody transactionRequest: TransactionRequest): ResponseEntity<TransferResponse> {
        val createdTransaction = createTransactionUseCase.create(transactionRequest)

        return ResponseEntity.ok().body(createdTransaction)
    }

    @GetMapping("/{walletId}/transactions")
    @Operation(summary = "Get all transaction for the given wallet id")
    fun list(@PathVariable("walletId") walletId: Long): ResponseEntity<List<TransactionResponse>> {
        return ResponseEntity.ok().body(findTransactions.findByWallet(walletId))
    }

}