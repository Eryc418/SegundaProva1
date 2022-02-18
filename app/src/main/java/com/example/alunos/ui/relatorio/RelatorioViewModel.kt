package com.example.alunos.ui.relatorio

import android.app.Application
import androidx.lifecycle.*
import com.example.alunos.data.database.alunoDatabase
import com.example.alunos.data.model.Aluno
import com.example.alunos.data.repository.AlunoRepository
import com.example.alunos.fragments.buscarMatricula
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RelatorioViewModel (application: Application): AndroidViewModel(application){
    var repositorio: AlunoRepository
    var listaAlunos: LiveData<List<Aluno>>
    init {
        val alunoDAO = alunoDatabase.getDatabase(application).alunoDAO()
        repositorio = AlunoRepository(alunoDAO)
        listaAlunos = repositorio.listaAlunos.asLiveData()
    }
    fun deletarTodosAlunos(){
        viewModelScope.launch(Dispatchers.IO) { repositorio.deletaTodosAlunos() }
    }
}