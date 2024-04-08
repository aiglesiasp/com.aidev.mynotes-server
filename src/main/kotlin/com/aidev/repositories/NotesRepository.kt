package com.aidev.repositories

import com.aidev.kotlin.database.AppDatabase
import com.aidev.kotlin.database.DbNote
import com.aidev.model.Note
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import java.io.File

private const val DATABASE_NAME = "database.db"
object NotesRepository {

    private val notesDb = JdbcSqliteDriver("jdbc:sqlite:$DATABASE_NAME").let {
        if(!File("database.db").exists()) {
            AppDatabase.Schema.create(it)
        }
        AppDatabase(it)
    }.noteQueries

    // OBTENER LISTA COMPLETA
    fun getAll(): List<Note> = notesDb.select().executeAsList().map { it.toNote() }

    // OBTENER NOTA POR ID
    fun getNoteById(id: Long): Note? = notesDb.select().executeAsOneOrNull()?.toNote()

    // CREAR NOTA
    fun save(note: Note): Note {
        notesDb.insert(note.title, note.description, note.type.name)
        return notesDb.selectLastInsertedNote().executeAsOne().toNote()
    }

    // ACTUALIZAR NOTA
    fun update(note: Note): Boolean {
        if(getNoteById(note.id) == null) return false
        notesDb.update(note.title, note.description, note.type.name, note.id)
        return true
    }

    // ELIMINAR NOTA
    fun delete(id: Long): Boolean {
        if(getNoteById(id) == null) return false
        notesDb.delete(id)
        return true
    }
}

private fun DbNote.toNote(): Note {
    return Note(
        id = id,
        title = title,
        description = description,
        type = Note.NoteType.valueOf(type)
    )
}