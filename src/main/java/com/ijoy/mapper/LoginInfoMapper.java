package com.ijoy.mapper;

import com.ijoy.model.LoginInfo;
import tk.mybatis.mapper.common.Mapper;

public abstract interface LoginInfoMapper extends Mapper<LoginInfo>
{
  public abstract LoginInfo getLoginInfoByPhoneAndCode(String paramString1, String paramString2);
}