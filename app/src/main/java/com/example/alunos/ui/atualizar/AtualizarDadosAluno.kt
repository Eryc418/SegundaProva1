package com.example.alunos.ui.atualizar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.alunos.R
import com.example.alunos.data.model.Aluno
import com.example.alunos.databinding.FragmentAtualizarDadosAlunoBinding
import com.example.alunos.ui.buscar.BuscarViewModel

class AtualizarDadosAluno : Fragment() {
    private val args by navArgs<AtualizarDadosAlunoArgs>()
    private lateinit var atualizarViewModel: AtualizarViewModel
    lateinit var binding: FragmentAtualizarDadosAlunoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val factoryAtualizar = AtualizarViewModel.FactoryAtualizar(requireActivity().application, args.alunoAlterado.matricula)
        atualizarViewModel = ViewModelProvider(this, factoryAtualizar).get(AtualizarViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_atualizar_dados_aluno, container, false)
        binding.atualizarViewModel = atualizarViewModel
        binding.lifecycleOwner = this

        atualizarViewModel.update.observe(viewLifecycleOwner, {
            if (it){
                Toast.makeText(context, "Alterando...", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_atualizarDadosAluno_to_relatorioAlunos)
                atualizarViewModel.atualizarAlunoCompleto()
            }
        })
        // Inflate the layout for this fragment
        /*
        val view = inflater.inflate(R.layout.fragment_atualizar_dados_aluno, container, false)
        this.atualizarViewModel = ViewModelProvider(this).get(AtualizarViewModel::class.java)

        atualizarViewModel.buscarAluno(args.alunoAlterado.matricula).observe(viewLifecycleOwner, {
            view.findViewById<EditText>(R.id.editTextNome).setText(it.nome)
            view.findViewById<EditText>(R.id.editTextCurso).setText(it.curso)
            view.findViewById<EditText>(R.id.editTextPeriodo).setText(it.periodo.toString())
            view.findViewById<EditText>(R.id.editTextCpf).setText(it.cpf)
            view.findViewById<EditText>(R.id.editTextIdade).setText(it.idade.toString())

        })
        view.findViewById<Button>(R.id.button_SalvarAlteracao).setOnClickListener {
            atualizarViewModel.atualizarAluno(
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

         */
        return binding.root
    }
}