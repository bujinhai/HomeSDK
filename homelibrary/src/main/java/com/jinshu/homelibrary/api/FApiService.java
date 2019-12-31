package com.jinshu.homelibrary.api;


import com.jinshu.homelibrary.basebean.FBaseResponse;
import com.jinshu.homelibrary.entity.FUserEntity;
import com.jinshu.homelibrary.entity.FocusEntity;
import com.jinshu.homelibrary.entity.HistoryData;
import com.jinshu.homelibrary.entity.HotWordData;
import com.jinshu.homelibrary.entity.SearchDefineData;
import com.jinshu.homelibrary.entity.ShopDetailEntity;
import com.jinshu.homelibrary.entity.ShopEntity;
import com.jinshu.homelibrary.entity.VersionData;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Create on 2019/9/10 15:10 by bll
 */


public interface FApiService {

    /**
     * 登录
     */
    @GET("memberLogin.json")
    Observable<FBaseResponse<FUserEntity>> login(
            @Query("loginName") String loginName,
            @Query("password") String password,
            @QueryMap Map<String, String> map);

    @GET("getVersionDetail.json")
    Observable<FBaseResponse<VersionData>> getVersionDetail(
            @Query("siteID") String siteID);

    /**
     * 历史搜索
     */
    @GET("getMemberSearchList.json")
    Observable<FBaseResponse<HistoryData>> getMemberSearchList(
            @Query("sessionID") String sessionID,
            @Query("objectDefineID") String objectDefineID,
            @Query("sortTypeTime") String sortTypeTime,
            @Query("siteID") String siteID);

    /**
     * 热词搜索
     */
    @GET("getHotWordList.json")
    Observable<FBaseResponse<HotWordData>> getHotWordList(
            @Query("sessionID") String sessionID,
            @Query("searchDefineID") String searchDefineID,
            @Query("sortTypeOrder") String sortTypeOrder);

    /**
     * 获取搜索定义列表
     */
    @GET("getSearchDefineList.json")
    Observable<FBaseResponse<SearchDefineData>> getSearchDefineList(
            @Query("sessionID") String sessionID);

    /**
     * 删除我的搜索历史记录
     */
    @GET("deleteMyMemberSearch.json")
    Observable<FBaseResponse> deleteMyMemberSearch(
            @Query("sessionID") String sessionID);

    /**
     * 获取店铺列表
     */
    @GET("getShopList.json")
    Observable<FBaseResponse<ShopEntity>> getShopList(
            @Query("sessionID") String sessionID,
            @Query("applicationID") String applicationID,
            @Query("currentPage") int currentPage,
            @Query("pageNumber") int pageNumber,
            @Query("sortTypeTime") String sortTypeTime);

    /**
     * 获取店铺详细信息
     */
    @GET("getShopDetail.json")
    Observable<FBaseResponse<ShopDetailEntity>> getShopDetail(
            @Query("sessionID") String sessionID,
            @Query("shopID") String shopID);


    /**
     * 获取轮播图列表
     */
    @GET("getFocusPictureList.json")
    Observable<FBaseResponse<FocusEntity>> getFocusPictureList(
            @Query("sessionID") String sessionID,
            @Query("focusID") String focusID);

    /**
     * 下载文件
     *
     * @return
     */
    @Streaming
    @GET
    Observable<ResponseBody> downLoadFile(@Url String url);
}
