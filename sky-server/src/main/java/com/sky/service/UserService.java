package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;

/**
 * ClassName:UserService
 * Description:
 *
 * @Author:AlanJake
 * @Create:2025/7/3下午10:39
 * @Version:
 */
public interface UserService {
  User wxlogin(UserLoginDTO userLoginDTOu);
}
