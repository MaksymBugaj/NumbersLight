package tap.ptic.numberslight.data.db.entity


import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "number_table")
data class NumberEntityItem(
    val image: String,
    val name: String
)