package tap.ptic.numberslight.data.repository

import io.reactivex.Flowable
import io.reactivex.Single
import tap.ptic.numberslight.data.db.entity.NumberEntityItem

interface NumbersApiRepository {

    fun getNumbers(): Flowable<List<NumberEntityItem>>

    fun getSpecificNumber(): Single<NumberEntityItem>
}