package com.assignment.repo.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.assignment.repo.R
import com.assignment.repo.pojo.Developers
import com.squareup.picasso.Picasso

class DeveloperAdapter: RecyclerView.Adapter<DeveloperAdapter.DeveloperViewHolder>() {

    var developerList: ArrayList<Developers> = arrayListOf()

    fun setDeveloperListData(developerList: List<Developers>){
        this.developerList = developerList as ArrayList<Developers>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeveloperViewHolder {

        return DeveloperAdapter.DeveloperViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.developer_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {

        return developerList.size
    }

    override fun onBindViewHolder(holder: DeveloperViewHolder, position: Int) {
        val data = developerList

        data[position].avatar?.let {
            Picasso.get()
                .load(it)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round)
                .into(holder.imgAvatar);
        }

        data[position].name?.let {
            holder.textName.text = it
        }
        data[position].username?.let {
            holder.textUserName.text = it
        }

        if(position == data.size-1){
            holder.viewSeparater.visibility = View.GONE
        }else{
            holder.viewSeparater.visibility = View.VISIBLE
        }
    }

    class DeveloperViewHolder(item: View): RecyclerView.ViewHolder(item){

        var imgAvatar = item.findViewById<ImageView>(R.id.img_avatar)
        var textName = item.findViewById<TextView>(R.id.tv_name)
        var textUserName = item.findViewById<TextView>(R.id.tv_username)
        var viewSeparater = item.findViewById<View>(R.id.view_separater)
    }
}