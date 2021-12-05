package com.arash.altafi.swiperefresh.java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.arash.altafi.swiperefresh.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainJava extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipe;
    private final ArrayList<Users> users = new ArrayList<>();
    private AdapterUser adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        init();
    }

    private void init() {
        bindViews();

        users.add(new Users("Arash" , "Altafi"));
        users.add(new Users("Jafar" , "Jafari"));
        users.add(new Users("Reza" , "Sadeghi"));

        adapter = new AdapterUser(users);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this , RecyclerView.VERTICAL , false));

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe.setColorSchemeColors(Color.RED , Color.BLUE , Color.GREEN , Color.YELLOW);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        swipe.setRefreshing(false);
                    }
                } , 4000);
            }
        });

        int helper = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, helper) {
            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
                Toast.makeText(MainJava.this , "User Deleted" , Toast.LENGTH_SHORT).show();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    private void bindViews() {
        swipe = findViewById(R.id.swipe_refresh_layout);
        recyclerView = findViewById(R.id.rc_user);
    }

}