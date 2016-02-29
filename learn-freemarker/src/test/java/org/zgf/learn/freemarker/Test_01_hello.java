package org.zgf.learn.freemarker;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 *  入门程序 
 */
public class Test_01_hello {
	
	@Test
	public void test_1() throws Exception{
//		1. 创建配置类
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
//			1.1 设置模板加载目录
		cfg.setDirectoryForTemplateLoading(new File("src/test/resources/templates"));
//			1.2 设置编码
		cfg.setDefaultEncoding("UTF-8");
//			1.3 设置模板更新延迟时间
		cfg.setTemplateUpdateDelayMilliseconds(0);
		
//		2. 创建数据模型:模型数据一般用一个map 来构建
		Map<String,Object> root = new HashMap<>();
		root.put("title", "Hello World");
		root.put("name", "键盘上的幽灵");
		root.put("date", new Date());
//		3. 加载模板文件
		Template template = cfg.getTemplate("hello.ftl");
		
//		4. 整合数据 和 模板，输出
		Writer out = new OutputStreamWriter(System.out); 
		template.process(root, out);
		out.flush();
		out.close();
	}

}
