package com.ishow.ssm.service;

import com.ishow.ssm.model.User;
import com.ishow.ssm.model.VerifyCode;

import java.util.Date;
import java.util.List;

/**
 * 验证码
 */
public interface VerifyCodeService {

    VerifyCode getCode(String phone);
    
    long insertCode(String userId, String phone, String code, long time);
}
