package salesken.ai.androidoauth;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.internal.concurrent.Task;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;



public interface RestUrlInterface {


    @POST("api/v2/authenticate")
    Call<SaleskenResponse> autenticate(@Body UserPojo user);



    @GET("api/password_policy")
    Call<SaleskenResponse> fetchpolicy(@Header("Authorization") String token);

    @POST("api/v2/refresh/token")
    Call<SaleskenResponse> fetchRefreshToken(@Header("AuthorizationRefresh") String token);
/*
    @GET("v1/task/upcoming")
    Call<SaleskenResponse> upcoming(@Header("Authorization") String token);

    @GET("v1/task/recent")
    Call<SaleskenResponse> recent(@Header("Authorization") String token);

    @GET("v1/task/completed")
    Call<SaleskenResponse> completed(@Header("Authorization") String token);

    @GET("v1/task/create_task")
    Call<Integer> create_task(@Query("user") String user, @Query("toNumber") String toNumber);

    @POST("v1/password/forgot")
    Call<SaleskenResponse> forgot_password(@Body User user);

    @POST("v1/password/reset")
    Call<SaleskenResponse> reset_password(@Body User user);

    @POST("v1/password/change")
    Call<SaleskenResponse> change_password(@Header("Authorization") String token, @Body User user);


    @PUT("v1/user/update/user")
    Call<SaleskenResponse> updateUser(@Header("Authorization") String token, @Body User user);

    @Multipart
    @POST("v1/user/image/upload")
    Call<SaleskenResponse> uploadImage(@Header("Authorization") String token, @Part MultipartBody.Part file, @Part("file") RequestBody requestBody);

    @POST("v1/task/dispostion")
    Call<SaleskenResponse> disposition(@Header("Authorization") String token, @Body TaskSubmission taskSubmission);

    @GET("v1/task/stages_list/{task_id}")
    Call<SaleskenResponse> stage_list(@Header("Authorization") String token, @Path("task_id") Integer task_id);


    @POST("v1/user/issues")
    Call<SaleskenResponse> issues(@Header("Authorization") String token, @Body SaleskenIssue saleskenIssue);

    @POST("v1/user/update/fcm")
    Call<SaleskenResponse> update_fcm(@Header("Authorization") String token, @Body User user);

    @POST("v1/task/start")
    Call<SaleskenResponse> task_start(@Header("Authorization") String token, @Body Task task);

    @POST("v1/task/end")
    Call<SaleskenResponse> task_end(@Header("Authorization") String token, @Body Task task);

    @GET("v1/lead")
    Call<SaleskenResponse> leads(@Header("Authorization") String token);

    @POST("v1/lead")
    Call<SaleskenResponse> create_lead(@Header("Authorization") String token, @Body Lead lead);

    @POST("v1/validate/phone")
    Call<SaleskenResponse> phone_validate(@Header("Authorization") String token, @Body User user);

    @POST("v1/proxy/call")
    Call<SaleskenResponse> proxy_call(@Header("Authorization") String token, @Body User user);

    @POST("v1/user/update/sip")
    Call<SaleskenResponse> sip_reg(@Header("Authorization") String token, @Body User user);

    @GET("v1/user/view/user")
    Call<SaleskenResponse> update_profile(@Header("Authorization") String token, @Header("release") String release,
                                          @Header("model") String model,
                                          @Header("product") String product,
                                          @Header("display") String display,
                                          @Header("brand") String brand,
                                          @Header("host") String host,
                                          @Header("build_id") String build_id,
                                          @Header("manufacturer") String manufacturer,
                                          @Header("hardware") String hardware,
                                          @Header("cpu_abi") String cpu_abi,
                                          @Header("client_id") String client_id);

    @POST("authenticate/google")
    Call<SaleskenResponse> google_signin(@Body User user, @Header("release") String release,
                                         @Header("model") String model,
                                         @Header("product") String product,
                                         @Header("display") String display,
                                         @Header("brand") String brand,
                                         @Header("host") String host,
                                         @Header("build_id") String build_id,
                                         @Header("manufacturer") String manufacturer,
                                         @Header("hardware") String hardware,
                                         @Header("cpu_abi") String cpu_abi,
                                         @Header("client_id") String client_id);

    @GET("v1/task/lead/{task_id}")
    Call<SaleskenResponse> task_details(@Header("Authorization") String token, @Path("task_id") Integer task_id);*/

}