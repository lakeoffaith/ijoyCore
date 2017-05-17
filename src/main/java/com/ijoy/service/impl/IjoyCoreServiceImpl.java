package com.ijoy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ijoy.core.util.Base64Util;
import com.ijoy.core.util.Md5Util;
import com.ijoy.core.util.coreEnum.LoginType;
import com.ijoy.core.util.coreEnum.ResourceType;
import com.ijoy.mapper.ResourceMapper;
import com.ijoy.mapper.RoleMapper;
import com.ijoy.mapper.UserMapper;
import com.ijoy.model.LoginInfo;
import com.ijoy.model.Resource;
import com.ijoy.model.Role;
import com.ijoy.model.User;
import com.ijoy.service.IjoyCoreService;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

@Service
public class IjoyCoreServiceImpl extends BaseServiceImpl
  implements IjoyCoreService
{

  @Autowired
  private Mapper<LoginInfo> loginInfoMapper;

  @Autowired
  private Mapper<User> userMapper;
  
  @Autowired
  private ResourceMapper resourceMapper;
  
  @Autowired
  private RoleMapper roleMapper;

  @Transactional
  public String getCodeByCellPhone(String Phone)
  {
    logger.debug("根据手机号获取验证码");
    int num=(int) (Math.random()*(9999-1000)+1000);
    String code = String.valueOf(num);
    String loginName = Phone;
    
    LoginInfo loginInfo =null;
    Example example=new Example(LoginInfo.class);
    example.createCriteria().andEqualTo("loginName", Phone);
    List<LoginInfo> selectByExample = loginInfoMapper.selectByExample(example);
    
    if(selectByExample!=null && selectByExample.size()>0){
    	loginInfo=selectByExample.get(0);
    	 //如果此号码存在，则更新 code,
    	loginInfo.setLoginType(LoginType.PhoneLogin.getNumberCode());
        loginInfo.setCode(code);
      
        this.loginInfoMapper.updateByExample(loginInfo, example);
    	
    }else{
    	loginInfo= new LoginInfo();
    	loginInfo.setLoginType(LoginType.PhoneLogin.getNumberCode());
        loginInfo.setCode(code);
        loginInfo.setLoginName(Phone);
        this.loginInfoMapper.insert(loginInfo);
    }
    return code;
  }

  @Transactional
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
	       this.userMapper.insert(user);
	       userId =user.getId();
    }
    
   String token = Base64Util.encode(userId + ":" + new Date().getTime());
    loginInfo.setUserId(Integer.valueOf(userId));
    loginInfo.setLastLoginTime(new Date());
    loginInfo.setToken(token);
    this.loginInfoMapper.updateByPrimaryKey(loginInfo);

    
    return token;
  }
  @Transactional
  public User checkToken(String token) {
    logger.debug("检查验证码");
    Example example=new Example(LoginInfo.class);
    
    String t = Base64Util.decode(token);
    String id = t.split(":")[0];
    example.createCriteria().andEqualTo("userId", id)
    .andEqualTo("token",token);
    List<LoginInfo> list = loginInfoMapper.selectByExample(example);
    Integer userId = list.get(0).getUserId();
    User user = userMapper.selectByPrimaryKey(userId);
    return user;
  }
  @Transactional
public List<Resource> getMenusByUserId(int userId)
{
  logger.debug("根据用户的Id获取菜单");
  return ((UserMapper) userMapper).getResource(userId, ResourceType.Menu.value());
}
  @Transactional
public List<Resource> getButtonsByUserId(int userId) {
  logger.debug("根据用户的Id获取菜单");
  return ((UserMapper) userMapper).getResource(userId, ResourceType.Button.value());
}
  @Transactional
public Boolean registerByUserNameAndPassword(String userName, String password) {
	//保存到logininfo
	  //检查userName 是否存在logininfo
	  LoginInfo varInfo=checkLoginNameExist(userName);
	  //如果存在退出
	  if(varInfo!=null)return false;
	  LoginInfo info=new LoginInfo();
	  info.setLoginName(userName);
	  try {
		info.setPassword(Md5Util.encrypt(password));
	} catch (Exception e) {
		return false;
	}
	  loginInfoMapper.insert(info);
	  return true;
}


@Transactional
public String loginByUserNameAndPassword(String userName, String password) {
	// TODO Auto-generated method stub
	//验证 loginInfo
	LoginInfo info=checkLoginNameExist(userName);
	if(info==null)return "";
	try {
		Boolean validPassword = Md5Util.validPassword(password, info.getPassword());
		if(!validPassword)return "";
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//插入 user
	int userId=info.getUserId()!=null?info.getUserId():0;
    
	   if (userId == 0)
	    {
		   	User user = new User();
		      user.setUserName(userName);
		       userMapper.insert(user);
		       userId =user.getId();
	    }
	    
	   String token = Base64Util.encode(userId + ":" + new Date().getTime());
	   info.setUserId(Integer.valueOf(userId));
	   info.setLastLoginTime(new Date());
	   info.setToken(token);
	   info.setLoginType(1);
	   loginInfoMapper.updateByPrimaryKey(info);
	//获得token,更新loginInfo
	
	return token;
}
  @Transactional
public List<Integer> initUrlResource(String ResourceTableName, List<Resource> resources) {
	  List<Integer> ids=new ArrayList();
	  for (Resource resource : resources) {
			 resourceMapper.insert(resource);
			 ids.add(resource.getId());
		}
		return ids;
}

 private LoginInfo checkLoginNameExist(String userName) {
	// TODO Auto-generated method stub
	 Example example=new Example(LoginInfo.class);
	 example.createCriteria().andEqualTo("loginName", userName);
	 List<LoginInfo> selectByExample = loginInfoMapper.selectByExample(example);
	 if(selectByExample==null || selectByExample.size()==0){
		 return null;
	 }
	return selectByExample.get(0);
}

public Role insertRole(Role role) {
	// TODO Auto-generated method stub
	roleMapper.insert(role);
	return role;
}

public Boolean linkRole(int userId, int roleId) {
	try{
		roleMapper.linkRole(userId,roleId);
		return true;
	}catch(Exception e){
		return false;
	}
}

public Boolean linkResource(int roleId, int resourceId) {
	try{
		resourceMapper.linkResource(roleId,resourceId);
		return true;
	}catch(Exception e){
		return false;
	}
	 
}
  

}