package com.picpay.challenge.wallet.app.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.picpay.challenge.wallet.app.dto.request.TransactionRequest
import com.picpay.challenge.wallet.app.dto.request.UpdateBalanceRequest
import com.picpay.challenge.wallet.app.dto.request.WalletRequest
import com.picpay.challenge.wallet.infrastructure.db.model.Wallet
import com.picpay.challenge.wallet.infrastructure.db.repository.WalletRepository
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.patch
import org.springframework.test.web.servlet.post
import java.math.BigDecimal
import kotlin.test.assertTrue


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WalletControllerIntegrationTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper,
    val walletRepository: WalletRepository
) {

    @BeforeAll
    fun setup() {
        walletRepository.save(Wallet(1, "1234", BigDecimal.valueOf(1000)))
        walletRepository.save(Wallet(2, "2212", BigDecimal.valueOf(500)))
    }

    @Test
    fun `should return success when create a validated wallet`() {
        val request = WalletRequest("2244")
        mockMvc.post("/wallet") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }
            .andDo { print() }
            .andExpect { status { isOk() } }
    }

    @Test
    fun `should return success and update wallet when call deposit api`() {
        val request = UpdateBalanceRequest(BigDecimal.valueOf(250))
        mockMvc.patch("/wallet/{walletId}/deposit", 1) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }
            .andDo { print() }
            .andExpect { status { isOk() } }


        val walletPayer = walletRepository.findById(1).get()
        assertTrue(BigDecimal.valueOf(1250).compareTo(walletPayer.balance) == 0)
    }

    @Test
    fun `should return success and update wallet when call withdraw api`() {
        val request = UpdateBalanceRequest(BigDecimal.valueOf(250))
        mockMvc.patch("/wallet/{walletId}/withdraw", 2) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }
            .andDo { print() }
            .andExpect { status { isOk() } }


        val walletPayer = walletRepository.findById(2).get()
        assertTrue(BigDecimal.valueOf(250).compareTo(walletPayer.balance) == 0)
    }

}
