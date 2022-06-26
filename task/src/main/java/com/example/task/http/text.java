package com.example.task.http;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;

public class text {
    public static void main(String[] args) {
        CommonHttps https = new CommonHttps();
        String accessToken = https.httpsRequest("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx3ba206772c26a0eb&secret=31ca3432939c71a70fa5db8183775c26", "GET", null);
        System.out.println("调用微信获取的accessToken为："+accessToken);
        //调用微信接口获取中转H5
        WxAccessTokenRes wxAccessTokenRes = JSON.parseObject(new String(accessToken), WxAccessTokenRes.class);
        WeChatReq weChatReq = new WeChatReq();
        WeChatInfo weChatInfo = new WeChatInfo();
        weChatInfo.setPath("pages/index/main");
        weChatInfo.setQuery("linkId=P17d437d841-cafb-46ea-82ff-a27ff5d3d5be");
        weChatInfo.setEnv_version("trial");
        weChatReq.setJump_wxa(weChatInfo);
        weChatReq.setIs_expire(true);
        weChatReq.setExpire_type(0);
        weChatReq.setExpire_time(new Date().getTime()/1000+300);
        String jsonString = JSONObject.toJSONString(weChatReq);
        System.out.println(jsonString);
        String json = https.httpsRequest("https://api.weixin.qq.com/wxa/generatescheme?access_token="+wxAccessTokenRes.getAccess_token(), "POST", jsonString);
        WeChatResp we = JSON.parseObject(new String(json), WeChatResp.class);
        System.out.println(json);
        System.out.println("调用微信接口获取数据"+we.getOpenlink());



    }
}
