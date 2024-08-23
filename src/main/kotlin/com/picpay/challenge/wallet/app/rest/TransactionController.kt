package com.picpay.challenge.wallet.app.rest

import com.picpay.challenge.wallet.app.dto.request.TransactionRequest
import com.picpay.challenge.wallet.app.dto.response.TransactionResponse
import com.picpay.challenge.wallet.app.dto.response.TransferResponse
import com.picpay.challenge.wallet.domain.usecase.CreateTransactionUseCase
import com.picpay.challenge.wallet.domain.usecase.FindTransactionsUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("wallet")
class TransactionController(
    private val createTransactionUseCase: CreateTransactionUseCase,
    private val findTransactions: FindTransactionsUseCase
) {

    @PostMapping("/transactions")
    fun createTransaction(@RequestBody transactionRequest: TransactionRequest): ResponseEntity<TransferResponse> {
        val createdTransaction = createTransactionUseCase.create(transactionRequest)

        return ResponseEntity.ok().body(createdTransaction)
    }

    @GetMapping("/{walletId}/transactions")
    fun list(@PathVariable("walletId") walletId: Long): ResponseEntity<List<TransactionResponse>> {
        return ResponseEntity.ok().body(findTransactions.findByWallet(walletId))
    }

}