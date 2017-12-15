package index;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/config/provider.xml");
        context.start();
        System.out.println("service has started.服务已经启动");
        System.in.read();
    }
}
