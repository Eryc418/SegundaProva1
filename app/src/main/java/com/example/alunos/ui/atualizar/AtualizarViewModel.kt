package com.example.alunos.ui.atualizar

import android.app.Application
import androidx.lifecycle.*
import com.example.alunos.data.database.alunoDatabase
import com.example.alunos.data.model.Aluno
import com.example.alunos.data.repository.AlunoRepository
import com.example.alunos.fragments.buscarMatricula
import com.example.alunos.ui.buscar.BuscarViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class AtualizarViewModel (application: Application, matricula: Long): AndroidViewModel(application){
    var repositorio: AlunoRepository
    var listaAlunos: LiveData<List<Aluno>>
    private var _update = MutableLiveData(false)
    val update: LiveData<Boolean>
        get() = _update
    var aluno = MutableLiveData<Aluno>()

    init {
        val alunoDAO = alunoDatabase.getDatabase(application).alunoDAO()
        repositorio = AlunoRepository(alunoDAO)
        listaAlunos = repositorio.listaAlunos.asLiveData()
        aluno.value = repositorio.buscaAluno(matricula)
    }

    fun atualizarAluno(){
        viewModelScope.launch(Dispatchers.IO) {
            repositorio.atualizarAluno(aluno.value!!)
        }
        _update.value = true
    }

    fun atualizarAlunoCompleto(){
        _update.value = false
    }
    class FactoryAtualizar(private val application: Application, var matricula: Long): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AtualizarViewModel::class.java)){
                return AtualizarViewModel(application, matricula) as T
            }
            throw IllegalArgumentException("Erro na Classe AtualizarViewModel")
        }
    }

}