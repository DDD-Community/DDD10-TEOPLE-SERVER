package com.ddd.teople.application.global.utils

import java.security.SecureRandom
import java.util.*

object CodeUtils {

    private val secureRandom = SecureRandom()

    fun generateId(digitNum: Int, randUpperCase: Boolean): String {
        val secret = StringBuilder().apply {
            while (length < digitNum) {
                append(generateUUID())
            }
        }.substring(0, digitNum)

        return if (randUpperCase) {
            secret.map { char ->
                if (char.isLowerCase() && secureRandom.nextBoolean()) char.uppercaseChar() else char
            }.joinToString("")
        } else {
            secret
        }
    }

    private fun generateUUID(): String {
        return UUID.randomUUID().toString().replace("-", "")
    }
}