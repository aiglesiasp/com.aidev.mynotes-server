package com.aidev.plugins

import com.aidev.repositories.NotesRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*

fun Application.configureRouting() {
    // Starting point for a Ktor app
    routing {
        route("/notes") {

            // CREATE

            //READ
            get {
                call.respond(NotesRepository.getAll())
            }

            //UPDATE

            //DELETE
        }
    }
}
