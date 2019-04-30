package tw.com.maxting.chocohomework.ui.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_drama.view.*
import tw.com.maxting.chocohomework.data.Drama

class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    lateinit var drama: Drama

    fun bind(drama: Drama) {
        this.drama = drama
        itemView.apply {
            tvName.text = drama.name
            tvRating.text = drama.rating.toString()
            tvCreatedAt.text = drama.createdAt

            Glide.with(this)
                .load(drama.thumb)
                .into(ivThumb)
        }
    }

}