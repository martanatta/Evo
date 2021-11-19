package kg.evoschool;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kg.evoschool.Api.ApiService;
import kg.evoschool.Api.CategoryList;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainFragment extends Fragment {
    View v;
    RecyclerView recyclerView;
    static List<CategoryList> mList;
    MainAdapter listItemAdapter;
    SharedPreferences settings;
    String prefsFiles = "Account";
    String token = "";
//    static int globalID;
//    static List<Integer> MedID;

    public MainFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_main, container, false);
        return v;
    }


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("MainFragment", "onViewCreated");
        recyclerView = v.findViewById(R.id.listItemRecyclerView);

        mList = new ArrayList<>();
        TextView nameSP = v.findViewById(R.id.text_name_SP);
        nameSP.setText(EditProfileActivity.Name);

        getData();
        listItemAdapter = new MainAdapter(mList, getContext());
        recyclerView.setAdapter(listItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ItemClickListener listener = new ItemClickListener() {
            @Override
            public void onClick(int position) {
                CategoryList item = mList.get(position);

                Intent intent = new Intent(getActivity(), SoundsActivity.class);
                intent.putExtra("id", item.id);
                startActivity(intent);
                Log.e("onResponse", "code: " + item.id);
            }
        };
        listItemAdapter.setOnItemClickListener(listener);
    }

    public void getData() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://143.198.111.199/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        settings = getContext().getSharedPreferences(prefsFiles, MODE_PRIVATE);
        token = settings.getString("token", "token");
        Log.e("Настоящий токен", token);


        ApiService api = retrofit.create(ApiService.class);
        api.getCategoryList("JWT " + token).enqueue(new Callback<List<CategoryList>>() {
            @Override
            public void onResponse(Call<List<CategoryList>> call, Response<List<CategoryList>> response) {
                Log.e("onResponse main", "code: " + response.code());
                Log.e("onResponse main", "string: " + response.toString());
                Log.e("token main", token);
                interceptor.level(HttpLoggingInterceptor.Level.BODY);
                if (response.isSuccessful()) {
                    initData(response.body());

                } else {
                    Log.e("not successful", "fail");
                }
            }

            @Override
            public void onFailure(Call<List<CategoryList>> call, Throwable t) {
                Log.e("onResponse", t.toString());
            }
        });
    }

    private void initData(List<CategoryList> body) {
        mList.clear();
        mList.addAll(body);
        listItemAdapter.notifyDataSetChanged();
    }
}