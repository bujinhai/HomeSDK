package com.jinshu.homelibrary.entity;

import java.util.List;

/**
 * Create on 2019/10/8 16:38 by bll
 */


public class HotWordData {


    /**
     * data : {"total":0,"currentPage":1,"currentPgeNumber":0,"pageNumber":10,"totalPage":0,"hasNextPage":false,"rows":[]}
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
         * total : 0
         * currentPage : 1
         * currentPgeNumber : 0
         * pageNumber : 10
         * totalPage : 0
         * hasNextPage : false
         * rows : []
         */

        private int total;
        private int currentPage;
        private int currentPgeNumber;
        private int pageNumber;
        private int totalPage;
        private boolean hasNextPage;
        private List<HotWordInfo> rows;

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

        public List<HotWordInfo> getRows() {
            return rows;
        }

        public void setRows(List<HotWordInfo> rows) {
            this.rows = rows;
        }
    }
}
