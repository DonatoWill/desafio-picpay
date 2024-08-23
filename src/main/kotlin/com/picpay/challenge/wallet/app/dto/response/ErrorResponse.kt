package com.picpay.challenge.wallet.app.dto.response

data class ErrorResponse(
    val status: Int,
    val error: String,
    val message: String?,
    val details: List<ErrorDetail>? = emptyList()
)