package com.example.myappsur;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewLinechartActivity extends AppCompatActivity {
    private RecyclerView mRvlinechart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_linechart);
        mRvlinechart = findViewById(R.id.recyclerview);
        mRvlinechart.setLayoutManager(new LinearLayoutManager(RecyclerViewLinechartActivity.this));
        mRvlinechart.addItemDecoration(new MyDecoration());
        mRvlinechart.setAdapter(new LinearAdapter(RecyclerViewLinechartActivity.this, new LinearAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {

            }
        }));

    }
    class MyDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
        }
    }
}
