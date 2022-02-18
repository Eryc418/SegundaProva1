package com.example.alunos.ui.inserir

import android.app.Application
import androidx.lifecycle.*
import com.example.alunos.data.database.alunoDatabase
import com.example.alunos.data.model.Aluno
import com.example.alunos.data.repository.AlunoRepository
import com.example.alunos.fragments.buscarMatricula
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class InserirViewModel (application: Application): AndroidViewModel(application){
    // Handlers --> Ações
    private var _insertAluno = MutableLiveData(false)
    val insertAluno: LiveData<Boolean>
        get() = _insertAluno
    // Objeto Aluno
    var aluno = Aluno()
    // Dados de Alunos
    var repositorio: AlunoRepository
    var listaAlunos: LiveData<List<Aluno>>
    init {
        val alunoDAO = alunoDatabase.getDatabase(application).alunoDAO()
        repositorio = AlunoRepository(alunoDAO)
        listaAlunos = repositorio.listaAlunos.asLiveData()
    }
    fun inserirAluno(){
        this._insertAluno.value = true
        viewModelScope.launch(Dispatchers.IO) {
            repositorio.inserirAluno(aluno)
        }
    }

    fun  inserirAlunoCompleto(){
        this._insertAluno.value = false
    }

    class FactoryInserir(private val application: Application): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(InserirViewModel::class.java)){
                return InserirViewModel(application) as T
            }
            throw IllegalArgumentException("Erro na Classe InserirViewModel")
        }
    }
}