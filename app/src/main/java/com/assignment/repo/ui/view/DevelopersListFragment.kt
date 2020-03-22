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
import com.assignment.repo.pojo.DeveloperList
import com.assignment.repo.pojo.RepoList
import com.assignment.repo.ui.viewmodel.DeveloperViewModel
import kotlinx.android.synthetic.main.fragment_developers_list.*

class DevelopersListFragment : Fragment() {

    lateinit var viewModel: DeveloperViewModel
    private val developerAdapter: DeveloperAdapter by lazy {
        DeveloperAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.run {
            viewModel = ViewModelProvider(this, ViewModelFactory(activity)).get(DeveloperViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_developers_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        developer_recycler.apply {
            adapter = developerAdapter
            layoutManager = LinearLayoutManager(context)
        }
        viewModel.developerData.observe(viewLifecycleOwner, Observer {
                state->
            when(state){
                is State.isLoading ->
                    progress_layout.visibility = if(state.isLoading) View.VISIBLE else View.GONE

                is State.onSuccess ->{
                    if((state.data as DeveloperList).developerData!!.isNotEmpty())
                        developerAdapter.setDeveloperListData((state.data as DeveloperList).developerData!!)
                        progress_layout.visibility = if(state.isLoading) View.VISIBLE else View.GONE
                }

                is State.onFailure ->{
                    Toast.makeText(context, state.data as String, Toast.LENGTH_SHORT).show()
                    progress_layout.visibility = if(state.isLoading) View.VISIBLE else View.GONE
                }
            }
        })

        et_search_developer.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(text: Editable?) {
                developerAdapter.filter.filter(text)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }


}
