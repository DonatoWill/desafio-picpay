package com.picpay.challenge.wallet.domain.usecase

import com.picpay.challenge.wallet.app.dto.response.TransactionResponse
import com.picpay.challenge.wallet.infrastructure.db.repository.TransactionRepository
import org.springframework.stereotype.Service

@Service
class FindTransactionsUseCase(val transactionRepository: TransactionRepository) {

    fun findByWallet(walletId: Long): List<TransactionResponse>? {
        val transactions = transactionRepository.findByWalletId(walletId)
        return transactions.map { transaction ->
            TransactionResponse(
                transaction.id,
                transaction.walletId,
                transaction.amount,
                transaction.type
            )
        }
    }
}