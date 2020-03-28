package com.senacrs.crudprodutosfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("FIREBASE","TESTE")

        setup()
        setContentView(R.layout.activity_main)
    }

    fun setup(){
        val db = FirebaseFirestore.getInstance()

        // Create a new user with a first and last name
        val produto = hashMapOf(
            "nome" to "Prod8",
            "preco" to 221.3
        )

        Log.i("FIREBASE",produto.toString());
// Add a new document with a generated ID
        db.collection("produtos")
            .add(produto)
            .addOnSuccessListener { documentReference ->
                Log.d("FIREBASE", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("FIREBASE", "Error adding document", e)
            }

        db.collection("produtos")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("FIREBASE", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("FIREBASE", "Error getting documents.", exception)
            }

    }
}
