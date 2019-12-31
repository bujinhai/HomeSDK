package com.jinshu.homelibrary.entity;

import java.util.List;

/**
 * Create on 2019/9/30 21:16 by bll
 */


public class FocusEntity {


    private DataInfo data;

    public DataInfo getData() {
        return data;
    }

    public void setData(DataInfo data) {
        this.data = data;
    }

    public static class DataInfo {
        private int total;
        private int currentPage;
        private int currentPgeNumber;
        private int pageNumber;
        private int totalPage;
        private boolean hasNextPage;
        private List<FocusInfo> rows;

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

        public List<FocusInfo> getRows() {
            return rows;
        }

        public void setRows(List<FocusInfo> rows) {
            this.rows = rows;
        }

    }
}
