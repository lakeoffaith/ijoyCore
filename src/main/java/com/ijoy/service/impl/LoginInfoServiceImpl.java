package com.ijoy.service.impl;

import com.ijoy.core.util.Base64Util;
import com.ijoy.core.util.coreEnum.LoginType;
import com.ijoy.model.LoginInfo;
import com.ijoy.model.User;
import com.ijoy.service.ILoginInfoService;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

@Service
public class LoginInfoServiceImpl extends BaseServiceImpl
  implements ILoginInfoService
{

  @Autowired
  private Mapper<LoginInfo> loginInfoMapper;

  @Autowired
  private Mapper<User> userMapper;

  public String getCodeByCellPhone(String Phone)
  {
    logger.debug("根据手机号获取验证码");
    Random r = new Random();
    String code = String.valueOf(r.nextInt(9999));
    String loginName = Phone;

    LoginInfo loginInfo = new LoginInfo();
    loginInfo.setCode(code);
    loginInfo.setLoginType(LoginType.PhoneLogin.getNumberCode());
    loginInfo.setLoginName(Phone);
    this.loginInfoMapper.insert(loginInfo);

    return code;
  }

  public String loginByPhoneAndCode(String Phone, String code) {
    logger.debug("根据手机号和验证码登录");

    LoginInfo n = new LoginInfo();
    n.setLoginName(Phone);
    n.setCode(code);
    List loginInfos = this.loginInfoMapper.select(n);
    LoginInfo loginInfo = (LoginInfo)loginInfos.get(0);
    System.out.println(loginInfo);
    int userId=loginInfo.getUserId()!=null?loginInfo.getUserId():0;
    
   if (userId == 0)
    {
	   User user = new User();
	      user.setUserName(Phone);
	      userId = this.userMapper.insert(user);
    }
    

    loginInfo.setUserId(Integer.valueOf(userId));
    loginInfo.setLastLoginTime(new Date());
    this.loginInfoMapper.updateByPrimaryKey(loginInfo);

    String token = Base64Util.encode(userId + ":" + new Date());
    return token;
  }

  public boolean checkToken(String token) {
    logger.debug("检查验证码");
    String t = Base64Util.decode(token);
    String id = t.split(":")[0];
    return this.userMapper.existsWithPrimaryKey(Integer.valueOf(id));
  }

public List<LoginInfo> findAll() {
	return loginInfoMapper.select(null);
}

public List<User> findUserAll() {
	// TODO Auto-generated method stub
	return userMapper.select(null);
}
}