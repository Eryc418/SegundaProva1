package com.example.alunos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.alunos.R
import com.example.alunos.data.model.Aluno
import com.example.alunos.databinding.FragmentBuscarMatriculaBinding
import com.example.alunos.ui.buscar.BuscarViewModel
import com.example.alunos.ui.inserir.InserirViewModel

class buscarMatricula : Fragment() {
    private val args by navArgs<buscarMatriculaArgs>()
    private lateinit var buscarViewModel: BuscarViewModel
    lateinit var binding: FragmentBuscarMatriculaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val factoryBuscar = BuscarViewModel.FactoryBuscar(requireActivity().application, args.aluno.matricula)
        buscarViewModel = ViewModelProvider(this, factoryBuscar).get(BuscarViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_buscar_matricula, container, false)
        binding.buscarViewModel = buscarViewModel
        binding.lifecycleOwner = this

        buscarViewModel.deleteMatricula.observe(viewLifecycleOwner, {
            if (it){
                Toast.makeText(context, "Aluno excluido", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_buscar_matricula_to_relatorioAlunos)
                buscarViewModel.deletarAlunoComleto()
            }
        })

        buscarViewModel.update.observe(viewLifecycleOwner, {
            if (it){
                val action = buscarMatriculaDirections.actionBuscarMatriculaToAtualizarDadosAluno(args.aluno)
                findNavController().navigate(action)
                buscarViewModel.atualizarAlunoCompleto()
            }
        })
        /*
         val view = inflater.inflate(R.layout.fragment_buscar_matricula, container, false)
         this.buscarViewModel = ViewModelProvider(this).get(BuscarViewModel::class.java)
         buscarViewModel.buscarAluno(args.aluno.matricula).observe(viewLifecycleOwner, {
             view.findViewById<TextView>(R.id.textViewListarMatricula).text = it.matricula.toString()
             view.findViewById<TextView>(R.id.textViewListarNome).text = it.nome
             view.findViewById<TextView>(R.id.textViewListarCurso).text = it.curso
             view.findViewById<TextView>(R.id.textViewListarPeriodo).text = it.periodo.toString()
             view.findViewById<TextView>(R.id.textViewListarCpf).text = it.cpf
             view.findViewById<TextView>(R.id.textViewListarIdade).text = it.idade.toString()
         })

         view.findViewById<Button>(R.id.button_Deletar_Aluno).setOnClickListener {
             buscarViewModel.deletarAluno(args.aluno)
             Toast.makeText(context, "Aluno excluido", Toast.LENGTH_LONG).show()
             findNavController().navigate(R.id.action_buscar_matricula_to_relatorioAlunos)
         }

        view.findViewById<Button>(R.id.button_Atualizar).setOnClickListener {
            val action = buscarMatriculaDirections.actionBuscarMatriculaToAtualizarDadosAluno(args.aluno)
            findNavController().navigate(action)
        }
         */
        return binding.root
    }
}