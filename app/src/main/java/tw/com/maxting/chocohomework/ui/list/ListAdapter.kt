package tw.com.maxting.chocohomework.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tw.com.maxting.chocohomework.R
import tw.com.maxting.chocohomework.data.Drama

class ListAdapter : RecyclerView.Adapter<ListViewHolder>() {
    var list = mutableListOf<Drama>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_drama, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun update(list: MutableList<Drama>) {
        this.list = list
        notifyDataSetChanged()
    }
}