package tw.com.maxting.chocohomework.ui.list

import android.content.Context
import android.provider.SearchRecentSuggestions
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import tw.com.maxting.chocohomework.data.Drama
import tw.com.maxting.chocohomework.data.Repository
import tw.com.maxting.chocohomework.ui.search.RecentSuggestionProvider

class ListViewModel constructor(private val repository: Repository) : ViewModel() {
    private val mDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        mDisposable.clear()
    }

    val mDramas = MutableLiveData<List<Drama>>()

    val mQuery = MutableLiveData<String>()

    fun loadDramas() {
        repository
                .loadDramasFromDb()
                .subscribeBy(
                        onNext = {
                            mDramas.postValue(it)
                        },
                        onError = {
                            //do nothing
                        }
                )
                .addTo(mDisposable)
    }

    fun fetchDramas() {
        repository
                .fetchDramasFromNetwork()
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onSuccess = {
                            saveDramas(it.data)
                        },
                        onError = {
                            //do nothing
                        }
                )
                .addTo(mDisposable)
    }

    private fun saveDramas(dramas: List<Drama>) {
        repository.saveDramas(dramas)
    }

    fun saveRecentQuery(context: Context, query: String) {
        SearchRecentSuggestions(context, RecentSuggestionProvider.AUTHORITY, RecentSuggestionProvider.MODE)
                .apply { saveRecentQuery(query, null) }

        mQuery.postValue(query)
    }

}