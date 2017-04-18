package com.ijoy.core.util.coreEnum;

public enum ResourceType
{
  Menu(0), Button(1);

  private int i;

  private ResourceType(int i) { this.i = i; }

  public int value()
  {
    return this.i;
  }
}