package tap.ptic.numberslight.data.repository

import io.reactivex.Flowable
import io.reactivex.Single
import tap.ptic.numberslight.data.db.entity.NumberEntityItem

class NumbersApiRepositoryImpl : NumbersApiRepository {
    override fun getNumbers(): Flowable<List<NumberEntityItem>> {
        TODO("Not yet implemented")
    }

    override fun getSpecificNumber(): Single<NumberEntityItem> {
        TODO("Not yet implemented")
    }
}