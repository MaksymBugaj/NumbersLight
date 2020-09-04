package tap.ptic.numberslight.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single
import tap.ptic.numberslight.data.db.entity.NumberEntityItem

@Dao
interface NumberDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(numberEntityItem: NumberEntityItem)

    @Query("select * from number_table")
    fun getAllNumbers(): Single<List<NumberEntityItem>>

    @Query("select * from number_table where name= :name")
    fun getSelectedNumber(name: String): Single<NumberEntityItem>
}