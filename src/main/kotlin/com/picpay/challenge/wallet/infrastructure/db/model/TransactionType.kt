package com.picpay.challenge.wallet.infrastructure.db.model

enum class TransactionType {
    TRANSFER_IN,
    TRANSFER_OUT,
    PAYMENT,
    DEPOSIT,
    WITHDRAW
}