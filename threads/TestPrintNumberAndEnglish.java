package com.hanhan.mylom;

/**
 * 
 * @author hanhan
 *
 *两个线程，一个是1-52；一个是a-z，打印的结果是12a...5152z
 *
 *线程，操作，资源类（资源类内部包含方法）
 */
class MyPrint{
	
	//打印数字的方法
	public synchronized void MyPrintNumber() throws InterruptedException {
		for (int i = 1; i <=52; i++) {
			System.out.print(i+",");
			
			
			if(i%2==0) {
				this.notifyAll();
				this.wait();
			}
			
		}
	}
	//打印字母的方法
	public synchronized void MyPrintEnglish() throws InterruptedException {
		for (int i = 0; i <=25; i++) {
			System.out.print((char)('A' + i) + ","); 
			this.notifyAll();
			if(i!=25) {
				this.wait();
			}
		}
		
		
	}
}
public class TestPrintNumberAndEnglish {

	public static void main(String[] args) {
		
		MyPrint myPrint =new MyPrint();
		new Thread(()->{
			
			
				try {
					myPrint.MyPrintNumber();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		},"aa").start(); 
		new Thread(() -> {

			
				try {
					myPrint.MyPrintEnglish();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		},"bb").start(); 
	}
	
}
