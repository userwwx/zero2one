package com.wwx.zero2one.service;

import com.wwx.zero2one.controller.VO.UserVO;
import com.wwx.zero2one.util.ReturnData;

public interface UserService {


    /**
     * user login interface
     * @param username
     * @param password
     * @return common return data
     */
    ReturnData login(UserVO userVO);

    /**
     * user register interface
     * @param username
     * @param password
     * @return common return data
     */
    ReturnData register(String username, String password);

}
