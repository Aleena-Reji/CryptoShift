package com.example.encryption.controller;

import com.example.encryption.service.CaesarCipherService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class EncryptionController {

    private final CaesarCipherService service;

    public EncryptionController(CaesarCipherService service) {
        this.service = service;
    }

    @GetMapping("/encrypt")
    public String encrypt(
            @RequestParam String text,
            @RequestParam int shift) {

        return service.encrypt(text, shift);
    }

    @GetMapping("/decrypt")
    public String decrypt(
            @RequestParam String text,
            @RequestParam int shift) {

        return service.decrypt(text, shift);
    }
}
