package tap.ptic.numberslight.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import tap.ptic.numberslight.data.db.entity.NumberEntityItem

@Dao
interface NumberDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(numberEntityItem: NumberEntityItem)
}