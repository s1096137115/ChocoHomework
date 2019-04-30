package tw.com.maxting.chocohomework.ui.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import tw.com.maxting.chocohomework.data.Drama
import tw.com.maxting.chocohomework.data.Repository

class InfoViewModel constructor(private val repository: Repository) : ViewModel() {
    private val mDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        mDisposable.clear()
    }

    val mDrama = MutableLiveData<Drama>()

    fun loadDramaById(id: Int) {
        repository
                .loadDramaById(id)
                .subscribeBy(
                        onNext = {
                            mDrama.postValue(it)
                        },
                        onError = {
                            val a = 1
                            //do nothing
                        }
                )
                .addTo(mDisposable)
    }

}