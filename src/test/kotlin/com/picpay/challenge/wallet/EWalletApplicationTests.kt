package com.picpay.challenge.wallet

import com.fasterxml.jackson.databind.ObjectMapper
import com.picpay.challenge.wallet.app.dto.request.TransactionRequest
import com.picpay.challenge.wallet.infrastructure.db.model.Wallet
import com.picpay.challenge.wallet.infrastructure.db.repository.WalletRepository
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import java.math.BigDecimal
import kotlin.test.assertEquals
import kotlin.test.assertTrue


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EWalletApplicationTests @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper,
    val walletRepository: WalletRepository
) {

    @BeforeAll
    fun setup() {
        walletRepository.save(Wallet(1, "1234", BigDecimal.valueOf(1000)))
        walletRepository.save(Wallet(2, "4321", BigDecimal.valueOf(200)))
    }

    @Test
    fun `should return success when create a validated transaction`() {
        val request = TransactionRequest(1, 2, BigDecimal.valueOf(500))
        mockMvc.post("/wallet/transactions") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }
            .andDo { print() }
            .andExpect { status { isOk() } }

        val walletPayee = walletRepository.findById(2).get()
        assertTrue(BigDecimal.valueOf(700).compareTo(walletPayee.balance) == 0)

        val walletPayer = walletRepository.findById(1).get()
        assertTrue(BigDecimal.valueOf(500).compareTo(walletPayer.balance) == 0)
    }

}
