package com.example.alunos.data.model
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Parcelize
@Entity(tableName = "tb_aluno")
data class Aluno (
    @PrimaryKey(autoGenerate = true)
    var matricula: Long,
    var nome: String,
    var curso: String,
    var periodo: Int,
    var cpf: String,
    var idade: Int
        ): Parcelable