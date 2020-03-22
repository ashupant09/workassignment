package com.assignment.repo.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.assignment.repo.R
import com.assignment.repo.pojo.Repositories
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_repo_detail.*

class RepoDetailActivity : AppCompatActivity() {

    lateinit var repositories: Repositories
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_detail)
        supportActionBar?.title = "Repo Detail"
        repositories = intent.extras.getParcelable("repoData")
        setData()
    }

    private fun setData(){
        repositories.avatar?.let {
            Picasso.get()
                .load(it)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round)
                .into(img_avatar);
        }

        repositories.author?.let{
            text_author.text = repositories.author
        }

        repositories.url?.let{
            text_url.text = repositories.url
        }

        repositories.description?.let {
            text_description.text = repositories.description
        }
    }
}
