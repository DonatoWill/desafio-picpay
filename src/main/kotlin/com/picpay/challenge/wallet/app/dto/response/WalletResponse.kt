package com.picpay.challenge.wallet.app.dto.response

import java.math.BigDecimal

data class WalletResponse(
    val id:Long,
    val account: String,
    val balance: BigDecimal
)
