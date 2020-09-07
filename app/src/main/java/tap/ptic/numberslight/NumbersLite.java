package tap.ptic.numberslight;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import tap.ptic.numberslight.di.DaggerAppComponent;
import timber.log.Timber;

public class NumbersLite extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        return DaggerAppComponent.builder().application(this).build();
    }
}
