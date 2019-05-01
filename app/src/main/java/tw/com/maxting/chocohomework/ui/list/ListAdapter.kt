package tw.com.maxting.chocohomework.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import tw.com.maxting.chocohomework.R
import tw.com.maxting.chocohomework.data.Drama

class ListAdapter : RecyclerView.Adapter<ListViewHolder>() {
    var mList = mutableListOf<Drama>()
    private var recyclerView: RecyclerView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_drama, parent, false))
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = null
    }

    private fun loadingAnimation() {
        recyclerView?.scheduleLayoutAnimation()
    }

    fun update(list: MutableList<Drama>, useAnimate: Boolean = true) {
        if (this.mList.isEmpty()) {
            this.mList = list
            notifyDataSetChanged()
            if (useAnimate) loadingAnimation()
        } else {
            val result = DiffUtil.calculateDiff(ListDiffUtil(this.mList, list))
            result.dispatchUpdatesTo(this)
            this.mList = list
        }
    }
}