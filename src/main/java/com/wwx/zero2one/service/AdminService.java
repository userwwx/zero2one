package com.wwx.zero2one.service;

import com.wwx.zero2one.entity.User;
import com.wwx.zero2one.util.ReturnData;

public interface AdminService {

    public ReturnData setRole(String phone, Integer roleId);
}
