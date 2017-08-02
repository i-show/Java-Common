package com.ishow.ssm.dao;

import com.ishow.ssm.model.Version;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 验证码
 */
@Repository
public interface VersionDao {

    Version getVersion(@Param("deviceType") int deviceType);

}
