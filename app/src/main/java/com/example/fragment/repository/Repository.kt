package com.example.fragment.repository

import com.example.fragment.network.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject


//this is for network source nad local data source

@ActivityRetainedScoped
class Repository @Inject constructor(remoteDataSource: RemoteDataSource,localDataSource: LocalDataSource){
    val remote=remoteDataSource
    val local=localDataSource
}