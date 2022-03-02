package casmag.com.pruebafirestore

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import casmag.com.pruebafirestore.databinding.ActivityMainBinding
import casmag.com.pruebafirestore.ui.empalme.EmpalmeAdapter
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var empalArrayList: ArrayList<Empalme>
    private lateinit var adapterEmpal: AdapterEmpalme
    private lateinit var empalAdapter: EmpalmeAdapter
    val db = Firebase.firestore

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        guardar()
        //llenarRecyclerView()
        initRecyclerView()

        binding.fabtnAdd.setOnClickListener {
            changeFormRegistro()
        }
    }

    private fun initRecyclerView() {
        binding.rcvEmpalme.layoutManager = LinearLayoutManager(this)
        binding.rcvEmpalme.setHasFixedSize(true)
        empalArrayList = arrayListOf()
        empalAdapter = EmpalmeAdapter(empalArrayList) { empalme ->
            onItenSelectec(empalme)
        }
        binding.rcvEmpalme.adapter = empalAdapter
        evenChangeListener()

    }

    private fun onItenSelectec(empalme: Empalme) {
        binding.edtnombre.setText(empalme.nombre)
        binding.edtTramo.setText(empalme.tramo)
        binding.edtdistancia.setText(empalme.distancia)
        binding.edtposte.setText(empalme.nun_poste)
        //Toast.makeText(this,empalme.nombre,Toast.LENGTH_SHORT).show()
        changeFormRegistro()
    }

    private fun llenarRecyclerView() {
        binding.rcvEmpalme.layoutManager = LinearLayoutManager(this)
        binding.rcvEmpalme.setHasFixedSize(true)

        empalArrayList = arrayListOf()
        adapterEmpal = AdapterEmpalme(empalArrayList)
        binding.rcvEmpalme.adapter = adapterEmpal
        evenChangeListener()
    }

    private fun evenChangeListener() {
        db.collection("Empalme")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(
                    value: QuerySnapshot?,
                    error: FirebaseFirestoreException?
                ) {
                    if (error != null) {
                        Log.e("Consulta", error.message.toString())
                        return
                    }

                    for (dc: DocumentChange in value?.documentChanges!!) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            empalArrayList.add(dc.document.toObject(Empalme::class.java))

                        }
                    }

                    empalAdapter.notifyDataSetChanged()
                }

            })
    }

    private fun guardar() {
        binding.btnguardar.setOnClickListener {
            val nombre = binding.edtnombre.text.toString().trim()
            val tramo = binding.edtTramo.text.toString().trim()
            val distan = binding.edtdistancia.text.toString().trim()
            val numposte = binding.edtposte.text.toString().trim()
            if (nombre.isEmpty()) {
                Toast.makeText(this, "Ingrese el nombre del empalme", Toast.LENGTH_SHORT).show()
                binding.edtnombre.requestFocus()
            } else {
                val empal = Empalme(nombre, tramo, distan, numposte)
                // Add a new document with a generated ID
                db.collection("Empalme")
                    .add(empal)
                    .addOnSuccessListener { documentReference ->
                        //  Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                        limpiar()
                        changeFormRegistro()
                    }
                    .addOnFailureListener { e ->
                        //  Log.w(TAG, "Error adding document", e)
                        Toast.makeText(this, "Error al registro $e", Toast.LENGTH_SHORT).show()
                    }
            }

        }
    }

    private fun limpiar() {
        binding.edtnombre.setText("")
        binding.edtTramo.setText("")
        binding.edtdistancia.setText("")
        binding.edtposte.setText("")
        binding.edtnombre.requestFocus()
    }

    private fun changeFormRegistro() {
        if (binding.cvformuRegistro.visibility == View.GONE) {
            binding.cvformuRegistro.visibility = View.VISIBLE
            binding.clpanelRcv.visibility = View.GONE
            binding.fabtnAdd.setImageResource(R.drawable.ic_reducir_24)
        }else{
            binding.cvformuRegistro.visibility = View.GONE
            binding.clpanelRcv.visibility = View.VISIBLE
            binding.fabtnAdd.setImageResource(R.drawable.ic_baseline_add_24)

        }
    }
}