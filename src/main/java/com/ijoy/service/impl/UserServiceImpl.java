package com.ijoy.service.impl;

import com.ijoy.core.util.coreEnum.ResourceType;
import com.ijoy.mapper.UserMapper;
import com.ijoy.model.Resource;
import com.ijoy.model.User;
import com.ijoy.service.IUserService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl
  implements IUserService
{

  @Autowired
  private UserMapper mapper;

  public List<Resource> getMenusByUserId(int userId)
  {
    logger.debug("根据用户的Id获取菜单");
    return this.mapper.getResource(userId, ResourceType.Menu.value());
  }

  public List<Resource> getButtonsByUserId(int userId) {
    logger.debug("根据用户的Id获取菜单");
    return this.mapper.getResource(userId, ResourceType.Button.value());
  }

public List<User> findAll() {
	return mapper.select(null);
}
}