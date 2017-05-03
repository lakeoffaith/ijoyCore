package com.ijoy.service;

import java.util.List;

import javax.jws.WebService;

import com.ijoy.model.Resource;
import com.ijoy.model.User;

@WebService
public abstract interface ILoginInfoService
{
  public abstract String getCodeByCellPhone(String paramString);

  public abstract String loginByPhoneAndCode(String paramString1, String paramString2);

  public abstract User checkToken(String paramString);
  
  public Boolean initUrlResource(List<Resource> resources);
  //public List<LoginInfo> findAll();
  
}