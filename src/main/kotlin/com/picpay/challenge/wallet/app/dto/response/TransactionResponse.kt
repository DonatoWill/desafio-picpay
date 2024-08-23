package com.picpay.challenge.wallet.app.dto.response

import com.picpay.challenge.wallet.infrastructure.db.model.Wallet
import java.math.BigDecimal

data class TransactionResponse(
    val id: Long?,
    val walletId: Long?,
    val amount: BigDecimal,
    val type: String
)
