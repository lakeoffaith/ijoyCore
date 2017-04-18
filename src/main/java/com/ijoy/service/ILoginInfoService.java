package com.ijoy.service;

import javax.jws.WebService;

@WebService
public abstract interface ILoginInfoService
{
  public abstract String getCodeByCellPhone(String paramString);

  public abstract String loginByPhoneAndCode(String paramString1, String paramString2);

  public abstract boolean checkToken(String paramString);
  
  //public List<LoginInfo> findAll();
  
}