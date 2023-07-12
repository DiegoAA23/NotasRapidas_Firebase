package com.example.examennotasrapidas

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class FragmentInicio : Fragment(R.layout.fragment_inicio) {
    private lateinit var notasRecyclerView : RecyclerView
    private lateinit var notasArrayList : ArrayList<notas>
    private lateinit var referencia : DatabaseReference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = Firebase.firestore
        val boton = view.findViewById<FloatingActionButton>(R.id.btnAgregar)

        boton.setOnClickListener {
            requireActivity().supportFragmentManager.commit {
                replace(R.id.contenedorFragmento, FragmentAgregar())
            }
        }

        notasRecyclerView = view.findViewById(R.id.notasAlmacenadas)
        notasRecyclerView.layoutManager = LinearLayoutManager(MainActivity())
        notasRecyclerView.setHasFixedSize(true)

        notasArrayList  = arrayListOf<notas>()
        getUserData()

       /* db.collection("Nota").document("1").get()
            .addOnSuccessListener {
                    documento->documento?.let {
                Log.d("DatosObtenidos", "Los datos son: ${documento.data}")
                var contenido = documento.getString("Contenido")
                var titulo = documento.getString("Titulo")
                }
            }
            .addOnFailureListener {
                    error->error.message?.let { Log.e("Firebase", it) }
            }*/
    }

    private fun getUserData() {

        referencia = FirebaseDatabase.getInstance().getReference("Notas")

        referencia.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {

                    for (userSnapshot in snapshot.children) {


                        val nota = userSnapshot.getValue(notas::class.java)
                        notasArrayList.add(nota!!)

                    }

                    notasRecyclerView.adapter = NotasR.Adaptador(notasArrayList)


                }

            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}


//data class notas(val titulo:String="", val contenido:String="")




