package com.lc.store.utils;

import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 利用工厂模式给dao层解耦合
 * @author user LC
 *
 */
public class BeanFactory {

	//利用工厂模式给项目解耦合，提高项目的通用性，保证项目可以在不同的数据库上运行
	public static Object creatObject(String name) {
		
		try {
			//解析xml
			SAXReader reader = new SAXReader();
			//获取当前类的输入流
			InputStream is = BeanFactory.class.getClassLoader().getResourceAsStream("application.xml");
			Document doc = reader.read(is);
			Element rootElement = doc.getRootElement();
			List<Element> list = rootElement.elements();
			
			//获取每个节点中的属性
			for(Element e : list) {
				String id = e.attributeValue("id");
				if(id.equals(name)) {
					String className = e.attributeValue("class");
					
					//利用反射返回一个实例
					Class clazz = Class.forName(className);
					return clazz.newInstance();
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
}
