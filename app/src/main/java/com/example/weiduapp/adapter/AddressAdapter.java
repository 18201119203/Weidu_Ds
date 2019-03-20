package com.example.weiduapp.adapter;

import android.content.Context;
import android.icu.util.IslamicCalendar;
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

public class AddressAdapter extends XRecyclerView.Adapter<AddressAdapter.ViewHolder> {

    private Context context;
    private List<AddressBean.ResultBean> list;

    public AddressAdapter(Context context) {
        this.context = context;
        notifyDataSetChanged();
    }

    public void setList(List<AddressBean.ResultBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.address_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.name.setText(list.get(position).realName);
        holder.address.setText(list.get(position).address);
        holder.phone.setText(list.get(position).phone);
        holder.address_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.address_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,list.get(position).realName,Toast.LENGTH_LONG).show();
            }
        });

        if (list.get(position).whetherDefault==1){
            holder.address_check.setChecked(true);
        }else{
            holder.address_check.setChecked(false);
        }

        holder.address_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = holder.address_check.isChecked();
                for (AddressBean.ResultBean che : list) {
                    che.whetherDefault=2;
                }
                if (checked){
                    list.get(position).whetherDefault=1;
                }
                notifyDataSetChanged();
                if (addressId!=null){
                    addressId.getAddressId(list.get(position).id+"");
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
        private CheckBox address_check;
        private TextView address_update;
        private TextView address_delete;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            phone = itemView.findViewById(R.id.phone);
            address_check = itemView.findViewById(R.id.address_check);
            address_update = itemView.findViewById(R.id.address_update);
            address_delete = itemView.findViewById(R.id.address_delete);

        }
    }

    private getAddressIdCallback addressId;
    public interface getAddressIdCallback{
        void getAddressId(String id);
    }
    public void setAddressId(getAddressIdCallback addressId) {
        this.addressId = addressId;
    }
}
