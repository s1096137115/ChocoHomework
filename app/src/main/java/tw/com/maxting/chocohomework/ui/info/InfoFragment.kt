package tw.com.maxting.chocohomework.ui.info


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_info.*
import tw.com.maxting.chocohomework.R
import tw.com.maxting.chocohomework.data.Repository
import tw.com.maxting.chocohomework.util.getViewModel

class InfoFragment : Fragment() {

    val viewModel by lazy {
        activity!!.getViewModel { InfoViewModel(Repository.getInstance(activity!!.application)) }
    }

    companion object {
        fun newInstance() = InfoFragment()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeViewModel()
    }

    @SuppressLint("SetTextI18n")
    private fun observeViewModel() {
        viewModel.apply {
            mDrama.observe(viewLifecycleOwner, Observer { drama ->
                tvName.text = "名稱: " + drama.name
                tvRating.text = "評分: " + drama.rating.toString()
                tvCreatedAt.text = "出版日期: " + drama.createdAt
                tvTotalViews.text = "觀看次數: " + drama.totalViews.toString()

                Glide.with(this@InfoFragment)
                        .load(drama.thumb)
                        .into(ivThumb)
            })
        }
    }


}
