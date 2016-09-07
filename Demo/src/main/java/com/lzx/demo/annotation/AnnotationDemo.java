package com.lzx.demo.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationDemo {
	public static void main(String[] args) throws Exception {
		Class<?> classType=AnnotationDemo.class;
		Method m=classType.getDeclaredMethod("show1");
		Annotation a=m.getAnnotation(MyAnnotation.class);
		System.out.println(a);
	}
	@MyAnnotation
	public void show(){
		System.out.println(1);
	}
}
