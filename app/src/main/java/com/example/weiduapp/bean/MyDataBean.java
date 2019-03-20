package com.example.weiduapp.bean;

import com.example.lib_network.bean.BaseResponseBean;

public class MyDataBean{

    public ResultBean result;
    public String message;
    public String status;

    public static class ResultBean {
        public long createTime;
        public String headPic;
        public String nickName;
        public String password;
        public String phone;
        public int sex;
        public int userId;
    }
}
