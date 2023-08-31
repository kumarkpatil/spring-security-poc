package com.kmr.springsecuritypoc.service;

import com.kmr.springsecuritypoc.config.UserInfoUserDetails;
import com.kmr.springsecuritypoc.entity.UserInfo;
import com.kmr.springsecuritypoc.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserInfoUserDetailService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = userInfoRepository.findByName(username);

        return userInfo.map(UserInfoUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
