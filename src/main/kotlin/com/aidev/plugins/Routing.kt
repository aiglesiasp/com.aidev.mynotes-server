package com.aidev.plugins

import com.aidev.model.Note
import com.aidev.repositories.NotesRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*

fun Application.configureRouting() {
    // Starting point for a Ktor app
    routing {
        route("/notes") {

            // CREATE
            post {
                try {
                    val note = call.receive<Note>()
                    val savedNote = NotesRepository.save(note)
                    call.respond(
                        HttpStatusCode.Created,
                        "$note"
                    )
                }catch (e: Exception) {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        "Bad JSON Data Body: ${e.message}"
                    )
                }
            }

            //GET ALL
            get {
                call.respond(NotesRepository.getAll())
            }

            // GET BY ID
            get("{id}") {
                val id = call.parameters["id"] ?: return@get call.respond(
                    HttpStatusCode.BadRequest,
                    "Missing or malformed id"
                )
                val note = NotesRepository.getNoteById(id.toLong()) ?: return@get call.respond(
                    HttpStatusCode.NotFound,
                    "No note with id $id"
                )
                call.respond(note)
            }

            //UPDATE
            put {
                try {
                    val note = call.receive<Note>()
                    if(NotesRepository.update(note)) {
                        call.respond(note)
                    } else {
                        call.respond(
                            HttpStatusCode.NotFound,
                            "No note with id ${note.id}"
                        )
                    }

                }catch (e: Exception) {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        "Bad JSON Data Body: ${e.message}"
                    )
                }
            }

            //DELETE
            delete("{id}") {
                val id = call.parameters["id"] ?: return@delete call.respond(
                    HttpStatusCode.BadRequest,
                    "Missing or malformed id"
                )
                if(NotesRepository.delete(id.toLong())) {
                    call.respond(
                        HttpStatusCode.Accepted,
                        "The note has been delete"
                    )
                } else {
                    call.respond(
                        HttpStatusCode.NotFound,
                        "No note with id $id"
                    )
                }


            }
        }
    }
}
