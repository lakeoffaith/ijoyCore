package com.ijoy.service;

import java.util.List;
import javax.jws.WebService;

import com.ijoy.model.Resource;
import com.ijoy.model.User;

@WebService
public abstract interface IUserService
{
  public abstract List<Resource> getMenusByUserId(int paramInt);

  public abstract List<Resource> getButtonsByUserId(int paramInt);
  
}