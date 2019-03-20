package com.example.weiduapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weiduapp.R;
import com.example.weiduapp.bean.CircleBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class CircleitemAdapter extends RecyclerView.Adapter<CircleitemAdapter.ViewHolder> {

    private Context context;
    private List<String> listImage;

    public CircleitemAdapter(Context context,List<String> listImage ) {
        this.context = context;
        this.listImage = listImage;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.circleitemtwo, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.headtitle.setImageURI(Uri.parse(listImage.get(position)));
    }

    @Override
    public int getItemCount() {
        return "".equals(listImage.get(0))?0:listImage.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView headtitle;

        public ViewHolder(View itemView) {
            super(itemView);
            headtitle = itemView.findViewById(R.id.headtitle);
        }
    }


}
