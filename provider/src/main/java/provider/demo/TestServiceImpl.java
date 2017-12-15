package provider.demo;

import api.demo.TestService;

public class TestServiceImpl implements TestService {
    public String hello(String name) {
        System.out.println("provider starts");
        return "hello " + name + ", this is the provider.";
    }
}
