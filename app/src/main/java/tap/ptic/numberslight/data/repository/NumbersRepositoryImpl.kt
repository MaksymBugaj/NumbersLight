package tap.ptic.numberslight.data.repository

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import tap.ptic.numberslight.data.db.entity.NumberEntityItem
import timber.log.Timber

class NumbersRepositoryImpl(
    private val numbersApiRepository: NumbersApiRepository
) : NumbersRepository {

    private val compositeDisposable = CompositeDisposable()

    override val numbers: PublishSubject<List<NumberEntityItem>>
        get() = _numbersLoaded
    private val _numbersLoaded = PublishSubject.create<List<NumberEntityItem>>()

    override fun getNumbers() {
        numbersApiRepository.getNumbers()
        compositeDisposable.add(
            numbersApiRepository.downloadedData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe { numberEntity ->
                Timber.tag("NOPE").d("in subscribe we have data like: ${numberEntity.size}")
                if (!numberEntity.isEmpty()) {
                    _numbersLoaded.onNext(numberEntity)
                }
            }
        )
    }

}