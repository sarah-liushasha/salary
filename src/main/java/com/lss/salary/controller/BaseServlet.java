package com.lss.salary.controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

//基础servlet
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		// 找到当前类中所有定义的方法
		Method[] methods = this.getClass().getDeclaredMethods();
		Method method = null;
		// 遍历当前servlet中所有的方法，根据名字m.getName()找到对应的方法（action的值对应的方法）
		for (Method m : methods) {
			if (m.getName().equals(action)) {
				method = m;
				break;
			}
		}
		//找打这个方法去执行他  发送请求获取响应
		method.setAccessible(true);
		invokeMethod(method, request, response);
		response.setCharacterEncoding("utf-8");
	}

	private void invokeMethod(Method method, HttpServletRequest request, HttpServletResponse response) {
		try {
			// 获取要执行的这个方法中的参数列表
			Parameter[] params = method.getParameters();
			// 组装一个数组保存这个方法执行时的实参  methodParameters[i]保存获取到的实际参数
			//相当于把所有的请求到的字符串转成对用的参数类型  返回给这个数组
			Object[] methodParameters = new Object[params.length];

			for (int i = 0; i < params.length; i++) {
				// 遍历参数列表，获取出当前的这个参数起名param
				Parameter param = params[i];
				Class<?> paramType = param.getType();// 查看这个参数的数据类型
				if (paramType.equals(Integer.class)) {// 如果是Integer
					//根据参数名从request往外面拿值
					String value = request.getParameter(param.getName());
					if (value != null && !value.equals("")) {
						methodParameters[i] = Integer.parseInt(value);
					}
				} else if (paramType.equals(HttpServletRequest.class)) {// request
					methodParameters[i] = request;
				} else if (paramType.equals(HttpServletResponse.class)) {// response
					methodParameters[i] = response;
				} else if (paramType.equals(String.class)) {// String
					methodParameters[i] = request.getParameter(param.getName());
				} else if (paramType.equals(BigDecimal.class)) {
					String value = request.getParameter(param.getName());
					if (value != null && !value.equals("")) {
						BigDecimal bd=new BigDecimal(value);
						methodParameters[i] = bd;
					}
				}
				else {// 其他情况，默认觉得是对象（如果有其他类型在上边添加else if）
					// 创建对象实例
					//---obj=user---
					Object obj = paramType.newInstance();
					// 获取request请求中传过来的所有的参数名字
					//user:id username password name tel sex 
					Enumeration<String> reqParamNames = request.getParameterNames();
					while (reqParamNames.hasMoreElements()) {// 遍历名字
						String reqParamName = reqParamNames.nextElement();
						// 根据请求参数名找一下对象中有没有这个成员变量
						try {
							Field field = paramType.getDeclaredField(reqParamName);
							field.setAccessible(true);
							String reqValue = request.getParameter(reqParamName);
							// 反射方式把请求参数的值赋值到对象的成员变量中
							// 下边的if判断是兼容的成员变量的数据类型
							if (field.getType().equals(Integer.class) && reqValue != null && !reqValue.equals("")) {
								field.set(obj, Integer.parseInt(reqValue));
							} else if (field.getType().equals(String.class)) {
								field.set(obj, reqValue);
							}else if(field.getType().equals(BigDecimal.class)&& reqValue != null && !reqValue.equals("")) {
								BigDecimal bd=new BigDecimal(reqValue);
								field.set(obj,bd);
							}
								
						} catch (NoSuchFieldException e) {
							System.out.println("对象中没找到成员变量:" + reqParamName);
						}
					}
					//把反射创建好的对象放入实参数组
					methodParameters[i] = obj;
				}
			}
			// 反射执行方法
			Object result= method.invoke(this, methodParameters);
			//只要执行的方法有返回值就转json写回
			if(result!=null) {
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().write(JSON.toJSONString(result));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
