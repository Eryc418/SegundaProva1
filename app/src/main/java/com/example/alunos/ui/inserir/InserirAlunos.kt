package com.example.alunos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alunos.R
import com.example.alunos.databinding.FragmentInserirAlunosBinding
import com.example.alunos.ui.inserir.InserirViewModel

class inserirAlunos : Fragment() {

    private lateinit var inserirViewModel: InserirViewModel
    lateinit var binding: FragmentInserirAlunosBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val factoryInserir = InserirViewModel.FactoryInserir(requireActivity().application)
        inserirViewModel = ViewModelProvider(this, factoryInserir).get(InserirViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_inserir_alunos, container, false)
        binding.insertViewModel = inserirViewModel
        binding.lifecycleOwner = this
        inserirViewModel.insertAluno.observe(viewLifecycleOwner, {
            if (it){
                Toast.makeText(context, "Aluno adicionado", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_inserirAlunos_to_relatorioAlunos)
                inserirViewModel.inserirAlunoCompleto()
            }
        })

        /*
        this.inserirViewModel = ViewModelProvider(this).get(InserirViewModel::class.java)
        var view = inflater.inflate(R.layout.fragment_inserir_alunos, container, false)


        view.findViewById<Button>(R.id.button_Add_Aluno).setOnClickListener {
            inserirViewModel.inserirAluno(Aluno(
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

         */
        return binding.root
    }
}