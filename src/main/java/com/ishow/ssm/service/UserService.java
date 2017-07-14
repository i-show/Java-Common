package com.ishow.ssm.service;

import com.ishow.ssm.model.User;

import java.util.List;

/**
 * 用户信息
 */
public interface UserService {

    List<User> getAllUser();

    User getUserByPhoneOrEmail(String emailOrPhone, Short state);

    User getUserById(Long userId);
}
