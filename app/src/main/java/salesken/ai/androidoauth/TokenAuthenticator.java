package salesken.ai.androidoauth;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Call;
/* Authenticator Interface will catch all http response in which http status code is 401 */
public class TokenAuthenticator implements Authenticator {
    private static final String TAG = "TokenAuthenticator";

    private Context context;
    public SharedPreferences sharedpreferences;

    public TokenAuthenticator(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public Request authenticate(@Nullable Route route, @NotNull Response response) throws IOException {
        int code =response.code();
        Log.d(TAG,"Code >>>>>>>>>> "+code);
        if(response.code() == 401){
            sharedpreferences = context.getSharedPreferences(context.getResources().getString(R.string.shared_preference_key), Context.MODE_PRIVATE);
            RestUrlInterface restUrlInterface = RestApiClient.getClient(context).create(RestUrlInterface.class);
            String exting_token = sharedpreferences.getString("REFRESHTOKEN","");
            Log.d(TAG,"exting_token >>>>>>>>>> "+exting_token);
            Call<SaleskenResponse> refres_respo =restUrlInterface.fetchRefreshToken(exting_token);
           retrofit2.Response<SaleskenResponse> saleskenResponse= refres_respo.execute();
            String newToken = saleskenResponse.headers().get("Authorization");
            Log.d(TAG,"newToken >>>>>>>>>> "+newToken);
            SharedPreferences.Editor  editor = sharedpreferences.edit();
            editor.putString("TOKEN",newToken);
            editor.commit();
            editor.apply();
            return response.request().newBuilder()
                    .header("Authorization", newToken)
                    .build();
        }
        return null;

    }
}
