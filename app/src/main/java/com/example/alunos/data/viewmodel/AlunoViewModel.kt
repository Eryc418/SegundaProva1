package com.example.alunos.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.alunos.data.database.alunoDatabase
import com.example.alunos.data.model.Aluno
import com.example.alunos.data.repository.AlunoRepository
import com.example.alunos.fragments.buscarMatricula
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlunoViewModel (application: Application): AndroidViewModel(application){
    var repositorio: AlunoRepository
    var listaAlunos: LiveData<List<Aluno>>
    init {
        val alunoDAO = alunoDatabase.getDatabase(application).alunoDAO()
        repositorio = AlunoRepository(alunoDAO)
        listaAlunos = repositorio.listaAlunos
    }
    fun inserirAluno(aluno: Aluno){
        viewModelScope.launch(Dispatchers.IO) { repositorio.inserirAluno(aluno) }
    }
    fun buscarAluno(matricula: Long): LiveData<Aluno>{
        return repositorio.buscaAluno(matricula)
    }
    fun deletarAluno(aluno: Aluno){
        viewModelScope.launch(Dispatchers.IO) { repositorio.deletaAluno(aluno) }
    }
    fun atualizarAluno(aluno: Aluno){
        viewModelScope.launch(Dispatchers.IO) { repositorio.atualizarAluno(aluno) }
    }
    fun deletarTodosAlunos(){
        viewModelScope.launch(Dispatchers.IO) { repositorio.deletaTodosAlunos() }
    }
}