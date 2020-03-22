package com.assignment.repo.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.assignment.repo.R
import com.assignment.repo.pojo.Developers
import com.squareup.picasso.Picasso

class DeveloperAdapter: RecyclerView.Adapter<DeveloperAdapter.DeveloperViewHolder>(),Filterable {

    var developerList: ArrayList<Developers> = arrayListOf()
    var developerListFiltered: ArrayList<Developers> = arrayListOf()

    fun setDeveloperListData(developerList: List<Developers>){
        this.developerListFiltered = developerList as ArrayList<Developers>
        this.developerList = developerListFiltered
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

        return developerListFiltered.size
    }

    override fun onBindViewHolder(holder: DeveloperViewHolder, position: Int) {
        val data = developerListFiltered

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

    override fun getFilter(): Filter {
        return (object : Filter(){
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val charString = charSequence.toString()
                if(charString.isEmpty()){
                    developerListFiltered = developerList
                }else{
                    var filteredRepo = arrayListOf<Developers>()
                    for(i in 0 until developerList.size){
                        if(developerList[i].name?.toLowerCase()?.contains(charString) == true){
                            filteredRepo.add(developerList[i])
                        }
                    }

                    developerListFiltered = filteredRepo
                }
                val filterResult = FilterResults()
                filterResult.values = developerListFiltered
                return filterResult
            }

            override fun publishResults(charSequence: CharSequence?, results: FilterResults?) {
                developerListFiltered = results?.values as ArrayList<Developers>
                notifyDataSetChanged()
            }
        })
    }

    class DeveloperViewHolder(item: View): RecyclerView.ViewHolder(item){

        var imgAvatar = item.findViewById<ImageView>(R.id.img_avatar)
        var textName = item.findViewById<TextView>(R.id.tv_name)
        var textUserName = item.findViewById<TextView>(R.id.tv_username)
        var viewSeparater = item.findViewById<View>(R.id.view_separater)
    }
}