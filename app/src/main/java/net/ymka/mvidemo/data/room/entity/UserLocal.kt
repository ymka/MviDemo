package net.ymka.mvidemo.data.room.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "user")
class UserLocal(
        @PrimaryKey(autoGenerate = true)
        var id: Long?,
        @ColumnInfo(name = "name")
        var name: String
)
