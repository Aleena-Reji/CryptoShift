package com.example.encryption.service;

import org.springframework.stereotype.Service;

@Service
public class CaesarCipherService {

    // Printable ASCII range: 32 (space) to 126 (~)
    private static final int ASCII_START = 32;
    private static final int ASCII_RANGE = 95; // 126 - 32 + 1 = 95 printable characters

    /**
     * Encrypts the given message using Caesar Cipher over the full printable ASCII range.
     * Supports: letters (A-Z, a-z), digits (0-9), and special characters like @#$%^&*!...
     *
     * @param message the text to encrypt
     * @param shift   the shift value
     * @return encrypted string
     */
    public String encrypt(String message, int shift) {
        StringBuilder result = new StringBuilder();

        // Normalize shift to stay within ASCII range
        int normalizedShift = ((shift % ASCII_RANGE) + ASCII_RANGE) % ASCII_RANGE;

        for (char c : message.toCharArray()) {
            if (c >= ASCII_START && c <= 126) {
                // Shift within printable ASCII range (32–126)
                int shifted = (c - ASCII_START + normalizedShift) % ASCII_RANGE + ASCII_START;
                result.append((char) shifted);
            } else {
                // Leave non-printable characters (e.g., newline, tab) unchanged
                result.append(c);
            }
        }

        return result.toString();
    }

    /**
     * Decrypts the given message by reversing the Caesar Cipher shift.
     *
     * @param message the encrypted text
     * @param shift   the shift value used during encryption
     * @return decrypted string
     */
    public String decrypt(String message, int shift) {
        // Decrypting = encrypting with the reverse shift
        int normalizedShift = ((shift % ASCII_RANGE) + ASCII_RANGE) % ASCII_RANGE;
        return encrypt(message, ASCII_RANGE - normalizedShift);
    }
}
