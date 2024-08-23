package com.picpay.challenge.wallet.app.dto.response

import java.math.BigDecimal

data class TransferResponse(
    val id: Long?,
    val payerWallet: Long?,
    val payeeWallet: Long?,
    val amount: BigDecimal
)
