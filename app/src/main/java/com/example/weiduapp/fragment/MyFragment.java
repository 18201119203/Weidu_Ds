package com.example.weiduapp.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lib_core.base.mvp.BasemvpFragment;
import com.example.lib_core.base.mvp.Basepresenter;
import com.example.lib_network.utils.SpUtils;
import com.example.weiduapp.activity.MyAddressActivity;
import com.example.weiduapp.R;
import com.example.weiduapp.activity.LoginActivity;
import com.example.weiduapp.adapter.AddressAdapter;
import com.example.weiduapp.adapter.HeaderBean;
import com.example.weiduapp.api.Api;
import com.example.weiduapp.bean.MyDataBean;
import com.example.weiduapp.contract.RetrofitContract;
import com.example.weiduapp.presenter.RetrofitPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TResult;

import java.io.File;
import java.util.HashMap;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MyFragment extends BasemvpFragment<RetrofitContract.IretrofitModel,RetrofitContract.retrofitPresenter> implements RetrofitContract.IretrofitView {
    private Unbinder bind;
    @BindView(R.id.my_home_tou)
    SimpleDraweeView my_home_tou;
    @BindView(R.id.my_name)
    TextView my_name;
    @BindView(R.id.relativel)
    RelativeLayout relativel;
    private TakePhoto takePhoto;
    private CropOptions cropOptions;  //裁剪参数
    private CompressConfig compressConfig;  //压缩参数
    private Uri imageUri;  //图片保存路径
    private Button camera;
    private Button photo;

    @Override
    protected int getViewId() {
        return R.layout.fragmentmy;
    }

    @Override
    protected void initView(View view) {
        bind = ButterKnife.bind(this, view);
    }

    @Override
    protected void initpresenter() {
        presenter.getData(Api.MYDATA,new HashMap<String, String>(),MyDataBean.class);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bind!=null){
            bind.unbind();
        }
    }

    /**
     * 重写点击事件
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(KeyEvent event) {
        return false;
    }


    /**
     * 我的里面列表点击事件
     * @param view
     */
    @OnClick(R.id.my_home_ziliao)
    public void onClick_my_home_ziliao(View view){

    }

    @OnClick(R.id.my_home_quanzi)
    public void onClick_my_home_quanzi(View view){

    }

    @OnClick(R.id.my_home_zuji)
    public void onClick_my_home_zuji(View view){

    }

    @OnClick(R.id.my_home_qianbao)
    public void onClick_my_home_qianbao(View view){

    }

    @OnClick(R.id.my_home_address)
    public void onClick_my_home_address(View view){
        startActivity(new Intent(getActivity(),MyAddressActivity.class));
    }

    /**
     * 初始化P层
     * @return
     */
    @Override
    public Basepresenter initPresenter() {
        return new RetrofitPresenter();
    }

    @Override
    public void failure(String msg) {
        Toast.makeText(getActivity(),msg+"",Toast.LENGTH_LONG).show();
    }

    @Override
    public <T> void SuccessData(T data) {
        if (data instanceof MyDataBean){
            MyDataBean myDataBean = (MyDataBean) data;
            if ("1001".equals(myDataBean.status)){
                SharedPreferences sp = SpUtils.getInstance().getSp();
                SharedPreferences.Editor edit = sp.edit();
                edit.putBoolean("che_zd",false).commit();
                Toast.makeText(getActivity(),myDataBean.message+"",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getActivity(),LoginActivity.class));
            }
            my_home_tou.setImageURI(Uri.parse(myDataBean.result.headPic));
            my_name.setText(myDataBean.result.nickName);
        }else if (data instanceof String){
            Toast.makeText(getActivity(),data+"",Toast.LENGTH_LONG).show();
            initpresenter();
        }

    }

        @OnClick(R.id.my_home_tou)
        public void chuanTou(View view){

            initzData();
            View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.headerpop, null, false);
            final PopupWindow pop = new PopupWindow(view1,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
            pop.setOutsideTouchable(true);
            pop.setBackgroundDrawable(new BitmapDrawable());
            pop.setFocusable(true);
            pop.showAsDropDown(view,0,0);

            camera = view1.findViewById(R.id.camera);
            photo = view1.findViewById(R.id.photo);
            camera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    imageUri = getImageCropUri();
                    takePhoto.onPickFromCaptureWithCrop(imageUri, cropOptions);
                    pop.dismiss();

                }
            });

            photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageUri = getImageCropUri();
                    takePhoto.onPickFromGalleryWithCrop(imageUri, cropOptions);
                    pop.dismiss();
                }
            });
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        String iconPath = result.getImage().getOriginalPath();
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){//挂载状态
            String path = iconPath;
            System.out.println("path:====="+path);
            File file = new File(path);
            if (file!=null&&file.exists()){
                RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),file);
                MultipartBody.Part filePart = MultipartBody.Part.createFormData("image",file.getName(),requestBody);
                presenter.postData(Api.TOUXIANG,filePart,HeaderBean.class);
            }else{
                Toast.makeText(getActivity(), "请选择文件",Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
        Toast.makeText(getActivity(), "Error:" + msg, Toast.LENGTH_SHORT).show();
        System.out.println("Error:====="+msg);
    }

    @Override
    public void takeCancel() {
        super.takeCancel();

    }

    public void initzData() {
         takePhoto = getTakePhoto();
         cropOptions = new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(false).create();
         compressConfig=new CompressConfig.Builder().setMaxSize(50*1024).setMaxPixel(800).create();
         takePhoto.onEnableCompress(compressConfig,true);
    }

    private Uri getImageCropUri() {
        File file=new File(Environment.getExternalStorageDirectory(), "/temp/"+System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        return Uri.fromFile(file);
    }



}
