package tap.ptic.numberslight.di

import dagger.Module
import dagger.Provides
import tap.ptic.numberslight.data.network.NumbersApiService
import tap.ptic.numberslight.data.repository.NumbersApiRepository
import tap.ptic.numberslight.data.repository.NumbersApiRepositoryImpl
import tap.ptic.numberslight.data.repository.NumbersRepository
import tap.ptic.numberslight.data.repository.NumbersRepositoryImpl
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideNumbersApiRepository(numbersApiService: NumbersApiService): NumbersApiRepository {
        return NumbersApiRepositoryImpl(numbersApiService)
    }

    @Singleton
    @Provides
    fun provideNumbersRepository(numbersApiRepository: NumbersApiRepository): NumbersRepository {
        return NumbersRepositoryImpl(numbersApiRepository)
    }
}