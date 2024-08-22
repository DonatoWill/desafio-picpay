package com.picpay.challenge.wallet.domain.usecase

import com.picpay.challenge.wallet.app.dto.response.TransactionResponse
import com.picpay.challenge.wallet.infrastructure.db.repository.TransactionRepository
import org.springframework.stereotype.Service

@Service
class FindTransactionsUseCase(val transactionRepository: TransactionRepository) {

    fun findAll(): List<TransactionResponse> {
        val transactions = transactionRepository.findAll();
        return transactions.map { transaction ->
            TransactionResponse(
                transaction.id!!,
                transaction.payerWallet,
                transaction.payeeWallet,
                transaction.amount
            )
        }
    }

}
