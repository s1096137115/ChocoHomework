package tw.com.maxting.chocohomework.ui.search

import android.app.SearchManager
import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cursoradapter.widget.CursorAdapter
import tw.com.maxting.chocohomework.R

class SearchSuggestionAdapter(context: Context, c: Cursor? = null, flags: Int = 0) : CursorAdapter(context, c, flags) {

    override fun newView(context: Context, cursor: Cursor, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(
                R.layout.item_search_suggestion, parent, false)

        val viewHolder = ViewHolder(view)
        view.tag = viewHolder

        return view
    }

    override fun bindView(view: View, context: Context, cursor: Cursor) {
        val viewHolder = view.tag as ViewHolder
        viewHolder.mTitle.text = cursor.getString(cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1))
    }

    fun getSuggestionText(position: Int): String? {
        if (position >= 0 && position < cursor.count) {
            val cursor = cursor
            cursor.moveToPosition(position)
            return cursor.getString(cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1))
        }
        return null
    }

    class ViewHolder(view: View) {
        var mIcon: ImageView = view.findViewById<View>(R.id.iv_suggestion_item_icon) as ImageView
        var mTitle: TextView = view.findViewById<View>(R.id.tv_suggestion_item_title) as TextView
    }

    companion object {
        private val LOG_TAG = SearchSuggestionAdapter::class.java.simpleName
    }
}
