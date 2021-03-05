package com.saas.school;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Var;

public class BuildTest {
	
	public static void main(String[] args) {
		try {
			loopPackage(args[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static void loopPackage(String packageName) throws Exception{
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		Enumeration<URL> dirs = cl.getResources(packageName.replace('.', '/'));
		while (dirs.hasMoreElements()) {
			URL url = dirs.nextElement();
			String protocol = url.getProtocol();
			if ("file".equals(protocol)) {
				String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
				File dir = new File(filePath);
				for(File file : dir.listFiles()) {
					String fileName = file.getName();
					if(file.isFile() && fileName.endsWith(".class")) {
						String className = fileName.substring(0, fileName.length() - 6);
						className = packageName + "." + className;
						className = className.replace('/', '.');
						loopClass(className);
					}
				}
			}
			
		}
	}

	public static void loopClass(String className) throws Exception{
		ClassLoader cl = BuildTest.class.getClassLoader();
		Class<?> cls = Class.forName(className);
		String testPackageName = cls.getPackage().getName() + ".test";
		URL url = cl.getResource(testPackageName.replace('.', '/'));
		String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
		buildTestClass(filePath, testPackageName, cls);
	}

	private static void buildTestClass(String testClassPath, String testPackageName, Class<?> cls)throws Exception {
		FileOutputStream fos = new FileOutputStream(testClassPath + "/Test" + cls.getSimpleName());
		
		try{
			PrintWriter pw = new PrintWriter(fos);
			buildTestClass(pw, testPackageName, cls);
		}finally {
			fos.close();
		}
	}

	private static void buildTestClass(PrintWriter pw, String packageName, Class<?> cls) {
		pw.print("package ");
		pw.print(packageName);
		pw.println(";");
		pw.println();
		pw.println("import java.util.*;");
		pw.println();
		
		pw.println("public class Test" + cls.getSimpleName() + " extends TestBase {");
		pw.println();
		for(Method method : cls.getDeclaredMethods()) {
			int mod = method.getModifiers();
			if(Modifier.isPublic(mod) && !Modifier.isStatic(mod)) {
				Field fld = method.getAnnotation(Field.class);
				if(fld != null) {
					String mapName = fld.value().length > 0 ? fld.value()[0] : method.getName();
					printTestMethod(pw, mapName, method);
				}
			}
		}
		pw.println("}");
		pw.flush();
	}

	private static void printTestMethod(PrintWriter pw, String mapName, Method method) {
		boolean isPost = false;
		if(method.getParameterCount() == 1) {
			Class<?> paramClass = method.getParameters()[0].getType();
			if(!paramClass.isPrimitive()) {
				isPost = true;
			}
		}
		pw.print("	public static void ");
		pw.print("test");
		pw.print(method.getName());
		pw.println("() throws Exception{");
		if(isPost) {
			pw.println("		ObjectNode params = (new ObjectMapper()).createObjectNode();");
		}else {
			pw.println("		Map<String, String> params = new HashMap<>();");
		}
		for(Parameter param : method.getParameters()) {
			Var var = param.getAnnotation(Var.class);
			pw.print("		params.put(");
			pw.print("\"");
			pw.print(var.value());
			pw.print("\"");
			pw.print(",");
			param.getType();
			pw.print("\"\"");
			pw.println(");");
		}
		pw.print("		");
		if(isPost) {
			pw.print("post");
		}else {
			pw.print("get");
		}
		pw.println("(\"/staff/" + mapName + "\", params);");
		pw.println("	}");
	}

}
