package com.example.mynotes;

import androidx.room.Entity;
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
class Note(val text:String) {
    @PrimaryKey(autoGenerate = true) var id=0
}
