package tap.ptic.numberslight.ui

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import tap.ptic.numberslight.R

class NumbersActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}