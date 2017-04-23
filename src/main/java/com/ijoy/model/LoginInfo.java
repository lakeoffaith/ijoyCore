package com.ijoy.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="logininfo")
public class LoginInfo implements Serializable
{

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column(name="user_id")
  private Integer userId;
  private String code;

  @Column(name="login_name")
  private String loginName;

  @Column(name="last_login_time")
  private Date lastLoginTime;

  @Column(name="login_type")
  private Integer loginType;
  @Column(name="token")
  private String token;
  
  
  public String getToken() {
	return token;
}

public void setToken(String token) {
	this.token = token;
}

public Integer getId()
  {
    return this.id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public Integer getUserId()
  {
    return this.userId;
  }

  public void setUserId(Integer userId)
  {
    this.userId = userId;
  }

  public String getCode()
  {
    return this.code;
  }

  public void setCode(String code)
  {
    this.code = code;
  }

  public String getLoginName()
  {
    return this.loginName;
  }

  public void setLoginName(String loginName)
  {
    this.loginName = loginName;
  }

  public Date getLastLoginTime()
  {
    return this.lastLoginTime;
  }

  public void setLastLoginTime(Date lastLoginTime)
  {
    this.lastLoginTime = lastLoginTime;
  }

  public Integer getLoginType()
  {
    return this.loginType;
  }

  public void setLoginType(Integer loginType)
  {
    this.loginType = loginType;
  }

  public String toString()
  {
    return "LoginInfo [id=" + this.id + ", userId=" + this.userId + ", code=" + this.code + ", loginName=" + this.loginName + ", lastLoginTime=" + this.lastLoginTime + ", loginType=" + this.loginType + "]";
  }
}