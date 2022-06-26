package com.example.task.http;


import lombok.Data;


public class WeChatReq {
    private WeChatInfo jump_wxa;//跳转到的目标小程序信息。
    private boolean is_expire;//生成的 scheme 码类型，到期失效：true，永久有效：false。注意，永久有效 scheme 和有效时间超过31天的到期失效 scheme 的总数上限为10万个，详见获取 URL scheme，生成 scheme 码前请仔细确认
    private Integer expire_type;//到期失效的 scheme 码失效类型，失效时间：0，失效间隔天数：1
    private long expire_time;//到期失效的 scheme 码的失效时间，为 Unix 时间戳。生成的到期失效 scheme 码在该时间前有效。最长有效期为1年。is_expire 为 true 且 expire_type 为 0 时必填
    private Integer expire_interval;//到期失效的 scheme 码的失效间隔天数。生成的到期失效 scheme 码在该间隔时间到达前有效。最长间隔天数为365天。is_expire 为 true 且 expire_type 为 1 时必填

    public WeChatInfo getJump_wxa() {
        return jump_wxa;
    }

    public void setJump_wxa(WeChatInfo jump_wxa) {
        this.jump_wxa = jump_wxa;
    }

    public boolean isIs_expire() {
        return is_expire;
    }

    public void setIs_expire(boolean is_expire) {
        this.is_expire = is_expire;
    }

    public Integer getExpire_type() {
        return expire_type;
    }

    public void setExpire_type(Integer expire_type) {
        this.expire_type = expire_type;
    }

    public long getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(long expire_time) {
        this.expire_time = expire_time;
    }

    public Integer getExpire_interval() {
        return expire_interval;
    }

    public void setExpire_interval(Integer expire_interval) {
        this.expire_interval = expire_interval;
    }
}
