package com.wwx.zero2one.controller;

import com.wwx.zero2one.controller.VO.UserVO;
import com.wwx.zero2one.service.UserService;
import com.wwx.zero2one.util.ReturnData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ReturnData register(@RequestBody UserVO userVO) {

        return userService.register(userVO.username, userVO.password);
    }

    @PostMapping("/login")
    public ReturnData login(@RequestBody UserVO user) {
        return userService.login(user);
    }
}
