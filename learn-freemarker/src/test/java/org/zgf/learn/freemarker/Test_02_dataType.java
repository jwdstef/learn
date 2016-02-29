package org.zgf.learn.freemarker;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 测试数据类型 
 */
public class Test_02_dataType {
	
	private static Configuration cfg;
	
	private static final String TEMPLATEFILENAME = "src/test/resources/templates";
	
	@BeforeClass
	public static void inite() throws Exception{
		cfg = new Configuration(Configuration.VERSION_2_3_22);
		cfg.setDirectoryForTemplateLoading(new File(TEMPLATEFILENAME));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateUpdateDelayMilliseconds(0);
	}
	
	@Test
	public void test_1() throws Exception{
		//1. 构建数据
		Map<String,Object> root = new HashMap<>();
		//int 类型
		root.put("i", 10);
		//double 类型
		root.put("d", 10.10);
		//boolean 类型
		root.put("b", true);
		//Date 类型
		root.put("date", new Date());
		//String 类型
		root.put("s", "hello");
		//JavaBean
		root.put("depart", createDepartment());
		//List<String>
		root.put("list", Arrays.asList(new String[]{"aaa","bbb","ccc"}));
		//Map<String,String>
		root.put("map", createHashMap());
		
		System.out.println(((Map)root.get("map")).get("A"));
		
		// 2. 加载模板
		Template dateTmp = cfg.getTemplate("datatype.ftl");
		
		// 3. 输出数据
		Writer out = new OutputStreamWriter(System.out); 
		dateTmp.process(root, out);
		out.flush();
		out.close();
		
	}
	
	private Department createDepartment(){
		Department department = new Department();
		department.setId(101);
		department.setName("开发部");
		
		List<Employee> emps = new ArrayList<>();
		for(int i=0; i<10; i++){
			Employee emp = new Employee("zong_" + i, 20 + i, "man");
			emps.add(emp);
		}
		department.setEmployees(emps);
		return department;
	}
	
	private Map<String, String> createHashMap(){
		Map<String, String> map = new HashMap<>();
		map.put("A", "aaaa");
		map.put("B", "bbbb");
		map.put("C", "cccc");
		return map;
	}
	
	
	
}
