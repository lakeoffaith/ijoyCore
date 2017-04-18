package core;

import com.ijoy.service.impl.UserServiceImpl;
import java.io.PrintStream;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-context.xml"})
public class UserServiceTest
{

  @Autowired
  private UserServiceImpl userServiceImpl;

  @Test
  public void getResourceTest()
  {
    List list = this.userServiceImpl.getMenusByUserId(1);
    System.out.println(list);
  }
}