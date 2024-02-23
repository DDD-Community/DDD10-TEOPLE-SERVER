package com.ddd.teople.bootstrap.global.common.context

import org.springframework.core.NamedInheritableThreadLocal

object AuthenticationContextHolder {

    private val AUTHENTICATION_CONTEXT_HOLDER = NamedInheritableThreadLocal<AuthenticationContext>(
        AuthenticationContext::class.java.name
    )

    fun get(): AuthenticationContext =
        AUTHENTICATION_CONTEXT_HOLDER.get()

    fun set(context: AuthenticationContext) {
        AUTHENTICATION_CONTEXT_HOLDER.set(context)
    }

    fun clear() {
        AUTHENTICATION_CONTEXT_HOLDER.get()?.let { AUTHENTICATION_CONTEXT_HOLDER.remove() }
    }

}