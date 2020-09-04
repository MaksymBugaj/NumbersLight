package tap.ptic.numberslight.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import tap.ptic.numberslight.NumbersActivity

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity() : NumbersActivity
}