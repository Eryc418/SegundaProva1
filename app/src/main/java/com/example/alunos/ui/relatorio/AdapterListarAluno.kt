package com.example.alunos.ui.relatorio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.alunos.R
import com.example.alunos.data.model.Aluno
import com.example.alunos.fragments.relatorioAlunosDirections

class AdapterListarAluno: RecyclerView.Adapter<AdapterListarAluno.MyViewHolder>(){

    var listaAluno = emptyList<Aluno>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.modelo_aluno, parent, false)
        )
    }

    override fun getItemCount(): Int { return  this.listaAluno.size }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val alunoAtual = listaAluno[position]

        holder.itemView.apply {
            findViewById<TextView>(R.id.textMostrarMatricula).text = alunoAtual.matricula.toString()
            findViewById<TextView>(R.id.textMostrarNome).text = alunoAtual.nome
            findViewById<TextView>(R.id.textMostrarCurso).text = alunoAtual.curso
        }

        holder.itemView.findViewById<ConstraintLayout>(R.id.modeloAluno).setOnClickListener {
            val action = relatorioAlunosDirections.actionRelatorioAlunosToBuscarMatricula(alunoAtual)
            holder.itemView.findNavController().navigate(action)
        }

    }

    fun setData(alunos: List<Aluno>) {
        this.listaAluno = alunos
        notifyDataSetChanged()
    }

}