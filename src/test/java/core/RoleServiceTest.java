package core;

import com.ijoy.model.Role;
import java.io.PrintStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.mybatis.mapper.common.Mapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-context.xml"})
public class RoleServiceTest
{

  @Autowired
  private Mapper<Role> mapper;

  @Test
  public void insertTest()
  {
    Role r = new Role();
    r.setName("系统管理者");
    int i = this.mapper.insert(r);
    System.out.println(i);
  }
}