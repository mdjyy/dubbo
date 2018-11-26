package test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Start {
	public static void main(String[] args) {
		//com.alibaba.dubbo.container.Main.main(args);
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		context.start();
		synchronized (Start.class) {
			while (true) {
				try {
					Start.class.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}
}