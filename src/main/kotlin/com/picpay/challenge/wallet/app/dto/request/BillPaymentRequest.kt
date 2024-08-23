package com.picpay.challenge.wallet.app.dto.request

data class BillPaymentRequest(
    val billCode: String,
    val walletId: Long,
)
