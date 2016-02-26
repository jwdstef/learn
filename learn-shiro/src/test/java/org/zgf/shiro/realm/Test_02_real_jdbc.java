package org.zgf.shiro.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Before;
import org.junit.Test;

/**
 * 测试jdbc 登录
 * @author zonggf
 */
public class Test_02_real_jdbc {
	// 用户信息文件位置
	private String realmLocation = "classpath:realm_jdbc.ini";
	
	//1. 初始化SecurityManager 工厂
	private Factory<SecurityManager> factory = new IniSecurityManagerFactory(realmLocation);
	
	//2. 获取 SecurityManager 实例
	private SecurityManager securityManager = factory.getInstance();
	
	
	@Before
	public void setUp(){
		//3. 绑定SecurityUtils属性
		SecurityUtils.setSecurityManager(securityManager);
	}
	
	/**
	 * 尝试登录
	 * @param upToken 登录令牌
	 * @author zongf
	 * @time 2016年2月24日-下午3:19:46
	 */
	private void testLogin(UsernamePasswordToken token){
		try{
		 Subject currentUser = SecurityUtils.getSubject();
		 currentUser.login(token);
		 System.out.println("jdbcRealm:用户身份验证成功");
		}catch(AuthenticationException ex){
			ex.printStackTrace();
			System.out.println("jdbcRealm:用户身份验证失败");
		}
	}
	
	@Test
	public void test_login_success(){
		//3. 用用户名和密码创建登录令牌
		String username = "zong";
		String password = "123456";
		UsernamePasswordToken token  = new UsernamePasswordToken(username, password);
		
		//尝试登录
		testLogin(token);
	}
	
	@Test
	public void test_login_fail(){
		//3. 用用户名和密码创建登录令牌
		String username = "zong";
		String password = "1111";
		UsernamePasswordToken token  = new UsernamePasswordToken(username, password);
		
		//尝试登录
		testLogin(token);
	}
	
	
	
	
	
	

}
