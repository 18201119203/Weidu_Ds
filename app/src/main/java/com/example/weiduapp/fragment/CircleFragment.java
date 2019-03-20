package com.example.weiduapp.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.example.lib_core.base.mvp.BasemvpFragment;
import com.example.lib_core.base.mvp.Basepresenter;
import com.example.weiduapp.R;
import com.example.weiduapp.adapter.CircleAdapter;
import com.example.weiduapp.api.Api;
import com.example.weiduapp.bean.CircleBean;
import com.example.weiduapp.contract.RetrofitContract;
import com.example.weiduapp.presenter.RetrofitPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CircleFragment extends BasemvpFragment<RetrofitContract.IretrofitModel,RetrofitContract.retrofitPresenter> implements RetrofitContract.IretrofitView {
    private Unbinder bind;
    private int page = 1;
    private int count = 5;

    @BindView(R.id.rv)
    XRecyclerView rv;
    private CircleAdapter circleAdapter;


    @Override
    protected int getViewId() {
        return R.layout.fragmentcircle;
    }

    @Override
    public Basepresenter initPresenter() {
        return new RetrofitPresenter();
    }

    @Override
    protected void initView(View view) {
        bind = ButterKnife.bind(this, view);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        circleAdapter = new CircleAdapter(getActivity());
        rv.setAdapter(circleAdapter);
        rv.setLoadingMoreEnabled(true);
        rv.setPullRefreshEnabled(true);
        rv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                initCircle();
                rv.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                initCircle();
                rv.loadMoreComplete();
            }
        });
    }

    @Override
    protected void initpresenter() {
        initCircle();
    }

    private void initCircle() {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("page",page+"");
        hashMap.put("count",count+"");
        presenter.getCircleData(Api.CIRCLEDATA,hashMap);

    }


    @Override
    public <T> void SuccessData(T data) {

        if (data instanceof CircleBean){
            CircleBean circleBean = (CircleBean) data;
            if (page==1){
                circleAdapter.setList(circleBean.result);
            }else{
                circleAdapter.addList(circleBean.result);
            }

        }

    }

    @Override
    public void failure(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onKeyDown(KeyEvent event) {
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bind!=null){
            bind.unbind();
        }
    }

}
