package com.picpay.challenge.wallet.infrastructure.db.repository

import com.picpay.challenge.wallet.infrastructure.db.model.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : JpaRepository<Transaction, Long> {

    fun findByWalletId(walletId: Long): List<Transaction>

}