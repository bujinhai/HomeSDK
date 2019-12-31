package com.jinshu.homelibrary.entity;

import java.util.List;

/**
 * Create on 2019-11-08 14:26 by bll
 */

public class ShopEntity {


    /**
     * data : {"total":2,"currentPage":1,"currentPgeNumber":2,"pageNumber":10,"totalPage":1,"hasNextPage":false,"rows":[{"shopID":"8c522fe89e83419ab652aab345c2db6f","name":"滕州荆河店","shortName":"滕州荆河店","kingID":"11222222","companyID":"9601b5f9e296474b82be6db6072aa79b","companyName":"山东省滕州市健康咨询有限公司","cityID":"d3f0176bf7cd49838145f50a1d5e642c","cityName":"黄浦区","shortDescription":"3333333","logoURL":null,"listImage":"http://wj.haoju.me/2eb5998dac8f4ab1a05d3fc56bd9be29.png","headMan":"赵真利研发","address":"滕州荆河东路100号","tel":"13601010100","contact":"井河口","weixinQRCode":null,"openTime":null,"shengID":"b87661038e90436392b9eb0220549c2b","shengName":"山东省","shiID":"90b85ca9bf114f45a8b4f393a129d4ac","shiName":"枣庄市","xianID":"e86c49f12dcc4b9a9ccb0795c3ba19b5","xianName":"滕州市","zhenID":null,"zhenName":null},{"shopID":"b678635667054af18c05cea7fa29c98c","name":"上海西藏北路店-即将开业","shortName":"上海西藏北路店-立刻开业","kingID":"1000011","companyID":"ff3310632f25450fb106ef6f2c8551be","companyName":"聚枢信息科技有限公司","cityID":"8e2dbf8ee7d54cebac0b5eb6317fcb5d","cityName":"静安区","shortDescription":"立刻开业","logoURL":"http://admin.haoju.me:8082/kpbase//group/M00/F4/6D/CCBC-D1A2-4D1F-971E-A980CA163D11.png","listImage":"http://admin.haoju.me:8082/kpbase//group/M00/3F/4B/6BA8-612A-4D0B-A76A-C367189C1CCC.png","headMan":"赵真利-占位","address":"上海市静安区西藏北路605号C座601室","tel":"13601767176","contact":"赵真利","weixinQRCode":null,"openTime":null,"shengID":"225af9d7f0a841fcbaf8db478c435c23","shengName":"上海市","shiID":"225af9d7f0a841fcbaf8db478c435c23","shiName":"上海市","xianID":"8e2dbf8ee7d54cebac0b5eb6317fcb5d","xianName":"静安区","zhenID":"d91c2db79f674660b4d4e484cb9e635c","zhenName":"芷江西路街道"}]}
     */

    private DataInfo data;

    public DataInfo getData() {
        return data;
    }

    public void setData(DataInfo data) {
        this.data = data;
    }

    public static class DataInfo {
        /**
         * total : 2
         * currentPage : 1
         * currentPgeNumber : 2
         * pageNumber : 10
         * totalPage : 1
         * hasNextPage : false
         * rows : [{"shopID":"8c522fe89e83419ab652aab345c2db6f","name":"滕州荆河店","shortName":"滕州荆河店","kingID":"11222222","companyID":"9601b5f9e296474b82be6db6072aa79b","companyName":"山东省滕州市健康咨询有限公司","cityID":"d3f0176bf7cd49838145f50a1d5e642c","cityName":"黄浦区","shortDescription":"3333333","logoURL":null,"listImage":"http://wj.haoju.me/2eb5998dac8f4ab1a05d3fc56bd9be29.png","headMan":"赵真利研发","address":"滕州荆河东路100号","tel":"13601010100","contact":"井河口","weixinQRCode":null,"openTime":null,"shengID":"b87661038e90436392b9eb0220549c2b","shengName":"山东省","shiID":"90b85ca9bf114f45a8b4f393a129d4ac","shiName":"枣庄市","xianID":"e86c49f12dcc4b9a9ccb0795c3ba19b5","xianName":"滕州市","zhenID":null,"zhenName":null},{"shopID":"b678635667054af18c05cea7fa29c98c","name":"上海西藏北路店-即将开业","shortName":"上海西藏北路店-立刻开业","kingID":"1000011","companyID":"ff3310632f25450fb106ef6f2c8551be","companyName":"聚枢信息科技有限公司","cityID":"8e2dbf8ee7d54cebac0b5eb6317fcb5d","cityName":"静安区","shortDescription":"立刻开业","logoURL":"http://admin.haoju.me:8082/kpbase//group/M00/F4/6D/CCBC-D1A2-4D1F-971E-A980CA163D11.png","listImage":"http://admin.haoju.me:8082/kpbase//group/M00/3F/4B/6BA8-612A-4D0B-A76A-C367189C1CCC.png","headMan":"赵真利-占位","address":"上海市静安区西藏北路605号C座601室","tel":"13601767176","contact":"赵真利","weixinQRCode":null,"openTime":null,"shengID":"225af9d7f0a841fcbaf8db478c435c23","shengName":"上海市","shiID":"225af9d7f0a841fcbaf8db478c435c23","shiName":"上海市","xianID":"8e2dbf8ee7d54cebac0b5eb6317fcb5d","xianName":"静安区","zhenID":"d91c2db79f674660b4d4e484cb9e635c","zhenName":"芷江西路街道"}]
         */

        private int total;
        private int currentPage;
        private int currentPgeNumber;
        private int pageNumber;
        private int totalPage;
        private boolean hasNextPage;
        private List<ShopInfo> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getCurrentPgeNumber() {
            return currentPgeNumber;
        }

        public void setCurrentPgeNumber(int currentPgeNumber) {
            this.currentPgeNumber = currentPgeNumber;
        }

        public int getPageNumber() {
            return pageNumber;
        }

        public void setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public List<ShopInfo> getRows() {
            return rows;
        }

        public void setRows(List<ShopInfo> rows) {
            this.rows = rows;
        }
    }
}
