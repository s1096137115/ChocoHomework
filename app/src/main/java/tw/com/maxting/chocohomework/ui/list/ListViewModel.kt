package tw.com.maxting.chocohomework.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import tw.com.maxting.chocohomework.data.Drama
import tw.com.maxting.chocohomework.data.Repository

class ListViewModel constructor(private val repository: Repository) : ViewModel() {
    private val mDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        mDisposable.clear()
    }

    val mDramas = MutableLiveData<List<Drama>>()

    fun loadDramas() {
        repository
                .loadDramasFromDb()
                .subscribeBy(
                        onNext = {
                            mDramas.postValue(it)
                        },
                        onError = {
                            val a = 1
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
                            val a = 1
                            //do nothing
                        }
                )
                .addTo(mDisposable)
    }

    private fun saveDramas(dramas: List<Drama>) {
        repository.saveDramas(dramas)
    }

}