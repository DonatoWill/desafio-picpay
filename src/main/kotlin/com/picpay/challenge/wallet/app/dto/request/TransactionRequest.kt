package com.picpay.challenge.wallet.app.dto.request

import java.math.BigDecimal

data class TransactionRequest(
    val payerWallet: Long,
    val payeeWallet: Long,
    val amount: BigDecimal
)
