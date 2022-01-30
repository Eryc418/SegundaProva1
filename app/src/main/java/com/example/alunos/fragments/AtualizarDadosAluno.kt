package com.example.alunos.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.alunos.R
import com.example.alunos.data.model.Aluno
import com.example.alunos.data.viewmodel.AlunoViewModel

class AtualizarDadosAluno : Fragment() {
    private val args by navArgs<AtualizarDadosAlunoArgs>()
    lateinit var alunoViewModel: AlunoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_atualizar_dados_aluno, container, false)
        this.alunoViewModel = ViewModelProvider(this).get(AlunoViewModel::class.java)

        alunoViewModel.buscarAluno(args.alunoAlterado.matricula).observe(viewLifecycleOwner, {
            view.findViewById<EditText>(R.id.editTextNome).setText(it.nome)
            view.findViewById<EditText>(R.id.editTextCurso).setText(it.curso)
            view.findViewById<EditText>(R.id.editTextPeriodo).setText(it.periodo.toString())
            view.findViewById<EditText>(R.id.editTextCpf).setText(it.cpf)
            view.findViewById<EditText>(R.id.editTextIdade).setText(it.idade.toString())

        })
        view.findViewById<Button>(R.id.button_SalvarAlteracao).setOnClickListener {
            alunoViewModel.atualizarAluno(
                Aluno(
                    args.alunoAlterado.matricula,
                    view.findViewById<EditText>(R.id.editTextNome).text.toString(),
                    view.findViewById<EditText>(R.id.editTextCurso).text.toString(),
                    view.findViewById<EditText>(R.id.editTextPeriodo).text.toString().toInt(),
                    view.findViewById<EditText>(R.id.editTextCpf).text.toString(),
                    view.findViewById<EditText>(R.id.editTextIdade).text.toString().toInt()
                )
            )
            Toast.makeText(context, "Alterando...", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_atualizarDadosAluno_to_relatorioAlunos)
        }
        return view
    }
}