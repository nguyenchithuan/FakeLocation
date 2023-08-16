package edu.wkd.fakelocation.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import edu.wkd.fakelocation.models.obj.ChangeBackground;
import edu.wkd.fakelocation.models.request.ForgotPassRequest;
import edu.wkd.fakelocation.models.request.LoginRequest;
import edu.wkd.fakelocation.models.request.RegisterRequest;
import edu.wkd.fakelocation.models.response.ForgotPassResponse;
import edu.wkd.fakelocation.models.response.LoginResponse;
import edu.wkd.fakelocation.models.response.RegisterResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://14.225.7.221:3002/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("register")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);

    @POST("resetpass")
    Call<ForgotPassResponse> forgetPassword(@Body ForgotPassRequest forgotPassRequest);

    @GET("list_change_background")
    Call<List<ChangeBackground>> listChangeBackground();
}
