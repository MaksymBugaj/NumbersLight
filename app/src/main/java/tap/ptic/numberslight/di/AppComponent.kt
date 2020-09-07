package tap.ptic.numberslight.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import tap.ptic.numberslight.NumbersLite
import tap.ptic.numberslight.di.fragments.ListNumbersModule
import tap.ptic.numberslight.di.fragments.NumberDetailsModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        AppModule::class,
        ViewModelFactoryModule::class,
        NetworkModule::class,
        ListNumbersModule::class,
        NumberDetailsModule::class
    ]
)
interface AppComponent : AndroidInjector<NumbersLite> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}