package net.ymka.mvidemo.data.retrofit

import net.ymka.mvidemo.data.api.UsersApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApiProvider : ApiProvider {

    companion object {
        private const val url = "https://api.github.com/"
    }

    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(RetrofitApiProvider.url)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    override val userApi: UsersApi = retrofit.create(UsersApi::class.java)

}
