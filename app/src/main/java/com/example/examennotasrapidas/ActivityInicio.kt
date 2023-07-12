package com.example.examennotasrapidas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ActivityInicio : AppCompatActivity() {

    private lateinit var notasRecyclerView: RecyclerView
    private lateinit var notasArrayList: ArrayList<notas>
    private lateinit var referencia: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)


        val db = Firebase.firestore
        val boton = findViewById<FloatingActionButton>(R.id.btnAgregar)

        boton.setOnClickListener {
            var i = Intent(this, ActivityAgregar::class.java)
            startActivity(i)
            finish()
        }

        notasRecyclerView = findViewById(R.id.notasAlmacenadas)
        notasRecyclerView.layoutManager = LinearLayoutManager(MainActivity())
        notasRecyclerView.setHasFixedSize(true)

        notasArrayList  = arrayListOf<notas>()
        getUserData()
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

data class notas(val titulo:String="", val contenido:String="")