package kg.evo.RegUser;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("/auth/users/")
    Call<ResponseBody> createUser(
            @Field("first_name") String first_name,
            @Field("email") String email,
            @Field("password") String password
    );


}
