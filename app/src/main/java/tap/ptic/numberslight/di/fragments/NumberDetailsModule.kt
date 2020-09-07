package tap.ptic.numberslight.di.fragments

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import tap.ptic.numberslight.di.ViewModelKey
import tap.ptic.numberslight.ui.list.NumberDetailsFragment
import tap.ptic.numberslight.ui.list.NumberDetailsViewModel
import javax.inject.Singleton

@Module
abstract class NumberDetailsModule {

    @ContributesAndroidInjector
    abstract fun contributeNumberDetailsFragment(): NumberDetailsFragment


    @Binds
    @IntoMap
    @Singleton
    @ViewModelKey(NumberDetailsViewModel::class)
    abstract fun bindNumberDetailsViewModel(numberDetailsViewModel: NumberDetailsViewModel) : ViewModel
}