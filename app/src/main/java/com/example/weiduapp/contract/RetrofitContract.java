package com.example.weiduapp.contract;

import com.example.lib_core.base.mvp.BaseView;
import com.example.lib_core.base.mvp.Basemodel;
import com.example.lib_core.base.mvp.Basepresenter;
import com.example.weiduapp.model.RetrofitModel;


import java.util.HashMap;

import okhttp3.MultipartBody;

public interface RetrofitContract {

    abstract class retrofitPresenter extends Basepresenter<IretrofitModel,IretrofitView> {
        @Override
        public IretrofitModel getModel() {
            return new RetrofitModel();
        }
        public abstract void getData(String url,HashMap<String,String> bodyparams,Class tclass);
        public abstract void postData(String url,MultipartBody.Part filePart,Class tclass);
        public abstract void putData(String url,HashMap<String,String> bodyparams,Class tclass);
        public abstract void postAddaddress(HashMap<String,String> bodyparams);
        public abstract void getaddressList();
        public abstract void postMoRenAddress(HashMap<String,String> bodyparams);
        public abstract void getOrderList(HashMap<String,String> bodyparams);
        public abstract void doPostSubmitOrder(HashMap<String,String> bodyparams);
        public abstract void doPostPay(HashMap<String,String> bodyparams);
        public abstract void doPutIsShop(HashMap<String,String> bodyparams);
        public abstract void getCardData(String url,HashMap<String,String> params);
        public abstract void getCircleData(String url,HashMap<String,String> params);


    }

    interface IretrofitModel extends Basemodel {

        void getData(String url, HashMap<String,String> bodyparams,Class tclass, RetrofitModel.IRetrofitCallback callback);
        void postData(String url, MultipartBody.Part filePart, Class tclass, RetrofitModel.IRetrofitCallback callback);
        void putData(String url,HashMap<String,String> bodyparams,Class tclass, RetrofitModel.IRetrofitCallback callback);
        void postAddaddress(HashMap<String,String> bodyparams,RetrofitModel.IRetrofitCallback callback);
        void getaddressList(RetrofitModel.IRetrofitCallback callback);
        void postMoRenAddress(HashMap<String,String> bodyparams,RetrofitModel.IRetrofitCallback callback);
        void getOrderList(HashMap<String,String> bodyparams,RetrofitModel.IRetrofitCallback callback);
        void doPostSubmitOrder(HashMap<String,String> bodyparams,RetrofitModel.IRetrofitCallback callback);
        void doPostPay(HashMap<String,String> bodyparams,RetrofitModel.IRetrofitCallback callback);
        void doPutIsShop(HashMap<String,String> bodyparams,RetrofitModel.IRetrofitCallback callback);
        void getCardData(String url,HashMap<String,String> params,RetrofitModel.IRetrofitCallback callback);
        void getCircleData(String url,HashMap<String,String> params,RetrofitModel.IRetrofitCallback callback);


    }

    interface IretrofitView extends BaseView {
        <T> void  SuccessData(T data);
    }

}
