package com.lilu.misc.validation;

import javax.validation.Valid;

public class UserInfoService {
    public void setUserInfo(@Valid UserInfo userInfo) {
    }

    public @Valid UserInfo getUserInfo() {
        return new UserInfo();
    }

    public UserInfoService() {
    }

    public UserInfoService(@Valid UserInfo userInfo) {
    }
}
