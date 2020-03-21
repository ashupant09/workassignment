package com.assignment.repo.network

import com.assignment.repo.pojo.DeveloperList
import com.assignment.repo.pojo.Developers
import com.assignment.repo.pojo.RepoList
import com.assignment.repo.pojo.Repositories
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface PostApi {

    @GET("/repositories")
    fun getRepository(): Observable<Response<AppResponse>>

    @GET("/developers")
    fun getDevelopers(): Observable<Response<RepoList>>
}