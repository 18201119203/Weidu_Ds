package com.example.weiduapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.weiduapp.R;
import com.example.weiduapp.bean.OrderBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class OrderAdapter extends XRecyclerView.Adapter<OrderAdapter.ViewHolder> implements OrderItemAdapter.getMoneyCallback{

    private Context context;
    private List<OrderBean.OrderListBean> list;

    public OrderAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<OrderBean.OrderListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public void addList(List<OrderBean.OrderListBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dingdan_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final OrderAdapter.ViewHolder holder, final int position) {

        for (int i = 0; i < list.get(position).detailList.size(); i++) {
            list.get(position).detailList.get(i).style = list.get(position).orderStatus;
        }
        if (list.get(position).orderStatus==1){
            holder.order_payment.setText("去支付");
            holder.order_cancel.setVisibility(View.VISIBLE);
        }else if (list.get(position).orderStatus==2){
            holder.order_payment.setText("确认收货");
            holder.order_cancel.setVisibility(View.INVISIBLE);
        }else if (list.get(position).orderStatus==3){
            holder.order_payment.setText("去评价");
            holder.order_cancel.setVisibility(View.INVISIBLE);
        }else if (list.get(position).orderStatus==9){
            holder.order_payment.setVisibility(View.INVISIBLE);
            holder.order_cancel.setVisibility(View.INVISIBLE);
        }
        holder.order_number.setText(list.get(position).orderId);
        holder.order_time.setText(list.get(position).expressCompName);
        holder.order_size.setText(list.get(position).detailList.size()+"");
        holder.order_price.setText(list.get(position).getPayAmount()+"");
        holder.order_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = holder.order_payment.getText().toString();
                if ("去支付".equals(s)){
                    if (payCallback!=null){
                        payCallback.pay(list.get(position).orderId);
                    }

                }else if ("确认收货".equals(s)){
                    if (isCallback!=null){
                        isCallback.isShop(list.get(position).orderId);
                    }

                }else if ("去评价".equals(s)){
                    if (pjCallback!=null){
                        pjCallback.pjShop();
                    }
                }

            }
        });

        holder.order_recycle.setLayoutManager(new LinearLayoutManager(context));
        OrderItemAdapter orderItemAdapter = new OrderItemAdapter(context,list.get(position).detailList,position);
        orderItemAdapter.setCallback(this);
        holder.order_recycle.setAdapter(orderItemAdapter);

    }

    @Override
    public int getItemCount() {
        return this.list==null?0:list.size();
    }

    @Override
    public void getMoney(double money,int j) {
        list.get(j).setPayAmount(money);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView order_number;
        TextView order_time;
        RecyclerView order_recycle;
        Button order_cancel;
        TextView order_size;
        TextView order_price;
        TextView order_payment;

        public ViewHolder(View itemView) {
            super(itemView);

            order_number = itemView.findViewById(R.id.order_number);
            order_time = itemView.findViewById(R.id.order_time);
            order_recycle = itemView.findViewById(R.id.order_recycle);
            order_cancel = itemView.findViewById(R.id.order_cancel);
            order_size = itemView.findViewById(R.id.order_size);
            order_price = itemView.findViewById(R.id.order_price);
            order_payment = itemView.findViewById(R.id.order_payment);

        }
    }

    private onClickPayCallback payCallback;
    public interface onClickPayCallback{
        void pay(String num);
    }
    public void setPayCallback(onClickPayCallback payCallback) {
        this.payCallback = payCallback;
    }

    private onClickIsCallback isCallback;
    public interface onClickIsCallback{
        void isShop(String num);
    }
    public void setIsCallback(onClickIsCallback isCallback) {
        this.isCallback = isCallback;
    }

    private onClickPjCallback pjCallback;
    public interface onClickPjCallback{
        void pjShop();
    }
    public void setPjCallback(onClickPjCallback pjCallback) {
        this.pjCallback = pjCallback;
    }
}
