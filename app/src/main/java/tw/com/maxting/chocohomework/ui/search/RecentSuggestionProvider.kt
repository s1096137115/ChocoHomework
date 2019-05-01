package tw.com.maxting.chocohomework.ui.search

import android.app.SearchManager
import android.content.ContentResolver
import android.content.Context
import android.content.SearchRecentSuggestionsProvider
import android.database.Cursor
import android.net.Uri
import tw.com.maxting.chocohomework.BuildConfig


class RecentSuggestionProvider : SearchRecentSuggestionsProvider() {
    init {
        setupSuggestions(AUTHORITY, MODE)
    }

    companion object {
        const val AUTHORITY = BuildConfig.APPLICATION_ID
        const val MODE: Int = SearchRecentSuggestionsProvider.DATABASE_MODE_QUERIES

        fun getRecentSuggestions(context: Context?, query: String, limit: Int = 0): Cursor? {
            val uriBuilder = Uri.Builder()
                    .scheme(ContentResolver.SCHEME_CONTENT)
                    .authority(AUTHORITY)

            uriBuilder.appendPath(SearchManager.SUGGEST_URI_PATH_QUERY)

            val selection = " ?"
            val selArgs = arrayOf(query)

            // the limit function not work...
            if (limit > 0) {
                uriBuilder.appendQueryParameter(
                        SearchManager.SUGGEST_PARAMETER_LIMIT, limit.toString())
            }

            val uri = uriBuilder.build()

//            val limit = uri.getQueryParameter(SearchManager.SUGGEST_PARAMETER_LIMIT)

            return context?.contentResolver?.query(uri, null, selection, selArgs, null)
        }

    }


}