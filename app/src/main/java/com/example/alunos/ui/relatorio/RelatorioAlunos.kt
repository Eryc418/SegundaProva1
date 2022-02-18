package com.example.alunos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alunos.R
import com.example.alunos.ui.relatorio.AdapterListarAluno
import com.example.alunos.ui.relatorio.ListaAcoes
import com.example.alunos.ui.relatorio.RelatorioViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class relatorioAlunos : Fragment() {

    lateinit var relatorioViewModel: RelatorioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_relatorio_alunos, container, false)
        val adapterListarAluno = AdapterListarAluno()

        this.relatorioViewModel = ViewModelProvider(this).get(RelatorioViewModel::class.java)

        view.findViewById<RecyclerView>(R.id.idRecicleView).apply {
            adapter = adapterListarAluno
            layoutManager = LinearLayoutManager(requireContext())
        }
        view.findViewById<FloatingActionButton>(R.id.idButtonAdicionar).setOnClickListener {
            findNavController().navigate(R.id.action_relatorioAlunos_to_inserirAlunos)
        }

        view.findViewById<Button>(R.id.button_DeletarTudo).setOnClickListener {
            relatorioViewModel.deletarTodosAlunos()
            Toast.makeText(context, "Alunos excluidos", Toast.LENGTH_LONG).show()
        }
        view.findViewById<RecyclerView>(R.id.idRecicleView).addOnItemTouchListener(ListaAcoes(requireContext(), view.findViewById(R.id.idRecicleView), object : ListaAcoes.OnItemClickListener {
            override fun onItemClick(v: View, position: Int) {
                val action = relatorioAlunosDirections.actionRelatorioAlunosToBuscarMatricula(adapterListarAluno.listaAluno[position])
                Navigation.findNavController(v).navigate(action)
            }

            override fun onItemLongClick(v: View, position: Int) {
                val action = relatorioAlunosDirections.actionRelatorioAlunosToBuscarMatricula(adapterListarAluno.listaAluno[position])
                Navigation.findNavController(v).navigate(action)
            }
        }))
        this.relatorioViewModel.listaAlunos.observe(viewLifecycleOwner, {adapterListarAluno.setData(it)})

        return view
    }
}