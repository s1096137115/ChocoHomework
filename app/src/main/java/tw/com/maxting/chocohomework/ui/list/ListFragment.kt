package tw.com.maxting.chocohomework.ui.list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_list.*
import tw.com.maxting.chocohomework.R
import tw.com.maxting.chocohomework.data.Repository
import tw.com.maxting.chocohomework.ui.search.RecentSuggestionProvider
import tw.com.maxting.chocohomework.ui.search.SearchSuggestionAdapter
import tw.com.maxting.chocohomework.util.getViewModel
import tw.com.maxting.chocohomework.util.setupItemExpandAnimation


class ListFragment : Fragment() {

    private val adapter = ListAdapter()

    val viewModel by lazy {
        activity!!.getViewModel { ListViewModel(Repository.getInstance(activity!!.application)) }
    }

    val searchSuggestionAdapter by lazy {
        SearchSuggestionAdapter(context!!)
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
        setupToolbar()
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupToolbar() {
        toolbar.run {
            inflateMenu(R.menu.search)

            setupItemExpandAnimation(R.id.search)

            val searchItem = menu.findItem(R.id.search)

            val searchView = searchItem.actionView as SearchView

            searchView.suggestionsAdapter = searchSuggestionAdapter

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {

                    val cursor = RecentSuggestionProvider.getRecentSuggestions(context, newText, 2)

                    searchSuggestionAdapter.swapCursor(cursor)

                    when (newText.isEmpty()) {
                        true -> {
                            viewModel.mDramas.value
                                ?.also {
                                    adapter.update(it.toMutableList())
                                }
                        }
                        false -> {
                            viewModel.mDramas.value
                                ?.filter {
                                    it.name.contains(newText)
                                }
                                ?.also {
                                    adapter.update(it.toMutableList())
                                }
                        }
                    }
                    return false
                }
            })
        }
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
            mQuery.observe(viewLifecycleOwner, Observer {
                (toolbar.menu.findItem(R.id.search).actionView as SearchView).setQuery(it, false)
            })
        }
    }

}
