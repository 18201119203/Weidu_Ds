package com.example.weiduapp.bean;

import java.util.List;

public class AddressBean {

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {

        public String address;
        public long createTime;
        public int id;
        public String phone;
        public String realName;
        public int userId;
        public int whetherDefault;
        public String zipCode;

    }
}
