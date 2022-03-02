package casmag.com.pruebafirestore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import casmag.com.pruebafirestore.databinding.ItemListEmpalmesBinding

class AdapterEmpalme(private val empalmeList: ArrayList<Empalme>) : RecyclerView.Adapter<AdapterEmpalme.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterEmpalme.MyViewHolder {
       val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_empalmes,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AdapterEmpalme.MyViewHolder, position: Int) {
       val empalme:Empalme = empalmeList[position]
        holder.nombre.text = empalme.nombre
        holder.tramo.text = empalme.tramo
        holder.distanci.text = empalme.distancia
        holder.numposte.text = empalme.nun_poste
    }

    override fun getItemCount()= empalmeList.size

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       // val binding = ItemListEmpalmesBinding.bind(itemView)
        val nombre = itemView.findViewById<TextView>(R.id.txtnombre)
        val tramo = itemView.findViewById<TextView>(R.id.txttramo)
        val distanci = itemView.findViewById<TextView>(R.id.txtdistancia)
        val numposte = itemView.findViewById<TextView>(R.id.txtnumposte)

    }
}