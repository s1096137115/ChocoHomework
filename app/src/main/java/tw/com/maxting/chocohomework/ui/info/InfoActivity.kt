package tw.com.maxting.chocohomework.ui.info

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import tw.com.maxting.chocohomework.R
import tw.com.maxting.chocohomework.util.openFragment


class InfoActivity : AppCompatActivity() {

    //    val viewModel by lazy {
//        getViewModel { InfoViewModel(Repository.getInstance(application)) }
//    }
    val viewModel: InfoViewModel by viewModel()

    companion object {
        const val DRAMA_ID = "drama_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        handleExtra()
        handleDeepLinks()

        supportFragmentManager.openFragment(InfoFragment.newInstance())
    }

    private fun handleExtra() {
        intent.getIntExtra(DRAMA_ID, -1)
            .takeIf { it > 0 }
            ?.also { id ->
                viewModel.loadDramaById(id)
            }
    }

    private fun handleDeepLinks() {
        intent?.data?.path
            ?.split("/drama/")
            ?.get(1)
            ?.toIntOrNull()
            ?.also {
                viewModel.loadDramaById(it)
            }
    }
}
