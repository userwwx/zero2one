package com.wwx.zero2one.service;

import com.wwx.zero2one.controller.VO.UserVO;
import com.wwx.zero2one.util.ReturnData;

public interface UserService {



    ReturnData login(UserVO userVO);


    ReturnData register(String username, String password);

}
