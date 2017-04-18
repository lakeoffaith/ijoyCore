package com.ijoy;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class generatorMybatis
{
  public static void main(String[] args)
    throws Exception
  {
    List warnings = new ArrayList();
    boolean overwrite = true;
    ConfigurationParser cp = new ConfigurationParser(warnings);
    Configuration config = cp.parseConfiguration(generatorMybatis.class
      .getResourceAsStream("/generator/generatorConfig.xml"));

    DefaultShellCallback callback = new DefaultShellCallback(overwrite);
    MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
    myBatisGenerator.generate(null);
    System.out.println("12");
  }
}