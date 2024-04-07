package com.aidev.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json


fun Application.configureSerialization() {

    install(plugin = ContentNegotiation) {
       json(Json { prettyPrint = true })
    }
}