package com.example.alunos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.alunos.R
import com.example.alunos.data.viewmodel.AlunoViewModel

class buscarMatricula : Fragment() {
    private val args by navArgs<buscarMatriculaArgs>()
    lateinit var alunoViewModel: AlunoViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         val view = inflater.inflate(R.layout.fragment_buscar_matricula, container, false)
         this.alunoViewModel = ViewModelProvider(this).get(AlunoViewModel::class.java)
         alunoViewModel.buscarAluno(args.aluno.matricula).observe(viewLifecycleOwner, {
             view.findViewById<TextView>(R.id.textViewListarMatricula).text = it.matricula.toString()
             view.findViewById<TextView>(R.id.textViewListarNome).text = it.nome
             view.findViewById<TextView>(R.id.textViewListarCurso).text = it.curso
             view.findViewById<TextView>(R.id.textViewListarPeriodo).text = it.periodo.toString()
             view.findViewById<TextView>(R.id.textViewListarCpf).text = it.cpf
             view.findViewById<TextView>(R.id.textViewListarIdade).text = it.idade.toString()
         })

         view.findViewById<Button>(R.id.button_Deletar_Aluno).setOnClickListener {
             alunoViewModel.deletarAluno(args.aluno)
             Toast.makeText(context, "Aluno excluido", Toast.LENGTH_LONG).show()
             findNavController().navigate(R.id.action_buscar_matricula_to_relatorioAlunos)
         }

        view.findViewById<Button>(R.id.button_Atualizar).setOnClickListener {
            val action = buscarMatriculaDirections.actionBuscarMatriculaToAtualizarDadosAluno(args.aluno)
            findNavController().navigate(action)
        }
        return view
    }
}