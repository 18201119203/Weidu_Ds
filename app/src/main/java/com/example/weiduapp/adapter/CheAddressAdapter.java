package com.example.weiduapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weiduapp.R;
import com.example.weiduapp.bean.AddressBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class CheAddressAdapter extends XRecyclerView.Adapter<CheAddressAdapter.ViewHolder> {

    private Context context;
    private List<AddressBean.ResultBean> list;

    public CheAddressAdapter(Context context,List<AddressBean.ResultBean> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.che_addresspopitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.name.setText(list.get(position).realName);
        holder.address.setText(list.get(position).address);
        holder.phone.setText(list.get(position).phone);
        holder.che.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addressId!=null){
                    addressId.getAddressId(list.get(position));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView address;
        private TextView phone;
        private TextView che;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            phone = itemView.findViewById(R.id.phone);
            che = itemView.findViewById(R.id.che);

        }
    }

    private getAddressIdCallback addressId;

    public interface getAddressIdCallback{
        void getAddressId(AddressBean.ResultBean resultBean);
    }
    public void setAddressId(getAddressIdCallback addressId) {
        this.addressId = addressId;
    }
}
