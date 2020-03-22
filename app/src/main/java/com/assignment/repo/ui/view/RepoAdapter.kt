package com.assignment.repo.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.assignment.repo.R
import com.assignment.repo.pojo.Repositories
import com.squareup.picasso.Picasso

class RepoAdapter: RecyclerView.Adapter<RepoAdapter.RepoItemViewHolder>(){

    var repoList: ArrayList<Repositories> = arrayListOf()

    fun setrepoListData(repoList: List<Repositories>){
        this.repoList = repoList as ArrayList<Repositories>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoItemViewHolder {

        return RepoItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.repo_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    override fun onBindViewHolder(holder: RepoItemViewHolder, position: Int) {
        val data = repoList

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
        data[position].author?.let {
            holder.textAuthor.text = it
        }
        data[position].language?.let {
            holder.textLanguage.text = it
        }

        if(position == data.size-1){
            holder.viewSeparater.visibility = View.GONE
        }else{
            holder.viewSeparater.visibility = View.VISIBLE
        }

    }


    class RepoItemViewHolder(item: View): RecyclerView.ViewHolder(item){

        var imgAvatar = item.findViewById<ImageView>(R.id.img_avatar)
        var textAuthor = item.findViewById<TextView>(R.id.tv_author)
        var textName = item.findViewById<TextView>(R.id.tv_repo_name)
        var textLanguage = item.findViewById<TextView>(R.id.tv_repo_language)
        var viewSeparater = item.findViewById<View>(R.id.view_separater)

    }
}
