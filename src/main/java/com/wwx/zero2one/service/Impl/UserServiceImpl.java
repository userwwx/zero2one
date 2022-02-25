package com.wwx.zero2one.service.Impl;

import com.wwx.zero2one.config.security.JwtTokenUtil;
import com.wwx.zero2one.controller.VO.UserVO;
import com.wwx.zero2one.entity.User;
import com.wwx.zero2one.service.UserService;
import com.wwx.zero2one.util.ReturnData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.wwx.zero2one.dao.UserDAO;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public UserDAO userdao;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ReturnData login(UserVO userVO) {
        User user = (User) userDetailsService.loadUserByUsername(userVO.username);
        if (null == user || !passwordEncoder.matches(userVO.password, user.getPassword())) {
            return ReturnData.ok(null, "用户名或密码错误");
        }
        //更新security登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        String token = jwtTokenUtil.generateToken(user);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        user.setToken(token);
        userdao.updateByPrimaryKeySelective(user);
        return ReturnData.ok(tokenMap, "登录成功");
    }


    @Override
    public ReturnData register(String username, String password) {

        Optional<User> userOpt = Optional.ofNullable(userdao.selectUserByPhone(username));
        if (userOpt.isPresent()) {
            return ReturnData.ok(null, "该手机号已注册");
        }
        User user = userOpt.orElseGet(User::new);
        user.setPhoneNumber(username);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        int insert = userdao.insert(user);
        return ReturnData.ok(null, "注册成功，请登录");
    }
}
