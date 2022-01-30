package com.example.alunos.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.alunos.data.dao.AlunoDAO
import com.example.alunos.data.model.Aluno

class AlunoRepository (private var alunoDAO: AlunoDAO){
    val listaAlunos: LiveData<List<Aluno>> = alunoDAO.relatorioGeralAlunos()

     fun inserirAluno(aluno: Aluno){
        alunoDAO.inserirAluno(aluno)
    }

    fun deletaAluno(aluno: Aluno){
        alunoDAO.deletarAluno(aluno)
    }
    fun atualizarAluno(aluno: Aluno){
        alunoDAO.atualizarDadosAluno(aluno)
    }

    fun buscaAluno(matricula: Long): LiveData<Aluno>{
        return this.alunoDAO.buscarMatricula(matricula)
    }

     fun deletaTodosAlunos(){
        alunoDAO.deletarTodosALunos()
    }

}