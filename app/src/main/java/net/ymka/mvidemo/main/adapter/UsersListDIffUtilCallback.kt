package net.ymka.mvidemo.main.adapter

import android.support.v7.util.DiffUtil
import net.ymka.mvidemo.domain.entity.User

class UsersListDIffUtilCallback(private val oldList: List<User>,
                                private val newList: List<User>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
