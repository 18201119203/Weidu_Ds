package com.example.weiduapp.activity;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.lib_core.base.mvp.BasemvpActivity;
import com.example.lib_core.base.mvp.Basepresenter;
import com.example.weiduapp.R;
import com.example.weiduapp.bean.AddShop;
import com.example.weiduapp.contract.RetrofitContract;
import com.example.weiduapp.presenter.RetrofitPresenter;
import java.util.HashMap;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AddAddressActivity extends BasemvpActivity<RetrofitContract.IretrofitModel,RetrofitContract.retrofitPresenter> implements RetrofitContract.IretrofitView {

    @BindView(R.id.btn_save)
    Button btn_save;
    @BindView(R.id.edit_sjr)
    EditText edit_sjr;
    @BindView(R.id.edit_phone)
    EditText edit_phone;
    @BindView(R.id.edit_dz)
    EditText edit_dz;
    @BindView(R.id.edit_yzbm)
    EditText edit_yzbm;

    private Unbinder bind;
    @Override
    protected int getViewId() {
        return R.layout.activity_add_address;
    }

    @Override
    protected void initPre() {

    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        bind = ButterKnife.bind(this);
    }

    @Override
    public Basepresenter initPresenter() {
        return new RetrofitPresenter();
    }

    /**
     * 点击使用并保存
     * @param view
     */
    @OnClick(R.id.btn_save)
    public void netWorkRequset(View view){
        String sjr = edit_sjr.getText().toString();
        String phone = edit_phone.getText().toString();
        String dz = edit_dz.getText().toString();
        String yzbm = edit_yzbm.getText().toString();
        HashMap<String,String> bodyparams = new HashMap<>();
        bodyparams.put("realName",sjr);
        bodyparams.put("phone",phone);
        bodyparams.put("address",dz);
        bodyparams.put("zipCode",yzbm);
        presenter.postAddaddress(bodyparams);

    }

    @Override
    public void failure(String msg) {

        Toast.makeText(AddAddressActivity.this,msg,Toast.LENGTH_LONG).show();
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
        if (data instanceof AddShop){
            AddShop addShop = (AddShop) data;
            Toast.makeText(AddAddressActivity.this,addShop.message,Toast.LENGTH_LONG).show();
            if ("0000".equals(addShop.status)){
                finish();
            }
        }

    }
}

