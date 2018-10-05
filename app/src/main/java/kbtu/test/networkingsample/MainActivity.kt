package kbtu.test.networkingsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gson = GsonBuilder().create()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(interceptor)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient.build())
                .build()


        val apiEndpoint = retrofit.create(ApiEndpoint::class.java)

//        val call = apiEndpoint.getTodos()
//
//        call.enqueue(object : Callback<List<Todo>> {
//
//            override fun onResponse(call: Call<List<Todo>>?, response: Response<List<Todo>>?) {
//                val todos = response?.body()
//                Log.d("Todos: ", todos!![0].toString())
//                Log.d("Todos Size: ", todos.size.toString())
//            }
//
//            override fun onFailure(call: Call<List<Todo>>?, t: Throwable?) {
//                Log.e("Error: ", t?.message)
//            }
//
//        })


//        val call = apiEndpoint.getTodo(1)
//
//        call.enqueue(object : Callback<List<Todo>> {
//
//            override fun onResponse(call: Call<List<Todo>>?, response: Response<List<Todo>>?) {
//                val todo = response?.body()
//                setData(todo!![0])
//                Log.d("List<Todo: ", todo.toString())
//            }
//
//            override fun onFailure(call: Call<List<Todo>>?, t: Throwable?) {
//                Log.e("Error: ", t?.message)
//                showError(t!!)
//            }
//
//        })

        val post = Post(1, title = "Post Title", body = "Post Body")

        val call = apiEndpoint.createPost(post)

        call.enqueue(object : Callback<Post> {

            override fun onResponse(call: Call<Post>?, response: Response<Post>?) {
                val post = response?.body()
                setData(post!!)
                Log.d("Post: ", post.toString())
            }

            override fun onFailure(call: Call<Post>?, t: Throwable?) {
                Log.e("Error: ", t?.message)
                showError(t!!)
            }

        })
    }


    fun setData(post: Post) {
        todoText.text = post.title
    }


    fun showError(t: Throwable?) {
        Toast.makeText(this, t?.message, Toast.LENGTH_SHORT).show()
    }
}
