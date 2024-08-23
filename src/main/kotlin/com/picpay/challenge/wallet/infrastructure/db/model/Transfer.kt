package com.picpay.challenge.wallet.infrastructure.db.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "transfer")
data class Transfer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    val payerWallet: Long,
    val payeeWallet: Long,
    @OneToOne
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    val transaction: Transaction,
    val createdAt: LocalDateTime = LocalDateTime.now()
)
