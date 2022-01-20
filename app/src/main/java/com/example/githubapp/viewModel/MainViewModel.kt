package com.example.githubapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapp.model.User
import com.example.githubapp.repository.Repository
import com.example.githubapp.utils.Resource

import kotlinx.coroutines.launch

class MainViewModel(val repository: Repository = Repository()) : ViewModel() {

    private var _UsersLiveData = MutableLiveData<Resource<List<User>?>>()
    val UsersLiveData: LiveData<Resource<List<User>?>> get() = _UsersLiveData

    fun fetchUsers(user:String) {
        _UsersLiveData.postValue(Resource.Loading())
        viewModelScope.launch {
            try {
                val client = repository.getUser(user)
                _UsersLiveData.postValue(Resource.Success(client.items))
            } catch (e: Exception) {

                _UsersLiveData.postValue(Resource.Error(e.message!!, null))
            }


        }


    }
}

