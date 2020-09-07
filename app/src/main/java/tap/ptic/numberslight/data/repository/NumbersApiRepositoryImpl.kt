package tap.ptic.numberslight.data.repository

import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import tap.ptic.numberslight.data.db.entity.NumberDetails
import tap.ptic.numberslight.data.db.entity.NumberEntity
import tap.ptic.numberslight.data.network.NumbersApiService
import timber.log.Timber

class NumbersApiRepositoryImpl(
    private val numbersApiService: NumbersApiService
) : NumbersApiRepository {

    private val compositeDisposable = CompositeDisposable()

    override val downloadedData: PublishSubject<NumberEntity>
        get() = downloadedList
    private val downloadedList = PublishSubject.create<NumberEntity>()

    override val downloadedNumber: PublishSubject<NumberDetails>
        get() = _downloadedNumber
    private val _downloadedNumber = PublishSubject.create<NumberDetails>()

    override fun getNumbers() {
        numbersApiService.getNumberList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .retry()
            .subscribe(object : SingleObserver<NumberEntity> {
                override fun onSuccess(t: NumberEntity) {
                    downloadedList.onNext(t)

                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Timber.tag("NOPE").d("Error during fetch API ${e.message}")
                }

            })

    }

    override fun getSpecificNumber(name: String) {
        numbersApiService.getNumberDetails(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<NumberDetails> {
                override fun onSuccess(t: NumberDetails) {
                    Timber.tag("NOPE").d("We have number API ${t}")
                    _downloadedNumber.onNext(t)
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Timber.tag("NOPE").e("Error during fetch name API ${e.message}")
                }

            })
    }
}