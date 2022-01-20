package com.example.githubapp

import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapp.adapter.UserAdapter
import com.example.githubapp.databinding.ActivityMainBinding
import com.example.githubapp.model.User
import com.example.githubapp.utils.Resource
import com.example.githubapp.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val viewModel: MainViewModel by lazy{
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

          binding.ivSearch.setOnClickListener {
            if (binding.edSearch.text!!.isNotEmpty()){
                viewModel.fetchUsers(binding.edSearch.text.toString())

            }
            else{
                Toast.makeText(this,"please enter user",Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.UsersLiveData.observe(this,{characters-> processResponse(characters) })
    }

    private fun processResponse(characters: Resource<List<User>?>?) {
        when(characters){
            is Resource.Loading->{
                binding.progress.visibility=VISIBLE
            }
            is Resource.Success->{
                binding.progress.visibility= View.GONE
                if (characters.data!=null){
                    val adapters= UserAdapter(characters.data)
                    binding.rvUsers.apply {
                        adapter=adapters
                        layoutManager=LinearLayoutManager(this@MainActivity)
                    }

                }

            }
            is Resource.Error->{
                binding.progress.visibility=View.GONE
                val view=binding.progress.rootView
                Snackbar.make(view, characters.message!!,Snackbar.LENGTH_LONG).show()

            }

    }
}

}