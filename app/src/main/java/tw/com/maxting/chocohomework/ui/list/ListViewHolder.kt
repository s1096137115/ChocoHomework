package tw.com.maxting.chocohomework.ui.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_drama.view.*
import org.jetbrains.anko.startActivity
import tw.com.maxting.chocohomework.data.Drama
import tw.com.maxting.chocohomework.ui.info.InfoActivity

class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    lateinit var mDrama: Drama

    init {
        itemView.setOnClickListener {
            it.context.startActivity<InfoActivity>(InfoActivity.DRAMA_ID to mDrama.dramaId)
        }
    }

    fun bind(drama: Drama) {
        this.mDrama = drama
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