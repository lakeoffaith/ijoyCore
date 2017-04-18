package core;

import com.ijoy.model.LoginInfo;
import com.ijoy.service.ILoginInfoService;
import java.io.PrintStream;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.mybatis.mapper.common.Mapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-context.xml"})
public class LoginInfoServiceTest
{

  @Autowired
  private ILoginInfoService loginInfoServiceImpl;

  @Autowired
  private Mapper<LoginInfo> mapper;

  @Test
  public void findAllTest()
  {
    List select = this.mapper.select(null);
    System.out.println(select);
  }
  @Test
  public void demoTest() {
    String code = this.loginInfoServiceImpl.getCodeByCellPhone("123");
    System.out.println(code);
  }

  @Test
  public void loginByPhoneAndCodeTest()
  {
    String code = this.loginInfoServiceImpl.loginByPhoneAndCode("123", "2015");
    System.out.println(code);
  }
  @Test
  public void checkTokenTest() {
    String token = "MTpGcmkgQXByIDA3IDE0OjIzOjE3IENTVCAyMDE3";

    boolean flag = this.loginInfoServiceImpl.checkToken(token);
    System.out.println(flag);
  }
}