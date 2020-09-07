package tap.ptic.numberslight.di.fragments

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import tap.ptic.numberslight.di.ViewModelKey
import tap.ptic.numberslight.ui.list.ListFragment
import tap.ptic.numberslight.ui.list.ListViewModel

@Module
abstract class ListNumbersModule {

    @ContributesAndroidInjector
    abstract fun contributeListFragment(): ListFragment


    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    abstract fun bindListViewModel(listViewModel: ListViewModel) : ViewModel
}