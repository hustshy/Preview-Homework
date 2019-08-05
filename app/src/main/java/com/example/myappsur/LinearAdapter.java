package com.example.myappsur;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import utils.SuitLines;
import utils.Unit;

public class LinearAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private OnItemClickListener mListener;

    public LinearAdapter(Context context, OnItemClickListener listener){
        this.mContext = context;
        this.mListener = listener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_linechart,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((LinearViewHolder)holder).suitLines.setXyColor(Color.parseColor("#000000"));
        List<Unit> lines = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            lines.add(new Unit(new SecureRandom().nextInt(48), i + ""));
        }
        ((LinearViewHolder)holder).suitLines.feedWithAnim(lines);
//       holder.itemView.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//               mListener.onClick(position);
//           }
//       });
    }

    @Override
    public int getItemViewType(int position) {
        if(position % 2 != 0){
            return 0;
        }else {
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }
    class LinearViewHolder extends RecyclerView.ViewHolder {
        private SuitLines suitLines;
        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            suitLines = itemView.findViewById(R.id.suit_chart);
        }
    }

    public interface OnItemClickListener{
        void onClick(int pos);
    }
}
