package com.example.weiduapp.bean;

import java.util.List;

public class CircleBean {

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {

        public int commodityId;
        public String content;
        public long createTime;
        public int greatNum;
        public String headPic;
        public int id;
        public String image;
        public String nickName;
        public int userId;
        public int whetherGreat;


    }
}
