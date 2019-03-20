package com.example.weiduapp.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.lib_core.base.mvp.BasemvpFragment;
import com.example.lib_core.base.mvp.Basepresenter;
import com.example.weiduapp.R;
import com.example.weiduapp.activity.OrderActivity;
import com.example.weiduapp.adapter.OrderAdapter;
import com.example.weiduapp.bean.OrderBean;
import com.example.weiduapp.bean.PayBean;
import com.example.weiduapp.contract.RetrofitContract;
import com.example.weiduapp.presenter.RetrofitPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class OrderFragment extends BasemvpFragment<RetrofitContract.IretrofitModel,RetrofitContract.retrofitPresenter>implements RetrofitContract.IretrofitView,OrderAdapter.onClickPayCallback,OrderAdapter.onClickIsCallback,OrderAdapter.onClickPjCallback {

    private Unbinder bind;
    @BindView(R.id.but_order)
    RadioGroup but_order;
    @BindView(R.id.all_order)
    RadioButton all_order;
    @BindView(R.id.money_order)
    RadioButton money_order;
    @BindView(R.id.take_order)
    RadioButton take_order;
    @BindView(R.id.app_order)
    RadioButton app_order;
    @BindView(R.id.success_order)
    RadioButton success_order;
    @BindView(R.id.rv_order)
    XRecyclerView rv_order;

    private int page = 1;
    private int count = 5;
    private int status = 0;
    private HashMap<String, String> params;
    private OrderAdapter orderAdapter;

    @Override
    protected int getViewId() {
        return R.layout.fragmentorder;
    }

    @Override
    public Basepresenter initPresenter() {
        return new RetrofitPresenter();
    }

    @Override
    protected void initView(View view) {
        bind = ButterKnife.bind(this, view);
        params = new HashMap<>();
        rv_order.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_order.setPullRefreshEnabled(true);
        rv_order.setLoadingMoreEnabled(true);
        orderAdapter = new OrderAdapter(getActivity());
        /**
         * 实例化接口
         */
        orderAdapter.setPayCallback(this);
        orderAdapter.setIsCallback(this);
        orderAdapter.setPjCallback(this);
        rv_order.setAdapter(orderAdapter);
        rv_order.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                getOrderList();
                rv_order.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                getOrderList();
                rv_order.loadMoreComplete();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        page=1;
        getOrderList();
    }

    @Override
    protected void initpresenter() {
        onOrderClick();
        getOrderList();
    }

    private void getOrderList() {
        params.put("page",page+"");
        params.put("count",count+"");
        params.put("status",status+"");
        presenter.getOrderList(params);
    }

    private void onOrderClick() {

        but_order.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.all_order:
                        status=0;
                        page=1;
                        params.put("page",page+"");
                        params.put("count",count+"");
                        params.put("status",status+"");
                        presenter.getOrderList(params);
                        break;
                    case R.id.money_order:
                        status=1;
                        page=1;
                        params.put("page",page+"");
                        params.put("count",count+"");
                        params.put("status",status+"");
                        presenter.getOrderList(params);
                        break;
                    case R.id.take_order:
                        status=2;
                        page=1;
                        params.put("page",page+"");
                        params.put("count",count+"");
                        params.put("status",status+"");
                        presenter.getOrderList(params);
                        break;
                    case R.id.app_order:
                        status=3;
                        page=1;
                        params.put("page",page+"");
                        params.put("count",count+"");
                        params.put("status",status+"");
                        presenter.getOrderList(params);
                        break;
                    case R.id.success_order:
                        status=9;
                        page=1;
                        params.put("page",page+"");
                        params.put("count",count+"");
                        params.put("status",status+"");
                        presenter.getOrderList(params);
                        break;
                }
            }
        });

    }


    @Override
    public <T> void SuccessData(T data) {

        if (data instanceof OrderBean){
            OrderBean orderBean = (OrderBean) data;
            //Toast.makeText(getActivity(),orderBean.message,Toast.LENGTH_LONG).show();
            if ("0000".equals(orderBean.status)){
                if (page==1){
                    if (orderBean.orderList.size()>0){
                        orderAdapter.setList(orderBean.orderList);
                        Toast.makeText(getActivity(),orderBean.message,Toast.LENGTH_LONG).show();
                    }else{
                        orderAdapter.setList(null);
                        Toast.makeText(getActivity(),"暂无订单",Toast.LENGTH_LONG).show();
                    }
                }else{
                    if (orderBean.orderList.size()>0){
                        orderAdapter.addList(orderBean.orderList);
                    }else{
                        Toast.makeText(getActivity(),"没有更多的订单了",Toast.LENGTH_LONG).show();
                    }
                }

            }
        }else if (data instanceof PayBean){

            PayBean payBean = (PayBean) data;
            Toast.makeText(getActivity(),payBean.message,Toast.LENGTH_LONG).show();
            orderAdapter.notifyDataSetChanged();

        }

    }
    @Override
    public void failure(String msg) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bind!=null){
            bind.unbind();
        }
    }
    @Override
    public boolean onKeyDown(KeyEvent event) {
        return false;
    }


    /**
     * 支付
     */
    @Override
    public void pay(String num) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("orderId",num);
        hashMap.put("payType","1");
        presenter.doPostPay(hashMap);
    }

    /**
     * 确认收货
     */
    @Override
    public void isShop(String num) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("orderId",num);
        presenter.doPutIsShop(hashMap);
    }

    /**
     * 评价
     */
    @Override
    public void pjShop() {

    }


}
