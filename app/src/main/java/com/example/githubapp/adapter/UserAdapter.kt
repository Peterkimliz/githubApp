package com.example.githubapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.R
import com.example.githubapp.databinding.ItemLayoutBinding
import com.example.githubapp.model.User
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class UserAdapter(val listData:List<User> ): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view=ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listData[position])

    }

    override fun getItemCount(): Int {
      return listData.size
    }

    inner class UserViewHolder(val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(users: User){
            val name=itemView.findViewById<TextView>(R.id.tv_user)
            val imageViewData=itemView.findViewById<CircleImageView>(R.id.iv_user)
            name.text=users.login
            Picasso.get().load(users.avatar_url).into(imageViewData)
        }

        }
}