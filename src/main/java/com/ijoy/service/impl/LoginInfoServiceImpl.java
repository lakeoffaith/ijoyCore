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
import tk.mybatis.mapper.entity.Example;

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
    
    LoginInfo loginInfo =null;
    Example example=new Example(LoginInfo.class);
    example.createCriteria().andEqualTo("loginName", Phone);
    loginInfo=loginInfoMapper.selectByExample(example).get(0);
    
    if(loginInfo!=null){
    	 //如果此号码存在，则更新 code,
    	loginInfo.setLoginType(LoginType.PhoneLogin.getNumberCode());
        loginInfo.setCode(code);
      
        this.loginInfoMapper.updateByExample(loginInfo, example);
    	
    }else{
    	loginInfo= new LoginInfo();
    	loginInfo.setLoginType(LoginType.PhoneLogin.getNumberCode());
        loginInfo.setCode(code);
        this.loginInfoMapper.insert(loginInfo);
    }
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
    
   String token = Base64Util.encode(userId + ":" + new Date());
    loginInfo.setUserId(Integer.valueOf(userId));
    loginInfo.setLastLoginTime(new Date());
    loginInfo.setToken(token);
    this.loginInfoMapper.updateByPrimaryKey(loginInfo);

    
    return token;
  }

  public User checkToken(String token) {
    logger.debug("检查验证码");
    Example example=new Example(LoginInfo.class);
    
    String t = Base64Util.decode(token);
    String id = t.split(":")[0];
    example.createCriteria().andEqualTo("userId", id)
    .andEqualTo("token",token);
    List<LoginInfo> list = loginInfoMapper.selectByExample(example);
    Integer userId = list.get(0).getId();
    User user = userMapper.selectByPrimaryKey(userId);
    return user;
  }

public List<LoginInfo> findAll() {
	return loginInfoMapper.select(null);
}

public List<User> findUserAll() {
	// TODO Auto-generated method stub
	return userMapper.select(null);
}
}