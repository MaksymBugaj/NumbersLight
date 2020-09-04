package tap.ptic.numberslight.data.repository

import android.util.Log
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import tap.ptic.numberslight.data.db.NumberDao
import tap.ptic.numberslight.data.db.entity.NumberEntityItem

class NumbersRepositoryImpl(
    private val numberDao: NumberDao,
    private val numbersApiRepository: NumbersApiRepository
) : NumbersRepository {

    private val compositeDisposable = CompositeDisposable()

    override fun insertNumber(number: NumberEntityItem) {
        compositeDisposable.add(
            Completable.fromAction {
                numberDao.insert(number)
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d("NOPE", "DONE")
                }
        )
    }

    override fun getNumbers(): Single<List<NumberEntityItem>> {
        numbersApiRepository.getNumbers()
        return numberDao.getAllNumbers()
    }
}