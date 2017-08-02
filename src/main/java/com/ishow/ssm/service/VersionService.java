package com.ishow.ssm.service;

import com.ishow.ssm.model.VerifyCode;
import com.ishow.ssm.model.Version;

/**
 * 验证码
 */
public interface VersionService {

    Version getVersion(int deviceType);

}
