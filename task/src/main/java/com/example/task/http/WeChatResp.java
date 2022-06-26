package com.example.task.http;

import lombok.Data;

@Data
public class WeChatResp {
    private String errcode;
    private String errmsg;
    private String openlink;
    private String linkId;
}
