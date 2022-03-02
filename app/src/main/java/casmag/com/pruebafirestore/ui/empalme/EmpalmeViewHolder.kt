package casmag.com.pruebafirestore.ui.empalme

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import casmag.com.pruebafirestore.Empalme
import casmag.com.pruebafirestore.R
import casmag.com.pruebafirestore.databinding.ItemListEmpalmesBinding

class EmpalmeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val bindig = ItemListEmpalmesBinding.bind(view)

    /*esta es la forma tradicional de conectar la vistas
    private val nombre = view.findViewById<TextView>(R.id.txtnombre)
    private val tramo = view.findViewById<TextView>(R.id.txttramo)
    private val distancia = view.findViewById<TextView>(R.id.txtdistancia)
    private val numposte = view.findViewById<TextView>(R.id.txtnumposte)
*/
    fun render(empalme: Empalme,onClickListener:(Empalme) -> Unit) {

        bindig.txtnombre.text = empalme.nombre
        bindig.txttramo.text = empalme.tramo
        bindig.txtdistancia.text = empalme.distancia
        bindig.txtnumposte.text = empalme.nun_poste

        itemView.setOnClickListener{onClickListener(empalme)}

        }
        /*forma tradicional
       nombre.text = empalme.nombre
       tramo.text = empalme.tramo
       distancia.text = empalme.distancia
       numposte.text = empalme.nun_poste
*/
    }
