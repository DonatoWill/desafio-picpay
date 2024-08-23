package com.picpay.challenge.wallet.infrastructure.db.model

import jakarta.persistence.*
import java.math.BigDecimal

@Entity(name = "wallet")
data class Wallet(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    val accountNumber: String,
    val balance: BigDecimal
) {
    fun debit(amount: BigDecimal): Wallet{
        val newBalance = this.balance.minus(amount)
        return Wallet(this.id, this.accountNumber, newBalance);
    }

    fun credit(amount: BigDecimal): Wallet {
        val newBalance = this.balance.plus(amount)
        return Wallet(this.id, this.accountNumber, newBalance);
    }
}