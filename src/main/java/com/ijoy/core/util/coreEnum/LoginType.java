package com.ijoy.core.util.coreEnum;

public enum LoginType
{
  PhoneLogin(0), EmailLogin(1);

  private Integer code;

  private LoginType(int number) { this.code = Integer.valueOf(number); }

  public Integer getNumberCode() {
    return this.code;
  }
  public void setNumberCode(int numberCode) {
    this.code = Integer.valueOf(numberCode);
  }
}