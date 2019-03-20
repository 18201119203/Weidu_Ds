package com.example.weiduapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.example.weiduapp.R;
import com.example.weiduapp.bean.CircleBean;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import java.util.List;

public class CircleAdapter extends RecyclerView.Adapter<CircleAdapter.ViewHolder> {

    private Context context;
    private List<CircleBean.ResultBean> list;

    public CircleAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<CircleBean.ResultBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public void addList(List<CircleBean.ResultBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.circleitemone, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        String[] split = list.get(position).image.split(",");
        List<String> listImage = new ArrayList<>();
        for (String s : split) {
            listImage.add(s);
        }
        holder.headtitle.setImageURI(Uri.parse(list.get(position).headPic));
        holder.title.setText(list.get(position).nickName);
        holder.time.setText(list.get(position).createTime+"");
        holder.data.setText(list.get(position).content);
        holder.image.setLayoutManager(new GridLayoutManager(context,1,GridLayoutManager.HORIZONTAL,false));
        holder.image.setAdapter(new CircleitemAdapter(context,listImage));
        holder.num.setText(list.get(position).greatNum+"");
        holder.zhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.zhan.isChecked()){
                    list.get(position).greatNum += 1;
                }else{
                    list.get(position).greatNum -= 1;
                }
                holder.num.setText(list.get(position).greatNum+"");
            }
        });



    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView headtitle;
        private TextView title;
        private TextView time;
        private TextView data;
        private TextView num;
        private CheckBox zhan;
        private RecyclerView image;

        public ViewHolder(View itemView) {
            super(itemView);
            headtitle = itemView.findViewById(R.id.headtitle);
            title = itemView.findViewById(R.id.title);
            time = itemView.findViewById(R.id.time);
            data = itemView.findViewById(R.id.data);
            num = itemView.findViewById(R.id.num);
            zhan = itemView.findViewById(R.id.zhan);
            image = itemView.findViewById(R.id.image);

        }
    }
}
