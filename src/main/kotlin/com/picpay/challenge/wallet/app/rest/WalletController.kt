package com.picpay.challenge.wallet.app.rest

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("wallet")
class WalletController {

    @PostMapping("/transaction")
    fun createTransaction(): ResponseEntity<String> {
        return ResponseEntity.ok().build()
    }

    @GetMapping()
    fun list(): ResponseEntity<String> {
        return ResponseEntity.ok().build()
    }

}