package com.example.alunos.ui.buscar

import android.app.Application
import androidx.lifecycle.*
import com.example.alunos.data.database.alunoDatabase
import com.example.alunos.data.model.Aluno
import com.example.alunos.data.repository.AlunoRepository
import com.example.alunos.fragments.buscarMatricula
import com.example.alunos.ui.inserir.InserirViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class BuscarViewModel (application: Application, val matricula: Long): AndroidViewModel(application){
    var repositorio: AlunoRepository
    var listaAlunos: LiveData<List<Aluno>>
    private var _update = MutableLiveData(false)
    val update: LiveData<Boolean>
        get() = _update

    private var _deleteMatricula = MutableLiveData(false)
    val deleteMatricula: LiveData<Boolean>
        get() = _deleteMatricula
    var aluno = MutableLiveData<Aluno>()


    init {
        val alunoDAO = alunoDatabase.getDatabase(application).alunoDAO()
        repositorio = AlunoRepository(alunoDAO)
        listaAlunos = repositorio.listaAlunos.asLiveData()
        aluno.value = repositorio.buscaAluno(matricula)
    }

    fun atualizarAluno(aluno: Aluno){
        viewModelScope.launch(Dispatchers.IO) {
            repositorio.atualizarAluno(aluno)
        }
        _update.value = true
    }
    fun atualizarAlunoCompleto(){
        _update.value = false
    }
    fun deletarAluno(){
        viewModelScope.launch(Dispatchers.IO) {
            repositorio.deletaAluno(aluno.value!!)
        }
        _deleteMatricula.value = true
    }
    fun deletarAlunoComleto(){
        _deleteMatricula.value = false
    }

    class FactoryBuscar(private val application: Application, var matricula: Long): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BuscarViewModel::class.java)){
                return BuscarViewModel(application, matricula) as T
            }
            throw IllegalArgumentException("Erro na Classe BuscarViewModel")
        }
    }
}