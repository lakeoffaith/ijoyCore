package com.ijoy.service;

import java.util.List;

import javax.jws.WebService;

import com.ijoy.model.Resource;
import com.ijoy.model.Role;
import com.ijoy.model.User;

@WebService
public abstract interface IjoyCoreService
{
	//根据电话号码获取验证码
  public abstract String getCodeByCellPhone(String phone);
  public abstract Boolean registerByUserNameAndPassword(String userName,String password);
  // 根据验证码和电话号码登录 获得token
  public abstract String loginByPhoneAndCode(String phone, String code);
  //根据用户名和密码登录 获取token 返回为"" 则表示登录不成功
  public abstract String loginByUserNameAndPassword(String userName,String password);
  //通过token 来获取user
  public abstract User checkToken(String paramString);
  //根据子平台的名称创建Resource表和Role_Resource表来保存子平台对应的资源表 
  public List<Integer>  initUrlResource(String ResourceTableName,List<Resource> resources);
  //新增角色
  public Role insertRole(Role role);
  //关联角色
  public Boolean linkRole(int userId,int roleId);
  
  //关联资源
  public Boolean linkResource(int roleId,int resourceId);
  
  //根据用户id来查找出菜单的资源
  public abstract List<Resource> getMenusByUserId(int userId);
  //根据用户id 来查找出按钮的权限
  public abstract List<Resource> getButtonsByUserId(int userId);
  
}