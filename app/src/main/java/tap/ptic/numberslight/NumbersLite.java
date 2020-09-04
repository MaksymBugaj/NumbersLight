package tap.ptic.numberslight;

import android.app.Application;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import tap.ptic.numberslight.di.DaggerAppComponent;

public class NumbersLite extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
