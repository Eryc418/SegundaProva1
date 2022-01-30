package com.example.alunos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alunos.R
import com.example.alunos.data.model.Aluno
import com.example.alunos.data.viewmodel.AlunoViewModel
import com.example.alunos.databinding.FragmentInserirAlunosBinding
import com.example.alunos.databinding.FragmentRelatorioAlunosBinding

class inserirAlunos : Fragment() {
    lateinit var alunoViewModel: AlunoViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.alunoViewModel = ViewModelProvider(this).get(AlunoViewModel::class.java)
        var view = inflater.inflate(R.layout.fragment_inserir_alunos, container, false)
        view.findViewById<Button>(R.id.button_Add_Aluno).setOnClickListener {
            alunoViewModel.inserirAluno(Aluno(
                0,
                view.findViewById<EditText>(R.id.nomeAluno).text.toString(),
                view.findViewById<EditText>(R.id.cursoAluno).text.toString(),
                view.findViewById<EditText>(R.id.periodoAluno).text.toString().toInt(),
                view.findViewById<EditText>(R.id.cpfAluno).text.toString(),
                view.findViewById<EditText>(R.id.idadeAluno).text.toString().toInt()
            ))
            Toast.makeText(context, "Aluno adicionado", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_inserirAlunos_to_relatorioAlunos)

        }
        return view
    }
}