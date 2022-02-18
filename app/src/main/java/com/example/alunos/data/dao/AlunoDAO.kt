package com.example.alunos.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.alunos.data.model.Aluno
import kotlinx.coroutines.flow.Flow

@Dao
interface AlunoDAO {
    @Insert
    fun inserirAluno(aluno: Aluno)

    @Delete
    fun deletarAluno(aluno: Aluno)

    @Update
    fun atualizarDadosAluno(aluno: Aluno)

    @Query("SELECT * from tb_aluno")
    fun relatorioGeralAlunos(): Flow<List<Aluno>>

    @Query("SELECT * FROM tb_aluno WHERE matricula = :matricula")
    fun buscarMatricula(matricula: Long): Aluno

    @Query("DELETE FROM tb_aluno")
    fun deletarTodosALunos()
}