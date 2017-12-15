package app.Demo;

import api.admin.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import api.demo.TestService;

@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/test/t1")
    @ResponseBody
    public String say(){
        return testService.hello("buddy");
    }

    @Autowired
    private AdminUserService adminUserService;
    @RequestMapping(value = "/test/t2")
    @ResponseBody
    public String adminName(){

        return adminUserService.getAdminUser(1);
    }
}
