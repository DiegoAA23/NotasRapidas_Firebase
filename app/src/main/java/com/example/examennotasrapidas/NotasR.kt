package com.example.examennotasrapidas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotasR {

    class Adaptador(private val listanotas : ArrayList<notas>) : RecyclerView.Adapter<Adaptador.viewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {

            val vistaItems = LayoutInflater.from(parent.context).inflate(R.layout.tarjeta_notas, parent, false)
            return viewHolder(vistaItems)
        }

        override fun getItemCount(): Int {
            return listanotas.size
        }

        override fun onBindViewHolder(holder: viewHolder, position: Int) {

            val actual = listanotas[position]

            holder.titulo.text = actual.titulo
            holder.contenido.text = actual.contenido
        }


        class viewHolder(itemview : View) : RecyclerView.ViewHolder(itemview){
            val titulo : TextView = itemview.findViewById(R.id.tituloNota)
            val contenido : TextView = itemview.findViewById(R.id.contenidoNota)
        }
    }

}