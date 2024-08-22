package com.picpay.challenge.wallet.infrastructure.db.repository

import com.picpay.challenge.wallet.infrastructure.db.model.Transaction
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : CrudRepository<Transaction, Long> {
}