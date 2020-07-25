package salesken.ai.androidoauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.login)
    Button login;
    public RestUrlInterface restUrlInterface;
    public SharedPreferences sharedpreferences;
    public SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        sharedpreferences = getSharedPreferences(getResources().getString(R.string.shared_preference_key), Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        username.setText("feroz@salesken.ai");
        password.setText("Salesken@123");
        restUrlInterface = RestApiClient.getClient(MainActivity.this).create(RestUrlInterface.class);

    }

    @OnClick(R.id.login)
    public void login(){
        Call<SaleskenResponse> login = restUrlInterface.autenticate(new UserPojo(username.getText().toString(),password.getText().toString()));
        login.enqueue(new Callback<SaleskenResponse>() {
            @Override
            public void onResponse(Call<SaleskenResponse> call, Response<SaleskenResponse> response) {
                String header = response.headers().get("authorization");
                String refresh_token= response.headers().get("refresh_token");
                Log.d(TAG,"Header ................................. "+header);
                Log.d(TAG,"refresh_token ................................. "+refresh_token);

                editor.putString("TOKEN",header);
                editor.putString("REFRESHTOKEN",refresh_token);

                editor.commit();
                editor.apply();
                startActivity(new Intent(MainActivity.this,NextActivity.class));

            }

            @Override
            public void onFailure(Call<SaleskenResponse> call, Throwable t) {

            }
        });
    }

}