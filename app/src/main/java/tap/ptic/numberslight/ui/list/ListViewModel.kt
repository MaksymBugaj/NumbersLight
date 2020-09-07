package tap.ptic.numberslight.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import tap.ptic.numberslight.data.db.entity.NumberEntityItem
import tap.ptic.numberslight.data.repository.NumbersRepository
import javax.inject.Inject

class ListViewModel @Inject constructor(
    private val numbersRepository: NumbersRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _data = MutableLiveData<List<NumberEntityItem>>()
    val data: LiveData<List<NumberEntityItem>> = _data

    init {
        numbersRepository.getNumbers()
        getNumbers()
    }

    private fun getNumbers() {
        compositeDisposable.add(
            numbersRepository.numbers.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe {
                it?.let {
                    if (it.isNotEmpty())
                        _data.postValue(it)
                }
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
