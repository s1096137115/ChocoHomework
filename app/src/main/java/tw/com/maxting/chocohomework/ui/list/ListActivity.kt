package tw.com.maxting.chocohomework.ui.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import tw.com.maxting.chocohomework.R
import tw.com.maxting.chocohomework.data.Repository
import tw.com.maxting.chocohomework.util.getViewModel

class ListActivity : AppCompatActivity() {

    val viewModel by lazy {
        getViewModel { ListViewModel(Repository.getInstance(application)) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        openFragment(ListFragment.newInstance())

        viewModel.loadDramas()
        viewModel.fetchDramas()
    }

    fun openFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commitAllowingStateLoss()
    }
}
