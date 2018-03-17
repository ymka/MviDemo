package net.ymka.mvidemo.data.api

import io.reactivex.Single
import net.ymka.mvidemo.data.retrofit.entity.UserRemote
import retrofit2.http.GET

interface UsersApi {

   @GET("/users")
   fun allUsers(): Single<List<UserRemote>>

}
