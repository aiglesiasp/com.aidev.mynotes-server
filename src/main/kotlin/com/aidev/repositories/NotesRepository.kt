package com.aidev.repositories

import com.aidev.model.Note
import com.aidev.model.Note.NoteType.*

object NotesRepository {

    private val list = mutableListOf<Note>()
    private var currentId = 1L

    // OBTENER LISTA COMPLETA
    fun getAll(): List<Note> {
        //Devolvemos la lista completa
        return list
    }

    // OBTENER NOTA POR ID
    fun getNoteById(id: Long): Note? {
        //Buscamos en la lsita por ese id
        val note: Note? = list.find { it.id == id }
        return note
    }

    // CREAR NOTA
    fun save(note: Note): Note {
        // Creamos nota con el currentId
        val newNote = note.copy(id = currentId)
        currentId++
        //Agregamos nota a la lista
        list.add(newNote)
        return newNote
    }

    // ACTUALIZAR NOTA
    fun update(note: Note): Boolean {
        //Obtener indice de la nota
        val index = list.indexOfFirst { it.id == note.id }
        //Si no lo encontramos devolvemos false
        if(index<0) return false
        //Sino actualizamos la nota
        list[index] = note
        return true
    }

    // ELIMINAR NOTA
    fun delete(id: Long): Boolean {
        //Obtener indice de la nota
        val index = list.indexOfFirst { it.id == id }
        //Si no lo encontramos devolvemos false
        if(index<0) return false
        //Sino eliminamos la nota
        list.removeAt(index)
        return true
    }
}