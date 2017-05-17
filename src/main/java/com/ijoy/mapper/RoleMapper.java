package com.ijoy.mapper;

import com.ijoy.model.Role;
import tk.mybatis.mapper.common.Mapper;

public abstract interface RoleMapper extends Mapper<Role>
{
	public void linkRole(int userId,int roleId);
}