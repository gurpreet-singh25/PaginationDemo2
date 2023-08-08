package com.example.paginationdemo2;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<Result> arrayList;
    private static final int pageSize = 50;
    private int currentPage = 0;
    private Response<List<Result>> result ;

    public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
        if(isScrollable()){
            int startPosition = currentPage * pageSize;
            for (int i = startPosition; i < startPosition + pageSize; i++) {
                //response.body().getId();
                Result result = new Result();
                result.setId(response.body().get(i).getId());
                result.setAlbumId(response.body().get(i).getAlbumId());
                result.setTitle(response.body().get(i).getTitle());
                result.setUrl(response.body().get(i).getUrl());
                result.setThumbnailUrl(response.body().get(i).getThumbnailUrl());
                arrayList.add(result);
            }
            currentPage++;

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
            rv.setLayoutManager(linearLayoutManager);

        }
    }

    public boolean isScrollable(){
        RvAdapter rvAdapter = new RvAdapter(MainActivity.this, arrayList);
        rv.setAdapter(rvAdapter);
        return rvAdapter.getItemCount() > currentPage * pageSize;
    }
    APIInterface apiInterface = APPClient.getclient().create(APIInterface.class);
    Call<List<Result>> call = apiInterface.getdata();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                onResponse(call,result);
            }
        });
        arrayList = new ArrayList<>();
        call.enqueue(new Callback<List<Result>>() {
            @Override
            public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                result= response;
            }

            @Override
            public void onFailure(Call<List<Result>> call, Throwable t) {
            }

        });


    }
}