package com.ijoy.mapper;

import com.ijoy.model.Resource;
import com.ijoy.model.User;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public abstract interface UserMapper extends Mapper<User>
{
  public abstract List<Resource> getResource(@Param("userId") int paramInt1, @Param("resourceType") int paramInt2);
}