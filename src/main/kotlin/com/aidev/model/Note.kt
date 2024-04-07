package com.aidev.model

import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val id: Long,
    val title: String,
    val description: String,
    val type: NoteType
) {
    enum class NoteType { TEXT, AUDIO }
}