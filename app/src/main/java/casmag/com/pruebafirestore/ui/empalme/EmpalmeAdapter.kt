package casmag.com.pruebafirestore.ui.empalme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import casmag.com.pruebafirestore.Empalme
import casmag.com.pruebafirestore.R

class EmpalmeAdapter(private val empalList: ArrayList<Empalme>, private val onClickListener:(Empalme) -> Unit) : RecyclerView.Adapter<EmpalmeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpalmeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EmpalmeViewHolder(layoutInflater.inflate(R.layout.item_list_empalmes,parent,false))
    }

    override fun onBindViewHolder(holder: EmpalmeViewHolder, position: Int) {
        val item = empalList[position]
        holder.render(item,onClickListener)
    }
    override fun getItemCount(): Int = empalList.size
}