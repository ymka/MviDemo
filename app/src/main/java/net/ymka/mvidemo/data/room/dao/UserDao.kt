package net.ymka.mvidemo.data.room.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Single
import net.ymka.mvidemo.data.room.entity.UserLocal

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll(): Single<List<UserLocal>>

    @Insert
    fun insertAll(users: List<UserLocal>)

}
