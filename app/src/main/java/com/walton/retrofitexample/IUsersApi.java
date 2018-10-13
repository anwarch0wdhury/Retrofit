package com.walton.retrofitexample;



import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IUsersApi {
    @Headers("Content-Type: application/json")
    @POST("/path/filename")
    Call<User> getUser(@Body String login);

}
