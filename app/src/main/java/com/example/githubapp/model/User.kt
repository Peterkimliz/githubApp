package com.example.githubapp.model
data class UserResponse(val items:List<User>)
data class User(
    val login:String,
    val id:Int,
    val avatar_url:String
)
