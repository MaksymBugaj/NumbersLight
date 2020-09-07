package tap.ptic.numberslight.data.network

import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import tap.ptic.numberslight.data.db.entity.NumberDetails
import tap.ptic.numberslight.data.db.entity.NumberEntity
import timber.log.Timber


interface NumbersApiService {

    @GET("json.php")
    fun getNumberList(): Single<NumberEntity>

    @GET("json.php/")
    fun getNumberDetails(
        @Query("name") name: String
    ): Single<NumberDetails>


    companion object{
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): NumbersApiService {
            val requestInterceptor = Interceptor { chain ->

                val url = chain.request()
                    .url
                    .newBuilder()
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Timber.tag("OkHttp").d(message)
                }
            })
            logging.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .addInterceptor(logging)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://dev.tapptic.com/test/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NumbersApiService::class.java)
        }
    }
}