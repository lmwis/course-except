package com.fehead.course.login;

import com.fehead.course.dao.PasswordMapper;
import com.fehead.course.dao.UserMapper;
import com.fehead.course.dao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author lmwis
 * @description: 实现springsecurity用户校验，自定义用户认证流程
 * @date 2019-08-29 16:08
 * @Version 1.0
 */

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordMapper passwordMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userMapper.selectByUsername(s);
        if (user == null){
            user = userMapper.selectByUserTel(s);
        }
        if(user == null){
            throw new UsernameNotFoundException("用户名或密码不正确");
        }

        return new org.springframework.security.core.userdetails.User(String.valueOf(user.getId())
                ,passwordMapper.selectById(user.getPasswordId()).getPassword(), AuthorityUtils.createAuthorityList("admin"));
    }
}
