package tap.ptic.numberslight.data.db.entity


import androidx.room.PrimaryKey

data class NumberEntityItem(
    val image: String,
    @PrimaryKey
    val name: String
)