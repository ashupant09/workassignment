package com.assignment.repo.ui.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.assignment.repo.R
import com.assignment.repo.network.State
import com.assignment.repo.pojo.RepoList
import com.assignment.repo.ui.viewmodel.DeveloperViewModel

class DevelopersListFragment : Fragment() {

    lateinit var viewModel: DeveloperViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_developers_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.repositoryData.observe(viewLifecycleOwner, Observer {
                state->
            when(state){
                is State.isLoading ->
                    state.isLoading

                is State.onSuccess ->{
                    state.data is RepoList
                    state.isLoading
                }

                is State.onFailure ->{
                    state.data
                    state.isLoading
                }
            }
        })
    }


}
