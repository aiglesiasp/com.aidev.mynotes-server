package com.aidev.repositories

import com.aidev.model.Note

object NotesRepository {

    private val list = mutableListOf<Note>()
    private var currentId = 1L

    // OBTENER LISTA COMPLETA
    fun getAll(): List<Note> = list

    // OBTENER NOTA POR ID
    fun getNoteById(id: Long): Note? = list.find { it.id == id }

    // CREAR NOTA
    fun save(note: Note): Note =
       note.copy(id = currentId++)
           .also(list::add)

    // ACTUALIZAR NOTA
    fun update(note: Note): Boolean =
        list.indexOfFirst { it.id == note.id }
            .takeIf { it > 0 }
            ?.also {  list[it] = note }
            .let { it != null }

    // ELIMINAR NOTA
    fun delete(id: Long): Boolean =
        list.indexOfFirst { it.id == id }
            .takeIf { it > 0 }
            ?.also(list::removeAt)
            .let { it != null }
}