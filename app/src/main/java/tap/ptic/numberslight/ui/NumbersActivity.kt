package tap.ptic.numberslight.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import tap.ptic.numberslight.R
import tap.ptic.numberslight.util.showDialog
import timber.log.Timber
import java.util.concurrent.TimeUnit

class NumbersActivity : DaggerAppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        compositeDisposable.add(
            Observable.interval(0, 10, TimeUnit.SECONDS).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).flatMap { isInternetOn(this) }
                .subscribe {
                    if (!it) {
                        Timber.tag("NOPE").d("We have no interneeeeet!!!!1")
                        showDialog(this)
                    }
                }
        )
    }

    private fun isInternetOn(context: Context): Observable<Boolean>? {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return Observable.just(activeNetworkInfo != null && activeNetworkInfo.isConnected)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}