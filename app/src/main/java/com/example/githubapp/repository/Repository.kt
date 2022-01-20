package com.example.githubapp.repository

import com.example.githubapp.network.RetrofitInstance
import com.example.githubapp.network.RetrofitInstance.Companion.retrofitData

class Repository {
 suspend fun getUser(user:String)=RetrofitInstance.api.getUsers(user)
}