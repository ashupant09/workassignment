package com.assignment.repo.ui.view


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.assignment.repo.R
import com.assignment.repo.di.ViewModelFactory
import com.assignment.repo.network.State
import com.assignment.repo.pojo.RepoList
import com.assignment.repo.ui.viewmodel.RepositoryViewModel
import com.assignment.repo.utils.AppUtil
import kotlinx.android.synthetic.main.fragment_repository_list.*

class RepositoryListFragment : Fragment() {

    private lateinit var viewModel: RepositoryViewModel
    private val repoAdapter: RepoAdapter by lazy {
        RepoAdapter(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repository_list, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.run {
            viewModel = ViewModelProvider(this, ViewModelFactory(activity)).get(RepositoryViewModel::class.java)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repo_recycler.apply {
            adapter = repoAdapter
            layoutManager = LinearLayoutManager(context)
        }
        viewModel.repositoryData.observe(viewLifecycleOwner, Observer {
            state->
            when(state){
                is State.isLoading ->
                    progress_layout.visibility = if(state.isLoading) View.VISIBLE else View.GONE

                is State.onSuccess ->{
                    if((state.data as RepoList).repoData!!.isNotEmpty())
                    repoAdapter.setrepoListData((state.data as RepoList).repoData!!)
                    progress_layout.visibility = if(state.isLoading) View.VISIBLE else View.GONE
                }

                is State.onFailure ->{
                    Toast.makeText(context, state.data as String, Toast.LENGTH_SHORT).show()
                    progress_layout.visibility = if(state.isLoading) View.VISIBLE else View.GONE
                }
            }
        })

        context?.run{
            if(AppUtil.isNetworkConnected(this)){
                viewModel.loadRepoData()
            }else{
                viewModel.repositoryData.value = State.onFailure("Internet not connected")
            }
        }

        et_search_repo.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(text: Editable?) {
                repoAdapter.filter.filter(text)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }


}
