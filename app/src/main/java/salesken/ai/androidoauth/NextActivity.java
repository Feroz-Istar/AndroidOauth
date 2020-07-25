package salesken.ai.androidoauth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.bloco.faker.Faker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NextActivity extends AppCompatActivity {
    private static final String TAG = "NextActivity";

    @BindView(R.id.swipeToRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recylcerview)
    RecyclerView recylcerview;
    MyRecyclerAdapter myRecyclerAdapter;
    List<DataPojo> dataPojos;
    public RestUrlInterface restUrlInterface;
    public SharedPreferences sharedpreferences;
    public SharedPreferences.Editor editor;
    public Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        ButterKnife.bind(this);
        sharedpreferences = getSharedPreferences(getResources().getString(R.string.shared_preference_key), Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        restUrlInterface = RestApiClient.getClient(NextActivity.this).create(RestUrlInterface.class);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d(TAG,"is ois refreshed");
                Call<SaleskenResponse> login =  restUrlInterface.fetchpolicy(sharedpreferences.getString("TOKEN",""));
                login.enqueue(new Callback<SaleskenResponse>() {
                    @Override
                    public void onResponse(Call<SaleskenResponse> call, Response<SaleskenResponse> response) {
                        Log.d(TAG,"Got response");
                        SaleskenResponse saleskenResponse = response.body();
                        try {
                            Log.d(TAG, gson.toJson(saleskenResponse.getResponse()));
                        }catch (JsonSyntaxException e){

                        }catch (Exception e){

                        }

                    }

                    @Override
                    public void onFailure(Call<SaleskenResponse> call, Throwable t) {

                    }
                });
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        dataPojos = new ArrayList<>();
        Faker faker = new Faker();

        for(int i=0;i<10;i++){
            DataPojo dataPojo = new DataPojo();
            dataPojo.setName(faker.name.firstName());        // "Aaron"
            dataPojo.setAge(faker.company.name());          // "Hirthe-Ritchie"
            dataPojos.add(dataPojo);
        }
        myRecyclerAdapter = new MyRecyclerAdapter(this,dataPojos);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recylcerview.setLayoutManager(mLayoutManager);
        recylcerview.setAdapter(myRecyclerAdapter);

    }
}