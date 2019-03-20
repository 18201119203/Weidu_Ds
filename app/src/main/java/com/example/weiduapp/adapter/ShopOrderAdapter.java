package com.example.weiduapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weiduapp.CartCallback.NotifyNum;
import com.example.weiduapp.R;
import com.example.weiduapp.bean.ShopCartBean;
import com.example.weiduapp.widget.AddMinusView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.List;

public class ShopOrderAdapter extends XRecyclerView.Adapter<ShopOrderAdapter.ViewHolder> {

    private List<ShopCartBean.ResultBean> list;
    private Context context;
    public ShopOrderAdapter(Context context, List<ShopCartBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_item_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int i) {

        holder.price.setText(list.get(i).price+"");
        holder.title.setText(list.get(i).commodityName);
        Glide.with(context).load(list.get(i).pic).into(holder.iv_product);
        holder.addminusView.setnum(list.get(i).count+"");

        holder.addminusView.onClickbut(new AddMinusView.getCallback() {
            @Override
            public void getnum(int j) {
                list.get(i).num = j;
                if (callback!=null){
                    callback.notifyMoney();
                }
            }
        });

        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list.remove(i);
                notifyDataSetChanged();
                holder.sm.quickClose();

            }
        });



    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_product;
        private TextView title;
        private TextView price;
        private AddMinusView addminusView;
        private Button del;
        private SwipeMenuLayout sm;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_product = itemView.findViewById(R.id.iv_product);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            addminusView = itemView.findViewById(R.id.addminusView);
            del = itemView.findViewById(R.id.del);
            sm = itemView.findViewById(R.id.sm);
        }
    }

    private notifyMoneyCallback callback;

    public void setCallback(notifyMoneyCallback callback) {
        this.callback = callback;
    }

    public interface notifyMoneyCallback{
        void notifyMoney();
    }

}
