package com.picpay.challenge.wallet.infrastructure.db.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity(name = "transaction")
data class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val walletId: Long,
    val amount: BigDecimal,
    val type: String, //deposit, withdraw, transfer, payment
    val status: String,
    val createdAt: LocalDateTime = LocalDateTime.now()
)