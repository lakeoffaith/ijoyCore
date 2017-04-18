package com.ijoy.core.util;

import org.apache.commons.codec.binary.Base64;

public class Base64Util
{
  public static String encode(String arg1)
  {
    byte[] binaryData = arg1.getBytes();
    return Base64.encodeBase64String(binaryData);
  }

  public static String decode(String arg1)
  {
    return new String(Base64.decodeBase64(arg1));
  }
}