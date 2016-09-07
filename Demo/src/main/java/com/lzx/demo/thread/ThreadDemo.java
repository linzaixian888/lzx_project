package com.lzx.demo.thread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadDemo extends Thread{
	public static int count=10;
	public static List<Integer> list=new ArrayList<Integer>();
	public static List<ThreadDemo.MyThread> allThread=new ArrayList<ThreadDemo.MyThread>();
	public  class MyThread extends Thread{
		public LinkedBlockingQueue<Integer> data=new LinkedBlockingQueue<Integer>();
		@Override
		public void run() {
			
			 while(!Thread.currentThread().isInterrupted()) {  
					try {
						System.out.println(Thread.currentThread().isInterrupted());
						System.out.println("运行了"+currentThread());
						int oneData=data.take();
						
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						System.out.println(Thread.currentThread().isInterrupted());
						e.printStackTrace();
					}
//					System.out.println(oneData);
				}
			System.out.println("结束了");
		}
		public void add(Integer one){
			try {
				data.put(one);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void run() {
		try {
			while(true){
				Thread.sleep(1000);
				for(int i=0;i<100;i++){
					list.add(i);
				}
				int size=allThread.size();
				if(size<count){
					for(int i=size;i<count;i++){
						ThreadDemo.MyThread my=new MyThread();
						allThread.add(my);
						my.start();
					}
				}else{
					for(int i=count;i<size;i++){
						MyThread my=allThread.get(i);
						my.interrupt();
						allThread.remove(i);
					}
				}
				putData(allThread, list);
				Thread.sleep(1000);
				Iterator<MyThread> it=allThread.iterator();
				while(it.hasNext()){
					it.next().interrupt();
					it.remove();
				}
				
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
//		new ThreadDemo().start();
		System.out.println(ThreadDemo.class.getSimpleName());
		
	}
	public static void putData(List<ThreadDemo.MyThread> allThread,List<Integer> list){
		int threadSize=allThread.size();
		for(int i=0;i<list.size();i++){
			int index=i%threadSize;
			allThread.get(index).add(list.get(i));;
		}
	}
}
