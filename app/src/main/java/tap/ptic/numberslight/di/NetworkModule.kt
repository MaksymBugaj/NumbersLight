package tap.ptic.numberslight.di

import android.app.Application
import dagger.Module
import dagger.Provides
import tap.ptic.numberslight.data.network.ConnectivityInterceptor
import tap.ptic.numberslight.data.network.ConnectivityInterceptorImpl
import tap.ptic.numberslight.data.network.NumbersApiService
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideConnectivityInterceptor(application: Application): ConnectivityInterceptor {
        return ConnectivityInterceptorImpl(application)
    }

    @Provides
    @Singleton
    fun provideNumbersApiService(connectivityInterceptor: ConnectivityInterceptor): NumbersApiService{
        return NumbersApiService.invoke(connectivityInterceptor)
    }
}