package com.assignment.repo.di

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.assignment.repo.ui.viewmodel.DeveloperViewModel
import com.assignment.repo.ui.viewmodel.RepositoryViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val activity: Activity?): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RepositoryViewModel::class.java)){
            return RepositoryViewModel() as T
        }else if(modelClass.isAssignableFrom(DeveloperViewModel::class.java)){
            return DeveloperViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}