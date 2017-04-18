package com.ijoy.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="user")
public class User implements Serializable
{

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column(name="user_name")
  private String userName;

  public Integer getId()
  {
    return this.id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public String getUserName()
  {
    return this.userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }
}