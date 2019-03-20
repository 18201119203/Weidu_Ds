package com.example.weiduapp.bean;

import java.util.List;

public class OrderBean {

    public String message;
    public String status;
    public List<OrderListBean> orderList;

    public static class OrderListBean {

        public String expressCompName;
        public String expressSn;
        public String orderId;
        public int orderStatus;
        private double payAmount;
        public int payMethod;
        public int userId;
        public List<DetailListBean> detailList;

        public double getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(double payAmount) {
            this.payAmount = payAmount;
        }

        public static class DetailListBean {

            public int style;
            public int commentStatus;
            public int commodityCount;
            public int commodityId;
            public String commodityName;
            public String commodityPic;
            public double commodityPrice;
            public int orderDetailId;

        }
    }
}
