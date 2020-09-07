package tap.ptic.numberslight.ui.list

import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import tap.ptic.numberslight.data.db.entity.NumberDetails
import tap.ptic.numberslight.data.db.entity.NumberEntityItem
import tap.ptic.numberslight.data.repository.NumbersApiRepository
import javax.inject.Inject

class NumberDetailsViewModel @Inject constructor(
    private val numbersApiRepository: NumbersApiRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val selectedNumber = PublishSubject.create<NumberDetails>()

    val selectedNumberFromList = PublishSubject.create<NumberEntityItem>()

    fun getSpecificNumberInfo(name: String) {
        numbersApiRepository.getSpecificNumber(name)
        compositeDisposable.add(
            numbersApiRepository.downloadedNumber.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe { numberDetails ->
                    selectedNumber.onNext(numberDetails)
                }
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}