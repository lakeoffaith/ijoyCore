package core;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ijoy.model.Resource;
import com.ijoy.model.Role;
import com.ijoy.model.User;
import com.ijoy.service.IjoyCoreService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml" })
public class LoginInfoServiceTest{

	@Autowired
	private IjoyCoreService service;
	@Test
	public void getCodeByCellPhoneTest() {
		String phone="1234566666";
		String code = service.getCodeByCellPhone(phone);
		Assert.assertTrue(code.length()==4);;
		String token = service.loginByPhoneAndCode(phone, code);
		Assert.assertTrue(token.length()>5);
		User user = service.checkToken(token);
		Assert.assertTrue(user.getUserName().equals(phone));
	}

	@Test
	public void registerByUserNameAndPassword() {
		String userName="userName"+Math.random()*1000;
		Boolean flag = service.registerByUserNameAndPassword(userName, "123");
		Assert.assertTrue(flag);
		String token = service.loginByUserNameAndPassword(userName, "123");
		Assert.assertTrue(token.length()>5);
		User user = service.checkToken(token);
		Assert.assertTrue(user.getUserName().equals(userName));
		List<Integer> resourceIds=initUrlResource();
		Role role=new Role();
		role.setName("测试角色");
		role= service.insertRole(role);
		Assert.assertTrue(role.getId()>0);
		Assert.assertTrue(service.linkRole(user.getId(), role.getId()));
		for(Integer i:resourceIds){
			Assert.assertTrue(service.linkResource(role.getId(), i));
		}
		List<Resource> menusByUserId = service.getMenusByUserId(user.getId());
		Assert.assertTrue(menusByUserId.get(0).getUrl().equals("/test/"));
		List<Resource> buttonsByUserId = service.getButtonsByUserId(user.getId());
		Assert.assertTrue(buttonsByUserId.get(0).getAction().equals("editBtn"));
	}

	
	public List<Integer> initUrlResource() {
		List<Resource> resources=new ArrayList();
		Resource r=new Resource();
		r.setUrl("/test/");
		r.setType(0);
		resources.add(r);
		Resource b=new Resource();
		b.setAction("editBtn");
		b.setType(1);
		resources.add(b);
		List<Integer> ids= service.initUrlResource("", resources);
		System.out.println(ids);
		
		Assert.assertTrue(ids.size()>0);
		return ids;
	}


}