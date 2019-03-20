package com.example.weiduapp.api;

import com.example.weiduapp.adapter.HeaderBean;
import com.example.weiduapp.bean.AddOrder;
import com.example.weiduapp.bean.AddShop;
import com.example.weiduapp.bean.AddressBean;
import com.example.weiduapp.bean.CircleBean;
import com.example.weiduapp.bean.MyDataBean;
import com.example.weiduapp.bean.OrderBean;
import com.example.weiduapp.bean.PayBean;
import com.example.weiduapp.bean.ShopCartBean;

import java.util.HashMap;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface RetrofitServerApi {

    @PUT
    Observable<AddShop> doPut(@Url String url,@QueryMap HashMap<String,String> bodyparms);

    @GET
    Observable<MyDataBean> doGet(@Url String url, @QueryMap HashMap<String,String> parms);

    @POST
    @Multipart
    Observable<HeaderBean> doPost(@Url String url,@Part MultipartBody.Part f);

    @FormUrlEncoded
    @POST
    Observable<AddShop> doPostAddAddress(@Url String url, @FieldMap HashMap<String, String> parms);

    @FormUrlEncoded
    @POST
    Observable<AddShop> doPostMoRenAddress(@Url String url, @FieldMap HashMap<String,String> parms);

    @GET
    Observable<AddressBean> doGetAddAddressList(@Url String url);

    @GET
    Observable<OrderBean> doGetOrderList(@Url String url,@QueryMap HashMap<String,String> parms);

    @FormUrlEncoded
    @POST
    Observable<AddOrder> doPostSubmitOrder(@Url String url, @FieldMap HashMap<String, String> parms);

    @FormUrlEncoded
    @POST
    Observable<PayBean> doPostPay(@Url String url, @FieldMap HashMap<String, String> parms);

    @PUT
    Observable<PayBean> doPutIsShop(@Url String url,@QueryMap HashMap<String,String> bodyparms);

    @GET
    Observable<ShopCartBean> getCardData(@Url String url, @QueryMap HashMap<String,String> bodyparms);

    @GET
    Observable<CircleBean> getCircleData(@Url String url, @QueryMap HashMap<String,String> bodyparms);



}
