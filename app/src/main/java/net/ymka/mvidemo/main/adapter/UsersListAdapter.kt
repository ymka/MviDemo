package net.ymka.mvidemo.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import net.ymka.mvidemo.R
import net.ymka.mvidemo.domain.entity.User

class UsersListAdapter(private val itemClickListener: ItemClickListener) : RecyclerView.Adapter<UsersListAdapter.UserHolder>() {

    var list = emptyList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserHolder(itemClickListener, view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.userName.text = list[position].name
    }

    class UserHolder(private val itemClickListener: ItemClickListener,
                     view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    itemClickListener.onItemClicked(adapterPosition)
                }
            }
        }

        val userName: TextView = view.findViewById(R.id.userNameView)
    }

}
