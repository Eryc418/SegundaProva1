package com.example.alunos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alunos.R
import com.example.alunos.data.viewmodel.AlunoViewModel
import com.example.alunos.databinding.FragmentRelatorioAlunosBinding
import com.example.alunos.fragments.adapter.AdapterListarAluno
import com.google.android.material.floatingactionbutton.FloatingActionButton

class relatorioAlunos : Fragment() {

    lateinit var alunoViewModel: AlunoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_relatorio_alunos, container, false)
        val adapterListarAluno = AdapterListarAluno()

        this.alunoViewModel = ViewModelProvider(this).get(AlunoViewModel::class.java)

        view.findViewById<RecyclerView>(R.id.idRecicleView).apply {
            adapter = adapterListarAluno
            layoutManager = LinearLayoutManager(requireContext())
        }
        view.findViewById<FloatingActionButton>(R.id.idButtonAdicionar).setOnClickListener {
            findNavController().navigate(R.id.action_relatorioAlunos_to_inserirAlunos)
        }

        view.findViewById<Button>(R.id.button_DeletarTudo).setOnClickListener {
            alunoViewModel.deletarTodosAlunos()
            Toast.makeText(context, "Alunos excluidos", Toast.LENGTH_LONG).show()
        }
        
        this.alunoViewModel.listaAlunos.observe(viewLifecycleOwner, {adapterListarAluno.setData(it)})

        return view
    }
}