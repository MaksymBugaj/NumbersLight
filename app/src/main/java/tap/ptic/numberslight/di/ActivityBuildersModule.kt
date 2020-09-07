package tap.ptic.numberslight.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import tap.ptic.numberslight.ui.NumbersActivity

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity() : NumbersActivity
}