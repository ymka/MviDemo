package net.ymka.mvidemo.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import net.ymka.mvidemo.data.room.dao.UserDao
import net.ymka.mvidemo.data.room.entity.UserLocal

@Database(entities = [UserLocal::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}
