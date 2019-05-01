package tw.com.maxting.chocohomework.ui.list

import androidx.recyclerview.widget.DiffUtil
import tw.com.maxting.chocohomework.data.Drama

class ListDiffUtil constructor(private val old: List<Drama>,
                               private val new: List<Drama>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return old.size
    }

    override fun getNewListSize(): Int {
        return new.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition].dramaId == new[newItemPosition].dramaId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] == new[newItemPosition]
    }
}