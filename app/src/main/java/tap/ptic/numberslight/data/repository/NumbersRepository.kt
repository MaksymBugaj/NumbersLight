package tap.ptic.numberslight.data.repository

import io.reactivex.Single
import tap.ptic.numberslight.data.db.entity.NumberEntityItem

interface NumbersRepository {

    fun insertNumber(number: NumberEntityItem)

    fun getNumbers(): Single<List<NumberEntityItem>>
}