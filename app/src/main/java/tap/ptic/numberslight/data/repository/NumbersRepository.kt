package tap.ptic.numberslight.data.repository

import io.reactivex.subjects.PublishSubject
import tap.ptic.numberslight.data.db.entity.NumberEntityItem

interface NumbersRepository {

    val numbers: PublishSubject<List<NumberEntityItem>>

    fun getNumbers()
}