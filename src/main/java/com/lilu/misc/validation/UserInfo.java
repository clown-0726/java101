package com.lilu.misc.validation;


import org.hibernate.validator.constraints.Length;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.groups.Default;
import java.util.Date;
import java.util.List;

public class UserInfo {
    // 验证场景分组
    public interface LoginGroup { }

    public interface RegisterGroup { }

    // 组序列验证方式，可以按照排序的验证组，从上到下进行验证，
    // 如果第一个验证不通过，则停止校验。
    @GroupSequence({
            LoginGroup.class,
            RegisterGroup.class,
            Default.class
    })
    public interface Group { }


    @NotNull(message = "用户 ID 不能为空")
    private String userId;

    // NotEmpty 不会自动去掉前后空格
    @NotEmpty(message = "用户名不能为空")
    private String userName;

    // NotBlank 自动去掉字符串前后空格验证是否为空
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 20, message = "长度不对")
    private String password;

    @NotNull(message = "邮箱不能为空")
    @Email(message = "邮箱验证错误")
    private String email;

    @Past(message = "不能为未来日期")
    private Date birthday;

    // @Valid 表示进行级联验证
    @Size(min = 1, message = "内容不能少于一个")
    private List<@Valid UserInfo> userInfoList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }
}
