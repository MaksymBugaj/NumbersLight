package tap.ptic.numberslight.data.repository

import io.reactivex.subjects.PublishSubject
import tap.ptic.numberslight.data.db.entity.NumberDetails
import tap.ptic.numberslight.data.db.entity.NumberEntity

interface NumbersApiRepository {

    val downloadedData: PublishSubject<NumberEntity>
    val downloadedNumber: PublishSubject<NumberDetails>

    fun getNumbers()

    fun getSpecificNumber(name: String)
}