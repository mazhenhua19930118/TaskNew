package com.example.task.http;

import lombok.Data;

/**
 * 微信接口 获取凭证 返回
 * @author c_zhouyaowu
 *
 */
@Data
public class WxAccessTokenRes {

	private String access_token; //获取到的凭证
	
	private String expires_in; //凭证有效时间，单位：秒。目前是7200秒之内的值。
	
	private String errcode; //错误码
	
	private String errmsg; //错误信息
}
