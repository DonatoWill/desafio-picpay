package com.picpay.challenge.wallet.app.dto.request

import java.math.BigDecimal

data class UpdateBalanceRequest(
    val amount: BigDecimal
)