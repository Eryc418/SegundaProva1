package com.example.alunos.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.alunos.data.dao.AlunoDAO
import com.example.alunos.data.model.Aluno

@Database(entities = [Aluno::class], version = 3, exportSchema = false)
abstract class alunoDatabase: RoomDatabase() {
    abstract fun alunoDAO(): AlunoDAO
    companion object {
        @Volatile
        private var INSTANCIA: alunoDatabase? = null
        fun getDatabase(context: Context): alunoDatabase {
            val tempInstance = INSTANCIA
            if (tempInstance != null)
                return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    alunoDatabase::class.java,
                    "database_alunos"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                INSTANCIA = instance
                return instance
            }
        }
    }
}