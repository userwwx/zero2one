package com.wwx.zero2one.service.Impl;

import com.wwx.zero2one.controller.VO.UserVO;
import com.wwx.zero2one.entity.User;
import com.wwx.zero2one.service.UserService;
import com.wwx.zero2one.util.ReturnData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wwx.zero2one.dao.UserDAO;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    public UserDAO userdao;

    @Override
    public ReturnData login(UserVO userVO) {
        User user = userdao.selectUserByPhone(userVO.username);
        return ReturnData.ok(null, verifyPassword(user.getPassword(), userVO.password));
    }

    private String verifyPassword(String dbPassword, String password) {
        return dbPassword.equals(password) ? "登录成功" : "密码错误!";
    }

    @Override
    public ReturnData register(String username, String password) {
        User user = new User();
        user.setPhoneNumber(username);
        user.setUsername(username);
        user.setPassword(password);
        int insert = userdao.insert(user);

        return ReturnData.ok();
    }
}
