package com.jinshu.homelibrary.entity;

import java.util.List;

/**
 * Create on 2019/11/26 15:51 by bll
 */


public class SearchDefineData {


    /**
     * data : {"total":90,"currentPage":1,"currentPgeNumber":10,"pageNumber":10,"totalPage":9,"hasNextPage":true,"rows":[{"searchDefineID":"402881f76e869017016e8699168c000a","name":null,"tipText":null,"objectDefineID":"8af5993a4fdaf145014fde16a40800d9","objectDefineName":"应用"},{"searchDefineID":"402881f76e869017016e86992db40011","name":"测试应用的搜索定义","tipText":null,"objectDefineID":null,"objectDefineName":null},{"searchDefineID":"402881f76e869017016e869a76730044","name":null,"tipText":null,"objectDefineID":"8af5993a4fdaf145014fde16a40800d9","objectDefineName":"应用"},{"searchDefineID":"402881f76e869017016e869a8f9b004b","name":"测试应用的搜索定义","tipText":null,"objectDefineID":null,"objectDefineName":null},{"searchDefineID":"8a2f462a62a9a4060162a9b586ed005d","name":"12333333","tipText":"3333","objectDefineID":"8a2f462a5fd91264015fdcaec9fa0c26","objectDefineName":"svn操作文件"},{"searchDefineID":"8a2f462a6385f20a016386a1cf2f06f5","name":"首页搜索","tipText":"搜索商品","objectDefineID":"8a2f462a5828485d015828c32a1f0373","objectDefineName":"商品goods"},{"searchDefineID":"8a2f462a646eff9a01646f9b2ef10b84","name":"拍卖首页搜索","tipText":"搜到你想要的","objectDefineID":"8a2f462a5b4b67be015b4be8b7080dfa","objectDefineName":"招标拍卖"},{"searchDefineID":"8a2f462a647d086601647d5461a60704","name":"文章","tipText":"输入文字搜资讯","objectDefineID":"8af5993a4fba5335014fbb0c8e7e00b0","objectDefineName":"文章"},{"searchDefineID":"8a2f462a647d086601647d552d370715","name":"案例","tipText":"输入文字搜案例","objectDefineID":"8af5993a512ce9a201512e7d84b60873","objectDefineName":"故事"},{"searchDefineID":"8a2f462a647d086601647d5cc6f70767","name":"视频","tipText":"请输入文字搜索视频","objectDefineID":"8af5993a4fba5335014fbb0c8e7e00b0","objectDefineName":"文章"}]}
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
         * total : 90
         * currentPage : 1
         * currentPgeNumber : 10
         * pageNumber : 10
         * totalPage : 9
         * hasNextPage : true
         * rows : [{"searchDefineID":"402881f76e869017016e8699168c000a","name":null,"tipText":null,"objectDefineID":"8af5993a4fdaf145014fde16a40800d9","objectDefineName":"应用"},{"searchDefineID":"402881f76e869017016e86992db40011","name":"测试应用的搜索定义","tipText":null,"objectDefineID":null,"objectDefineName":null},{"searchDefineID":"402881f76e869017016e869a76730044","name":null,"tipText":null,"objectDefineID":"8af5993a4fdaf145014fde16a40800d9","objectDefineName":"应用"},{"searchDefineID":"402881f76e869017016e869a8f9b004b","name":"测试应用的搜索定义","tipText":null,"objectDefineID":null,"objectDefineName":null},{"searchDefineID":"8a2f462a62a9a4060162a9b586ed005d","name":"12333333","tipText":"3333","objectDefineID":"8a2f462a5fd91264015fdcaec9fa0c26","objectDefineName":"svn操作文件"},{"searchDefineID":"8a2f462a6385f20a016386a1cf2f06f5","name":"首页搜索","tipText":"搜索商品","objectDefineID":"8a2f462a5828485d015828c32a1f0373","objectDefineName":"商品goods"},{"searchDefineID":"8a2f462a646eff9a01646f9b2ef10b84","name":"拍卖首页搜索","tipText":"搜到你想要的","objectDefineID":"8a2f462a5b4b67be015b4be8b7080dfa","objectDefineName":"招标拍卖"},{"searchDefineID":"8a2f462a647d086601647d5461a60704","name":"文章","tipText":"输入文字搜资讯","objectDefineID":"8af5993a4fba5335014fbb0c8e7e00b0","objectDefineName":"文章"},{"searchDefineID":"8a2f462a647d086601647d552d370715","name":"案例","tipText":"输入文字搜案例","objectDefineID":"8af5993a512ce9a201512e7d84b60873","objectDefineName":"故事"},{"searchDefineID":"8a2f462a647d086601647d5cc6f70767","name":"视频","tipText":"请输入文字搜索视频","objectDefineID":"8af5993a4fba5335014fbb0c8e7e00b0","objectDefineName":"文章"}]
         */

        private int total;
        private int currentPage;
        private int currentPgeNumber;
        private int pageNumber;
        private int totalPage;
        private boolean hasNextPage;
        private List<SearchDefineInfo> rows;

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

        public List<SearchDefineInfo> getRows() {
            return rows;
        }

        public void setRows(List<SearchDefineInfo> rows) {
            this.rows = rows;
        }
    }
}
