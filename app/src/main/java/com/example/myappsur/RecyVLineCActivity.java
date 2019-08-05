package com.example.myappsur;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import Adapter.LinearAdapter2;

public class RecyVLineCActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recy_vline_c);
        mRecyclerView = findViewById(R.id.recy2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(RecyVLineCActivity.this));
        mRecyclerView.setAdapter(new LinearAdapter2(RecyVLineCActivity.this, new LinearAdapter2.OnItemClickListener() {
            @Override
            public void onClick(int pos) {

            }
        }));

    }
    class MyDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0,0,0,1);
        }
    }
}
