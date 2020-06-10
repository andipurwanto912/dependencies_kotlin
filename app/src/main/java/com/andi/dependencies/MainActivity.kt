package com.andi.dependencies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.andi.dependencies.data.ResultUsers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContentView(R.layout.activity_main)
        NetworkConfig().getService().getUsers().enqueue(object : Callback<List<ResultUsers>> {
            override fun onFailure(call:
                                   Call<List<ResultUsers>>, t: Throwable) {
                Toast.makeText(this@MainActivity,
                    t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(
            call: Call<List<ResultUsers>>,
            response: Response<List<ResultUsers>>
            ){
                rvUser.adapter =
                    UsersAdapter(response.body())
            }
        })
    }
}
