package edu.wkd.fakelocation.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import edu.wkd.fakelocation.models.obj.Categories;
import edu.wkd.fakelocation.models.obj.Comment;
import edu.wkd.fakelocation.models.obj.Picture;
import edu.wkd.fakelocation.models.obj.Location;
import edu.wkd.fakelocation.models.obj.Profile;
import edu.wkd.fakelocation.models.request.CommentRequest;
import edu.wkd.fakelocation.models.request.ForgotPassRequest;
import edu.wkd.fakelocation.models.request.LoginRequest;
import edu.wkd.fakelocation.models.request.RegisterRequest;
import edu.wkd.fakelocation.models.response.CommentDeleteReponse;
import edu.wkd.fakelocation.models.response.CommentResponse;
import edu.wkd.fakelocation.models.response.ForgotPassResponse;
import edu.wkd.fakelocation.models.response.ListUserResponse;
import edu.wkd.fakelocation.models.response.LoginResponse;
import edu.wkd.fakelocation.models.response.RegisterResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://14.225.7.221:3005/")
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
    Call<List<Picture>> listLatestPictures();

    @GET("list_new_users")
    Call<ListUserResponse> listNewUsers();

    @GET("api/locations")
    Call<List<Location>> listLocation(@Query("page") int page);

    @GET("profile/list_fakelocation/{idUser}")
    Call<List<Picture>> listYourPictures(@Header("Authorization") String authorization,
                                         @Path("idUser") int idUser);

    @GET("profile/{idUser}")
    Call<Profile> profileUser(@Header("Authorization") String authorization,
                              @Path("idUser") int idUser);

    @GET("api/categories")
    Call<Categories> listCategories();

    @GET("get_comments/{idImage}")
    Call<List<Comment>> listComment(@Path("idImage") int idImage);

    @POST("post_comments")
    Call<CommentResponse> postComment(@Header("Authorization") String authorization,
                                      @Body CommentRequest commentRequest);

    @DELETE("delete_comment/{idComment}")
    Call<CommentDeleteReponse> deleteComment(@Header("Authorization") String authorization,
                                             @Path("idComment") int idComment);
}
