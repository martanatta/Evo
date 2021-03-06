package kg.evoschool.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @GET("categories/")
    Call<List<CategoryList>> getCategoryList(@Header("Authorization") String token);

    @GET("/categories/{id}/")
    Call<CategoryDetail> getCategoryDetail(@Header("Authorization") String token, @Path("id") int audioId);

    @POST("/auth/jwt/create/")
    Call<TokenRefresh> logIn(@Body TokenObtainPair tokenObtainPair);

//    @GET("/audios/")
//    Call<List<AudioListCategory>> getAllSongs();

//    @GET("/favorite-audios/")
//    Call<List<FavoriteAudioList>> getFavoriteAudioList();
}