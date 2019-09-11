package com.duzj.newses;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

/**
 * @author dzj
 * @create 2019-09-11 16:39
 */
public class Test {
    private String appId = "";
    private String secret = "";
    private String AccessToken = "";

    //获取AccessToken
    private String getAccessTokenUrl  ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+secret;
    //获取特定云环境下集合信息
    private String databaseCollectionGetUrl = "https://api.weixin.qq.com/tcb/databasecollectionget?access_token="+AccessToken;
    //数据库查询记录
    private String databaseQueryUrl = "https://api.weixin.qq.com/tcb/databasequery?access_token="+AccessToken;
    //数据库插入记录
    private String databaseAddUrl = "https://api.weixin.qq.com/tcb/databaseadd?access_token="+AccessToken;

    /**
     * 获取AccessToken
     */
    @org.junit.Test
    public void getAccessToken(){
        String s = HttpUtil.get(getAccessTokenUrl);
        System.out.println(s);
    }

    /**
     * 获取特定云环境下集合信息
     */
    @org.junit.Test
    public void databaseCollectionGet(){
        JSONObject post = new JSONObject(false);
        post.put("env","test-9rk4y");
        System.out.println(post);
        String s = HttpUtil.post(databaseCollectionGetUrl, post.toJSONString());
        System.out.println(s);
        JSONObject data = JSONObject.parseObject(s, Feature.OrderedField);
        data.getString("errcode");
        data.getString("errmsg");
        JSONArray collections = data.getJSONArray("collections");
    }

    /**
     * 数据库查询记录
     */
    @org.junit.Test
    public void databaseQuery(){
        JSONObject post = new JSONObject(false);
        post.put("env","test-9rk4y");
        post.put("query","db.collection(\"newses\").get()");
        System.out.println(post);
        String s = HttpUtil.post(databaseQueryUrl, post.toJSONString());
        System.out.println(s);
        JSONObject data = JSONObject.parseObject(s, Feature.OrderedField);
        data.getString("errcode");
        data.getString("errmsg");
        JSONArray datas = data.getJSONArray("data");
        for (int i = 0; i <datas.size() ; i++) {
            System.out.println(datas.get(i));
        }
    }



    /**
     * 数据库新增记录
     */
    @org.junit.Test
    public void databaseAdd(){
        JSONObject post = new JSONObject(false);
        post.put("env","test-9rk4y");
        post.put("query","db.collection(\"newses\").add({ data: [{main:\"item1\",createDate: new Date(\"2019-09-09\"),title:\"Uber斥资2亿灭源扩大Frieght卡车运输业务\",peopleNo: 1,peopleOk: 2}]})");
        System.out.println(post);
        String s = HttpUtil.post(databaseAddUrl, post.toJSONString());
        System.out.println(s);
        JSONObject data = JSONObject.parseObject(s, Feature.OrderedField);
        data.getString("errcode");
        data.getString("errmsg");
        JSONArray datas = data.getJSONArray("id_list");
        for (int i = 0; i <datas.size() ; i++) {
            System.out.println(datas.get(i));
        }
    }


}
