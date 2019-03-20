package com.example.weiduapp.activity;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.lib_core.base.mvp.BasemvpActivity;
import com.example.lib_core.base.mvp.Basepresenter;
import com.example.weiduapp.R;
import com.example.weiduapp.adapter.AddressAdapter;
import com.example.weiduapp.bean.AddShop;
import com.example.weiduapp.bean.AddressBean;
import com.example.weiduapp.contract.RetrofitContract;
import com.example.weiduapp.presenter.RetrofitPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyAddressActivity extends BasemvpActivity<RetrofitContract.IretrofitModel,RetrofitContract.retrofitPresenter>implements RetrofitContract.IretrofitView,AddressAdapter.getAddressIdCallback {
    private Unbinder bind;
    @BindView(R.id.Rv_address)
    XRecyclerView Rv_address;
    @BindView(R.id.address_finsh)
    TextView address_finsh;
    private AddressAdapter addressAdapter;
    @OnClick(R.id.address_finsh)
    public void onClickaddress_finsh(View viwe){
        finish();
    }
    @OnClick(R.id.add_address)
    public void onClickadd_address(View viwe){
        startActivity(new Intent(MyAddressActivity.this,AddAddressActivity.class));
    }
    @Override
    public Basepresenter initPresenter() {
        return new RetrofitPresenter();
    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        bind = ButterKnife.bind(this);
        Rv_address.setLayoutManager(new LinearLayoutManager(this));
        Rv_address.setLoadingMoreEnabled(false);
        Rv_address.setPullRefreshEnabled(true);
        addressAdapter = new AddressAdapter(MyAddressActivity.this);
        addressAdapter.setAddressId(this);
        Rv_address.setAdapter(addressAdapter);
        Rv_address.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                getNetWorkAddressData();
            }
            @Override
            public void onLoadMore() {
            }
        });
    }

    @Override
    protected void initPre() {
        getNetWorkAddressData();

    }

    private void getNetWorkAddressData() {
        presenter.getaddressList();
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_my_ddress;
    }


    /**
     * 网络请求失败
     * @param msg
     */
    @Override
    public void failure(String msg) {
        Toast.makeText(MyAddressActivity.this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bind!=null){
            bind.unbind();
        }
    }

    @Override
    public <T> void SuccessData(T data) {
        if (data instanceof AddressBean){
            AddressBean addShop = (AddressBean) data;
            Toast.makeText(MyAddressActivity.this,addShop.message,Toast.LENGTH_LONG).show();
            if ("0000".equals(addShop.status)){
                addressAdapter.setList(addShop.result);
                Rv_address.refreshComplete();
            }
        }else if (data instanceof AddShop){
            AddShop addShop = (AddShop) data;
            Toast.makeText(MyAddressActivity.this,addShop.message,Toast.LENGTH_LONG).show();
            if ("0000".equals(addShop.status)){
                presenter.getaddressList();
            }
        }


    }

    @Override
    public void getAddressId(String id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id",id);
        presenter.postMoRenAddress(hashMap);
    }
}
