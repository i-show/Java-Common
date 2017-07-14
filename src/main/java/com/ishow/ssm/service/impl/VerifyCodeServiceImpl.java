package com.ishow.ssm.service.impl;

import com.ishow.ssm.dao.UserDao;
import com.ishow.ssm.dao.VerifyCodeDao;
import com.ishow.ssm.model.User;
import com.ishow.ssm.model.VerifyCode;
import com.ishow.ssm.service.UserService;
import com.ishow.ssm.service.VerifyCodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class VerifyCodeServiceImpl implements VerifyCodeService {
    @Resource
    private VerifyCodeDao dao;

    public VerifyCode getCode(String phone) {
        return dao.getCode(phone);
    }


    public long insertCode(String userId, String phone, String code, long time) {
        return dao.insertCode(userId, phone, code, time);
    }
}
