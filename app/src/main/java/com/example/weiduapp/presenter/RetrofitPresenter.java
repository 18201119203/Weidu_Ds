package com.example.weiduapp.presenter;

import com.example.weiduapp.contract.RetrofitContract;
import com.example.weiduapp.model.RetrofitModel;
import com.google.gson.Gson;

import java.util.HashMap;

import okhttp3.MultipartBody;

public class RetrofitPresenter extends RetrofitContract.retrofitPresenter {


    @Override
    public void getData(String url, HashMap<String, String> bodyparams, Class tclass) {
        model.getData(url, bodyparams, tclass, new RetrofitModel.IRetrofitCallback() {
            @Override
            public void Success(Object response) {
                if (view!=null){
                    view.SuccessData(response);
                }
            }

            @Override
            public void Failure(String msg) {
                if (view!=null){
                    view.failure(msg);
                }
            }
        });

    }

    @Override
    public void postData(String url, MultipartBody.Part filePart, Class tclass) {

        model.postData(url, filePart, tclass, new RetrofitModel.IRetrofitCallback() {

            @Override
            public void Success(Object response) {
                if (view!=null){
                    view.SuccessData(response);
                }
            }

            @Override
            public void Failure(String msg) {
                if (view!=null){
                    view.failure(msg);
                }
            }
        });

    }


    @Override
    public void putData(String url, HashMap<String, String> bodyparams, Class tclass) {

        model.putData(url, bodyparams,tclass, new RetrofitModel.IRetrofitCallback() {
            @Override
            public void Success(Object response) {
                if (view!=null){
                    view.SuccessData(response);
                }
            }

            @Override
            public void Failure(String msg) {
                if (view!=null){
                    view.failure(msg);
                }
            }
        });
    }

    @Override
    public void postAddaddress(HashMap<String, String> bodyparams) {

        model.postAddaddress(bodyparams, new RetrofitModel.IRetrofitCallback() {
            @Override
            public void Success(Object response) {
                if (view!=null){
                    view.SuccessData(response);
                }
            }

            @Override
            public void Failure(String msg) {
                if (view!=null){
                    view.failure(msg);
                }
            }
        });

    }

    @Override
    public void getaddressList() {
        model.getaddressList(new RetrofitModel.IRetrofitCallback() {
            @Override
            public void Success(Object response) {
                if (view!=null){
                    view.SuccessData(response);
                }
            }
            @Override
            public void Failure(String msg) {
                if (view!=null){
                    view.failure(msg);
                }
            }
        });

    }

    @Override
    public void postMoRenAddress(HashMap<String, String> bodyparams) {

        model.postMoRenAddress(bodyparams, new RetrofitModel.IRetrofitCallback() {
            @Override
            public void Success(Object response) {
                if (view!=null){
                    view.SuccessData(response);
                }
            }

            @Override
            public void Failure(String msg) {
                if (view!=null){
                    view.failure(msg);
                }
            }
        });
    }

    @Override
    public void getOrderList(HashMap<String, String> bodyparams) {
        model.getOrderList(bodyparams, new RetrofitModel.IRetrofitCallback() {
            @Override
            public void Success(Object response) {
                if (view!=null){
                    view.SuccessData(response);
                }
            }

            @Override
            public void Failure(String msg) {
                if (view!=null){
                    view.failure(msg);
                }
            }
        });
    }

    @Override
    public void doPostSubmitOrder(HashMap<String, String> bodyparams) {
        model.doPostSubmitOrder(bodyparams, new RetrofitModel.IRetrofitCallback() {
            @Override
            public void Success(Object response) {
                if (view!=null){
                    view.SuccessData(response);
                }
            }

            @Override
            public void Failure(String msg) {
                if (view!=null){
                    view.failure(msg);
                }
            }
        });
    }

    @Override
    public void doPostPay(HashMap<String, String> bodyparams) {
        model.doPostPay(bodyparams, new RetrofitModel.IRetrofitCallback() {
            @Override
            public void Success(Object response) {
                if (view!=null){
                    view.SuccessData(response);
                }
            }

            @Override
            public void Failure(String msg) {
                if (view!=null){
                    view.failure(msg);
                }
            }
        });
    }

    @Override
    public void doPutIsShop(HashMap<String, String> bodyparams) {
        model.doPutIsShop(bodyparams, new RetrofitModel.IRetrofitCallback() {
            @Override
            public void Success(Object response) {
                if (view!=null){
                    view.SuccessData(response);
                }
            }

            @Override
            public void Failure(String msg) {
                if (view!=null){
                    view.failure(msg);
                }
            }
        });
    }

    @Override
    public void getCardData(String url, HashMap<String, String> params) {
        model.getCardData(url, params, new RetrofitModel.IRetrofitCallback() {
            @Override
            public void Success(Object response) {
                if (view!=null){
                    view.SuccessData(response);
                }
            }

            @Override
            public void Failure(String msg) {
                if (view!=null){
                    view.failure(msg);
                }
            }
        });
    }

    @Override
    public void getCircleData(String url, HashMap<String, String> params) {
        model.getCircleData(url, params, new RetrofitModel.IRetrofitCallback() {
            @Override
            public void Success(Object response) {
                if (view!=null){
                    view.SuccessData(response);
                }
            }

            @Override
            public void Failure(String msg) {
                if (view!=null){
                    view.failure(msg);
                }
            }
        });
    }


}
