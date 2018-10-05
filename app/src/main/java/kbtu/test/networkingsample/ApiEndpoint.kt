package kbtu.test.networkingsample

import retrofit2.Call
import retrofit2.http.*

interface ApiEndpoint {

    @GET("todos/")
    fun getTodos(): Call<List<Todo>>

    @GET("todos/{id}")
    fun getTodo(@Path("id") id: Int): Call<List<Todo>>


    @FormUrlEncoded
    @POST("posts/")
    fun createPost(@Field("title") title: String,
                   @Field("body") body: String,
                   @Field("userId") userId: Int): Call<Post>

    @POST("posts/")
    fun createPost(@Body post: Post): Call<Post>


}