package com.eegrab.recyclerretrofitpractice

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eegrab.recyclerretrofitpractice.databinding.ItemUserBinding
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<UserData>() {
        override fun areItemsTheSame(oldItem: UserData, newItem: UserData): Boolean {
                return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserData, newItem: UserData): Boolean {
            return oldItem == newItem
        }
    }

    private  val differ = AsyncListDiffer(this,diffCallback)
    var userList: List<UserData>
        get() = differ.currentList
        set(value) {differ.submitList(value)}

    override fun getItemCount() = userList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
            return UserViewHolder(ItemUserBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
        false
            ))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user =  userList[position]
        holder.binding.apply {
            tvUserId.text = user.userId.toString()
            tvId.text = user.id.toString()
            tvTitle.text = user.title
        }
        holder.itemView.userView.setOnClickListener {
            Toast.makeText(holder.itemView.context,"title is "+user.title, Toast.LENGTH_SHORT).show()
        }
    }
}