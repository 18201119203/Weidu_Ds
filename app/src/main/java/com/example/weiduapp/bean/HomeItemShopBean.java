package com.example.weiduapp.bean;

import java.util.List;

public class HomeItemShopBean {

    public ResultBean result;
    public String message;
    public String status;

    public static class ResultBean {
        public listBean rxxp;
        public listBean pzsh;
        public listBean mlss;

        public static class listBean {
            public int id;
            public String name;
            public List<CommodityListBean> commodityList;

            public static class CommodityListBean {

                public int commodityId;
                public String commodityName;
                public String masterPic;
                public int price;
                public int saleNum;
            }
        }
    }

}
