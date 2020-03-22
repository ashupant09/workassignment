package com.assignment.repo.ui.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.assignment.repo.R
import com.assignment.repo.pojo.Repositories
import com.squareup.picasso.Picasso

class RepoAdapter(private val context: Context?): RecyclerView.Adapter<RepoAdapter.RepoItemViewHolder>(), Filterable{

    var repoList: ArrayList<Repositories> = arrayListOf()
    var repoListFiltered: ArrayList<Repositories> = arrayListOf()

    fun setrepoListData(repoList: List<Repositories>){
        this.repoListFiltered = repoList as ArrayList<Repositories>
        this.repoList = repoListFiltered
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoItemViewHolder {

        return RepoItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.repo_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return repoListFiltered.size
    }

    override fun onBindViewHolder(holder: RepoItemViewHolder, position: Int) {
        val data = repoListFiltered

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

        holder.itemView.setOnClickListener {
            val bundle = Bundle().apply {
                putParcelable("repoData", data[position])
            }
            context?.run{
                this.startActivity(Intent(this, RepoDetailActivity::class.java).putExtras(bundle))
            }
        }

    }


    override fun getFilter(): Filter{
        return (object : Filter(){
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val charString = charSequence.toString()
                if(charString.isEmpty()){
                    repoListFiltered = repoList
                }else{
                    var filteredRepo = arrayListOf<Repositories>()
                    for(i in 0 until repoList.size){
                        if(repoList[i].author?.toLowerCase()?.contains(charString) == true){
                            filteredRepo.add(repoList[i])
                        }
                    }

                    repoListFiltered = filteredRepo
                }
                val filterResult = FilterResults()
                filterResult.values = repoListFiltered
                return filterResult
            }

            override fun publishResults(charSequence: CharSequence?, results: FilterResults?) {
                repoListFiltered = results?.values as ArrayList<Repositories>
                notifyDataSetChanged()
            }
        })
    }




    class RepoItemViewHolder(item: View): RecyclerView.ViewHolder(item){

        var imgAvatar = item.findViewById<ImageView>(R.id.img_avatar)
        var textAuthor = item.findViewById<TextView>(R.id.tv_author)
        var textName = item.findViewById<TextView>(R.id.tv_repo_name)
        var textLanguage = item.findViewById<TextView>(R.id.tv_repo_language)
        var viewSeparater = item.findViewById<View>(R.id.view_separater)

    }
}
