package com.example.weiduapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.weiduapp.R;
import com.example.weiduapp.bean.OrderBean;
import com.example.weiduapp.bean.ShopBase;
import com.example.weiduapp.widget.AddMinusView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class OrderItemAdapter extends XRecyclerView.Adapter<OrderItemAdapter.ViewHolder> {

    private Context context;
    private List<OrderBean.OrderListBean.DetailListBean> list;
    private int j;

    public OrderItemAdapter(Context context,List<OrderBean.OrderListBean.DetailListBean> list,int j) {
        this.context = context;
        this.list = list;
        this.j = j;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.ding_page_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        if (list.get(position).style==1){

        }else{
            holder.page_item_countt.setVisibility(View.INVISIBLE);
        }

        String[] split = list.get(position).commodityPic.split(",");
        Uri uri = Uri.parse(split[0]);
        holder.page_item_img.setImageURI(uri);
        holder.page_item_name.setText(list.get(position).commodityName);
        holder.page_item_price.setText(list.get(position).commodityPrice+"");
        holder.page_item_countt.setnum(list.get(position).commodityCount+"");

        holder.page_item_countt.onClickbut(new AddMinusView.getCallback() {
            @Override
            public void getnum(int i) {
                list.get(position).commodityCount = i;
                double money = list.get(position).commodityPrice * i;
                if (callback!=null){
                    callback.getMoney(money,j);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView page_item_img;
        TextView page_item_name;
        TextView page_item_price;
        AddMinusView page_item_countt;


        public ViewHolder(View itemView) {
            super(itemView);

            page_item_img = itemView.findViewById(R.id.page_item_img);
            page_item_name = itemView.findViewById(R.id.page_item_name);
            page_item_price = itemView.findViewById(R.id.page_item_price);
            page_item_countt = itemView.findViewById(R.id.page_item_countt);

        }
    }


    public getMoneyCallback callback;

    interface getMoneyCallback{
        void getMoney(double money,int j);
    }

    public void setCallback(getMoneyCallback callback) {
        this.callback = callback;
    }
}
