package com.ishow.ssm.dao;

import com.ishow.ssm.model.User;
import com.ishow.ssm.model.VerifyCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 验证码
 */
@Repository
public interface VerifyCodeDao {

    VerifyCode getCode(@Param("phone") String phone);

    long insertCode(@Param("userId") String userId, @Param("phone") String phone, @Param("code") String code, @Param("time") long time);

}
