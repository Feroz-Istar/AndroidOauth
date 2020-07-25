package salesken.ai.androidoauth;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;

public class CustomInterceptor implements Interceptor {
    private static final String TAG = "CustomInterceptor";
    private Context context;
    public SharedPreferences sharedpreferences;

    public CustomInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        // try the request
        Response response = chain.proceed(request);
        int code =response.code();
        Log.d(TAG,"Code >>>>>>>>>> "+code);
        if (code == 401) {
            Log.d(TAG,"Iam here ........ee");

            sharedpreferences = context.getSharedPreferences(context.getResources().getString(R.string.shared_preference_key), Context.MODE_PRIVATE);






            RestUrlInterface restUrlInterface = RestApiClient.getClient(context).create(RestUrlInterface.class);
            String exting_token = sharedpreferences.getString("REFRESHTOKEN","");
            Log.d(TAG,"exting_token >>>>>>>>>> "+exting_token);

           Request k= request.newBuilder()
                    .addHeader("AuthorizationRefresh", exting_token)
                    .url(context.getResources().getString(R.string.server_ip)+"api/v2/refresh/token")
                    .build();


            String newToken = k.headers().get("Authorization");
            Log.d(TAG,"newToken >>>>>>>>>> "+newToken);

            SharedPreferences.Editor  editor = sharedpreferences.edit();
            editor.putString("TOKEN",newToken);
            editor.commit();
            editor.apply();

            // restUrlInterface.fetchRefreshToken()
            // get a new token (I use a synchronous Retrofit call)

            // create a new request and modify it accordingly using the new token
            //Request newRequest = request.newBuilder().addHeader("",)
            // retry the request

            Request modifiedRequest = request.newBuilder()
                    .addHeader("Authorization", newToken)
                    .build();
            return chain.proceed(modifiedRequest);
        }

        // otherwise just pass the original response on
        return response;
    }

}