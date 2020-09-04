package tap.ptic.numberslight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity

class NumbersActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}