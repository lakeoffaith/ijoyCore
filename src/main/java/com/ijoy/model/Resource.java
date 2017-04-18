package com.ijoy.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="resource")
public class Resource implements Serializable
{

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;
  private String url;
  private String action;
  private Integer type;

  public Integer getId()
  {
    return this.id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public String getUrl()
  {
    return this.url;
  }

  public void setUrl(String url)
  {
    this.url = url;
  }

  public String getAction()
  {
    return this.action;
  }

  public void setAction(String action)
  {
    this.action = action;
  }

  public Integer getType() {
    return this.type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String toString()
  {
    return "Resource [id=" + this.id + ", url=" + this.url + ", action=" + this.action + ", type=" + this.type + "]";
  }
}