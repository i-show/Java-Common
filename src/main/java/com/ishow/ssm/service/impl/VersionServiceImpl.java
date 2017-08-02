package com.ishow.ssm.service.impl;

import com.ishow.ssm.dao.VerifyCodeDao;
import com.ishow.ssm.dao.VersionDao;
import com.ishow.ssm.model.VerifyCode;
import com.ishow.ssm.model.Version;
import com.ishow.ssm.service.VerifyCodeService;
import com.ishow.ssm.service.VersionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(rollbackFor = Exception.class)
public class VersionServiceImpl implements VersionService {
    @Resource
    private VersionDao dao;

    public Version getVersion(int deviceType) {
        return dao.getVersion(deviceType);
    }
}
