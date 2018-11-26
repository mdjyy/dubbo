package mybatis.test;

import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mdj.bean.User;
import com.mdj.service.interfaces.IUserService;

public class TestMybatis {
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IUserService service = context.getBean("userService",IUserService.class);
	    User u = service.getUser("admin", "admin");
	    Map map = service.addUser(u);
	    System.out.println(map);
	}
}
