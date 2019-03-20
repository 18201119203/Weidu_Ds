package com.example.weiduapp.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lib_core.base.mvp.BasemvpActivity;
import com.example.lib_core.base.mvp.Basepresenter;
import com.example.weiduapp.R;
import com.example.weiduapp.adapter.CheAddressAdapter;
import com.example.weiduapp.adapter.ShopOrderAdapter;
import com.example.weiduapp.bean.AddOrder;
import com.example.weiduapp.bean.AddressBean;
import com.example.weiduapp.bean.ShopCartBean;
import com.example.weiduapp.contract.RetrofitContract;
import com.example.weiduapp.presenter.RetrofitPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class OrderActivity extends BasemvpActivity<RetrofitContract.IretrofitModel,RetrofitContract.retrofitPresenter> implements RetrofitContract.IretrofitView,ShopOrderAdapter.notifyMoneyCallback,CheAddressAdapter.getAddressIdCallback {
    private Unbinder bind;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.rv)
    XRecyclerView rv;
    @BindView(R.id.count)
    TextView count;
    @BindView(R.id.money)
    TextView money;
    private List<ShopCartBean.ResultBean> list;
    private ShopOrderAdapter shopOrderAdapter;
    private RecyclerView rv1;
    private AddressBean addShop;
    private PopupWindow pop;
    private int addressId;
    private HashMap hashMap;
    private double price;

    /**
     * 选择地址
     * @param view
     */
    @OnClick(R.id.che_address)
    public void setChe_address(View view){

        View inflate = LayoutInflater.from(OrderActivity.this).inflate(R.layout.chepopaddress, null, false);
        pop = new PopupWindow(inflate,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
        pop.setFocusable(true);//获取焦点
        pop.setOutsideTouchable(true);//获取外部触摸事件
        pop.setTouchable(true);//能够响应触摸事件
        pop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//设置背景

        rv1 = inflate.findViewById(R.id.rv);
        rv1.setLayoutManager(new LinearLayoutManager(OrderActivity.this));
        CheAddressAdapter cheAddressAdapter = new CheAddressAdapter(OrderActivity.this, addShop.result);
        cheAddressAdapter.setAddressId(this);
        rv1.setAdapter(cheAddressAdapter);
        pop.showAsDropDown(view,0,0);

    }

    /**
     * 点击选择订单
     * @param resultBean
     */
    @Override
    public void getAddressId(AddressBean.ResultBean resultBean) {
        name.setText(resultBean.realName);
        phone.setText(resultBean.phone);
        address.setText(resultBean.address);
        addressId = resultBean.id;
        pop.dismiss();

    }


   /* private String one = "[{'commodityId':";
    private String two = ",'amount':";
    private String three = "}]";
    private String four = "},{'commodityId':";*/
    /**
     * 提交订单
     * @param view
     */
    @OnClick(R.id.order)
    public void setorder(View view){

        String rderInfo = "";
        hashMap = new HashMap<String,String>();
        for (int i = 0; i < list.size(); i++) {
            if (list.size()==1) {
                rderInfo = "["+list.get(i).toString()+"]";
            }else if(i==0&&list.size()>1&&i!=list.size()-1){
                rderInfo += "["+list.get(i).toString()+",";
            }else if (i!=0&&list.size()>1&&i==list.size()-1){
                rderInfo += list.get(i).toString()+"]";
            }else{
                rderInfo += list.get(i).toString()+",";
            }
        }

        hashMap.put("orderInfo",rderInfo);
        hashMap.put("totalPrice",price+"");
        hashMap.put("addressId",addressId+"");
        presenter.doPostSubmitOrder(hashMap);

        finish();

    }

    @Override
    protected int getViewId() {
        return R.layout.activity_order;
    }

    @Override
    public Basepresenter initPresenter() {
        return new RetrofitPresenter();
    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        bind = ButterKnife.bind(this);
        EventBus.getDefault().register(this);

    }

    /**
     * 传数据
     * @param list
     */
    @Subscribe(sticky = true)
    public void getShopData(List<ShopCartBean.ResultBean> list){
        this.list = list;

    }

    @Override
    protected void initPre() {
        //初始化数据
        initShopData();
        //设置数据
        initMoney();
        //查询地址
        initAddress();

    }

    private void initAddress() {
        presenter.getaddressList();
    }

    private void initShopData() {
        shopOrderAdapter = new ShopOrderAdapter(OrderActivity.this, list);
        shopOrderAdapter.setCallback(this);
        rv.setLayoutManager(new LinearLayoutManager(OrderActivity.this));
        rv.setPullRefreshEnabled(true);
        rv.setLoadingMoreEnabled(false);
        rv.setAdapter(shopOrderAdapter);
        rv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                rv.refreshComplete();
            }
            @Override
            public void onLoadMore() {
                rv.loadMoreComplete();
            }
        });
    }

    @Override
    public void notifyMoney() {
        initMoney();
    }

    private void initMoney() {
        count.setText(list.size()+"");
        price = 0;
        for (ShopCartBean.ResultBean resultBean : list) {
            if (resultBean.ischelick){
                price += resultBean.num * resultBean.price;
            }
        }
        money.setText(price +"");
    }

    @Override
    public <T> void SuccessData(T data) {
        if (data instanceof AddressBean){
            addShop = (AddressBean) data;
            Toast.makeText(OrderActivity.this, addShop.message,Toast.LENGTH_LONG).show();
            if ("0000".equals(addShop.status)){
                for (AddressBean.ResultBean resultBean : addShop.result) {
                    if (resultBean.whetherDefault==1){
                        name.setText(resultBean.realName);
                        phone.setText(resultBean.phone);
                        address.setText(resultBean.address);
                        addressId = resultBean.id;
                    }
                }
            }
        }else if (data instanceof AddOrder){
            AddOrder addOrder = (AddOrder) data;
            Toast.makeText(OrderActivity.this, addOrder.message,Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void failure(String msg) {
        Toast.makeText(OrderActivity.this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind!=null){
            bind.unbind();
        }
    }



}
