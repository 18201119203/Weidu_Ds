package com.example.weiduapp.model;

import android.annotation.SuppressLint;
import com.example.lib_network.network.RetrofitUtils;
import com.example.lib_network.network.RxUtils;
import com.example.weiduapp.adapter.HeaderBean;
import com.example.weiduapp.api.Api;
import com.example.weiduapp.api.RetrofitServerApi;
import com.example.weiduapp.bean.AddOrder;
import com.example.weiduapp.bean.AddShop;
import com.example.weiduapp.bean.AddressBean;
import com.example.weiduapp.bean.CircleBean;
import com.example.weiduapp.bean.MyDataBean;
import com.example.weiduapp.bean.OrderBean;
import com.example.weiduapp.bean.PayBean;
import com.example.weiduapp.bean.ShopCartBean;
import com.example.weiduapp.contract.RetrofitContract;
import java.util.HashMap;
import io.reactivex.functions.Consumer;
import okhttp3.MultipartBody;

public class RetrofitModel implements RetrofitContract.IretrofitModel {


    @SuppressLint("CheckResult")
    @Override
    public void getData(String url, HashMap<String, String> params, final Class tclass, final RetrofitModel.IRetrofitCallback callback) {

        RetrofitUtils.getInstance().createService(RetrofitServerApi.class)
                .doGet(url,params)
                .compose(RxUtils.<MyDataBean>schdulers())
                .subscribe(new Consumer<MyDataBean>() {
                    @Override
                    public void accept(MyDataBean myDataBean) throws Exception {
                        if (callback!=null){
                            callback.Success(myDataBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (callback!=null){
                            callback.Failure("网络异常,请重新加载");
                        }
                    }
                });

    }

    @SuppressLint("CheckResult")
    @Override
    public void postData(String url, MultipartBody.Part filePart, Class tclass, final RetrofitModel.IRetrofitCallback callback) {

        RetrofitUtils.getInstance().createService(RetrofitServerApi.class)
                .doPost(url,filePart)
                .compose(RxUtils.<HeaderBean>schdulers())
                .subscribe(new Consumer<HeaderBean>() {
                    @Override
                    public void accept(HeaderBean headerBean) throws Exception {
                        if (callback!=null){
                            callback.Success(headerBean.message);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (callback!=null){
                            callback.Failure("网络异常,请重新加载");
                        }
                    }
                });

    }

    @SuppressLint("CheckResult")
    @Override
    public void putData(String url, HashMap<String, String> bodyparams,Class tclass, final IRetrofitCallback callback) {

        RetrofitUtils.getInstance().createService(RetrofitServerApi.class)
                .doPut(url,bodyparams)
                .compose(RxUtils.<AddShop>schdulers())
                .subscribe(new Consumer<AddShop>() {
                    @Override
                    public void accept(AddShop addShop) throws Exception {

                        if (callback!=null){
                            callback.Success(addShop.message);
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (callback!=null){
                            callback.Failure("网络异常,请重新加载");
                        }
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void postAddaddress(HashMap<String, String> bodyparams, final IRetrofitCallback callback) {

        RetrofitUtils.getInstance().createService(RetrofitServerApi.class)
                .doPostAddAddress(Api.ADDADDRESS,bodyparams)
                .compose(RxUtils.<AddShop>schdulers())
                .subscribe(new Consumer<AddShop>() {
                    @Override
                    public void accept(AddShop addShop) throws Exception {

                        if (callback!=null){
                            callback.Success(addShop);
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (callback!=null){
                            callback.Failure("网络异常,请重新加载");
                        }
                    }
                });


    }

    @SuppressLint("CheckResult")
    @Override
    public void getaddressList(final IRetrofitCallback callback) {
        RetrofitUtils.getInstance().createService(RetrofitServerApi.class)
                .doGetAddAddressList(Api.GETADDRESS)
                .compose(RxUtils.<AddressBean>schdulers())
                .subscribe(new Consumer<AddressBean>() {
                    @Override
                    public void accept(AddressBean addressBean) throws Exception {
                        if (callback!=null){
                            callback.Success(addressBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (callback!=null){
                            callback.Failure("网络异常,请重新加载");
                        }
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void postMoRenAddress(HashMap<String, String> bodyparams, final IRetrofitCallback callback) {
        RetrofitUtils.getInstance().createService(RetrofitServerApi.class)
                .doPostMoRenAddress(Api.MORENADDRESS,bodyparams)
                .compose(RxUtils.<AddShop>schdulers())
                .subscribe(new Consumer<AddShop>() {
                    @Override
                    public void accept(AddShop addShop) throws Exception {

                        if (callback!=null){
                            callback.Success(addShop);
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (callback!=null){
                            callback.Failure("网络异常,请重新加载");
                        }
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getOrderList(HashMap<String, String> bodyparams, final IRetrofitCallback callback) {
        RetrofitUtils.getInstance().createService(RetrofitServerApi.class)
                .doGetOrderList(Api.GETORDER,bodyparams)
                .compose(RxUtils.<OrderBean>schdulers())
                .subscribe(new Consumer<OrderBean>() {
                    @Override
                    public void accept(OrderBean orderBean) throws Exception {
                        if (callback!=null){
                            callback.Success(orderBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (callback!=null){
                            callback.Failure("网络异常,请重新加载");
                        }
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void doPostSubmitOrder(HashMap<String, String> bodyparams, final IRetrofitCallback callback) {
        RetrofitUtils.getInstance().createService(RetrofitServerApi.class)
                .doPostSubmitOrder(Api.SUBMITORDER,bodyparams)
                .compose(RxUtils.<AddOrder>schdulers())
                .subscribe(new Consumer<AddOrder>() {
                    @Override
                    public void accept(AddOrder addOrder) throws Exception {
                        if (callback!=null){
                            callback.Success(addOrder);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (callback!=null){
                            callback.Failure("网络异常,请重新加载");
                        }
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void doPostPay(HashMap<String, String> bodyparams, final IRetrofitCallback callback) {
        RetrofitUtils.getInstance().createService(RetrofitServerApi.class)
                .doPostPay(Api.PAY,bodyparams)
                .compose(RxUtils.<PayBean>schdulers())
                .subscribe(new Consumer<PayBean>() {
                    @Override
                    public void accept(PayBean payBean) throws Exception {
                        if (callback!=null){
                            callback.Success(payBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (callback!=null){
                            callback.Failure("网络异常,请重新加载");
                        }
                    }
                });
    }
    @SuppressLint("CheckResult")
    @Override
    public void doPutIsShop(HashMap<String, String> bodyparams, final IRetrofitCallback callback) {
        RetrofitUtils.getInstance().createService(RetrofitServerApi.class)
                .doPutIsShop(Api.ISSHOP,bodyparams)
                .compose(RxUtils.<PayBean>schdulers())
                .subscribe(new Consumer<PayBean>() {
                    @Override
                    public void accept(PayBean payBean) throws Exception {
                        if (callback!=null){
                            callback.Success(payBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (callback!=null){
                            callback.Failure("网络异常,请重新加载");
                        }
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getCardData(String url, HashMap<String, String> params, final IRetrofitCallback callback) {
        RetrofitUtils.getInstance().createService(RetrofitServerApi.class)
                .getCardData(url,params)
                .compose(RxUtils.<ShopCartBean>schdulers())
                .subscribe(new Consumer<ShopCartBean>() {
                    @Override
                    public void accept(ShopCartBean shopCartBean) throws Exception {
                        if (callback!=null){
                            callback.Success(shopCartBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (callback!=null){
                            callback.Failure("网络异常,请重新加载");
                        }
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getCircleData(String url, HashMap<String, String> params, final IRetrofitCallback callback) {
        RetrofitUtils.getInstance().createService(RetrofitServerApi.class)
                .getCircleData(url,params)
                .compose(RxUtils.<CircleBean>schdulers())
                .subscribe(new Consumer<CircleBean>() {
                    @Override
                    public void accept(CircleBean circleBean) throws Exception {
                        if (callback!=null){
                            callback.Success(circleBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (callback!=null){
                            callback.Failure("网络异常,请重新加载");
                        }
                    }
                });
    }

    private IRetrofitCallback iRetrofitCallback;
    public interface IRetrofitCallback{
        void Success(Object response);
        void Failure(String msg);
    }

}
