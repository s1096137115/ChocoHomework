package tw.com.maxting.chocohomework.ui.list

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tw.com.maxting.chocohomework.R
import tw.com.maxting.chocohomework.data.Repository
import tw.com.maxting.chocohomework.util.getViewModel
import tw.com.maxting.chocohomework.util.openFragment

class ListActivity : AppCompatActivity() {

    val viewModel by lazy {
        getViewModel { ListViewModel(Repository.getInstance(application)) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        supportFragmentManager.openFragment(ListFragment.newInstance())

        viewModel.loadDramas()
        viewModel.fetchDramas()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        intent
                ?.takeIf { it.action == Intent.ACTION_SEARCH }
                ?.let { it.getStringExtra(SearchManager.QUERY) }
                ?.also { query ->
                    viewModel.saveRecentQuery(applicationContext, query)
                }
    }
}
