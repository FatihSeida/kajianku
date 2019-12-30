package com.prplsd.kajianku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.prplsd.kajianku.adapter.KajiankuAdapter;
import com.prplsd.kajianku.data.Session;
import com.prplsd.kajianku.model.ListKajiankuResponse;

import static com.prplsd.kajianku.data.Constant.GET_LIST_KAJIANKU;
import static com.prplsd.kajianku.data.Constant.GET_SEARCH_KAJIANKU;

public class DashboardActivity extends AppCompatActivity {

    KajiankuAdapter adapter;
    RecyclerView rv;
    ProgressDialog progressDialog;
    Session session;
    SearchView svKajianku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        session = new Session(this);
        progressDialog = new ProgressDialog(this);
        initView();
        initRecyclerView();
        initSearch();
    }


    private void initSearch(){
        svKajianku.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                loadItem(GET_SEARCH_KAJIANKU + query);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                loadItem(GET_SEARCH_KAJIANKU + newText);
                return true;
            }
        });
        svKajianku.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                loadItem(GET_LIST_KAJIANKU);
                return false;
            }
        });
    }

    private void initView(){
        svKajianku = findViewById(R.id.sv_kajianku);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_logout:
                session.logoutUser();
                break;
            case R.id.menu_account:
                startActivity(new Intent(DashboardActivity.this, ProfileActivity.class));
                break;
        }
        return true;
    }

    public void initRecyclerView(){
        adapter = new KajiankuAdapter(this);
        loadItem(GET_LIST_KAJIANKU);
        rv = findViewById(R.id.rv_kajianku);
        rv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setNestedScrollingEnabled(false);
        rv.hasFixedSize();
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new KajiankuAdapter.OnItemClickListener(){
            @Override
            public void onClick(int position) {
                Toast.makeText(DashboardActivity.this, "Clicked Item - " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loadItem(String url){

        progressDialog.setMessage("Please Wait..");
        progressDialog.show();
        AndroidNetworking.get(url)
                .build()
                .getAsObject(ListKajiankuResponse.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        if(response instanceof ListKajiankuResponse){
                            progressDialog.dismiss();
                            if (((ListKajiankuResponse) response).getData() !=
                                    null && ((ListKajiankuResponse) response).getData().size() > 0){
                                adapter.swap(((ListKajiankuResponse)
                                        response).getData());
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Toast.makeText(DashboardActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                    }
                });
    }


}
