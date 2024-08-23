package com.picpay.challenge.wallet.infrastructure.db.model

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal
import java.time.LocalDateTime

data class BillPayment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    val walletId: Long,
    val amount: BigDecimal,
    val billNumber: String,
    val dueDate: LocalDateTime,
    val paymentDate: LocalDateTime,
    val createdAt: LocalDateTime
)