package tw.com.maxting.chocohomework.ui.list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_list.*
import tw.com.maxting.chocohomework.R
import tw.com.maxting.chocohomework.data.Repository
import tw.com.maxting.chocohomework.util.getViewModel

class ListFragment : Fragment() {

    private val adapter = ListAdapter()

    val viewModel by lazy {
        activity!!.getViewModel { ListViewModel(Repository.getInstance(activity!!.application)) }
    }

    companion object {
        fun newInstance() = ListFragment()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        recyclerView.adapter = adapter
        recyclerView.layoutAnimation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
    }

    private fun observeViewModel() {
        viewModel.apply {
            mDramas.observe(viewLifecycleOwner, Observer {
                adapter.update(it.toMutableList())
            })
        }
    }

}
