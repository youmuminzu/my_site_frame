package app.admin.controller;

import app.admin.lib.base.AdminBaseController;
import define.admin.api.AdminUserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class AdminUserController extends AdminBaseController {

    @Autowired
    private AdminUserApi adminUserApi;

    @RequestMapping("/admin/index")
    public String index(){
        return "admin/index";
    }

    @RequestMapping("/admin/login")
    public String login(Model model, HttpServletRequest request) {
        String pageToken = this.generatePageToken(request);
        model.addAttribute("token",pageToken);
        return "admin/login";
    }

    @RequestMapping("/admin/addAccount")
    public String addCount(Model model,HttpServletRequest request) {
        String pageToken = this.generatePageToken(request);
        model.addAttribute("token",pageToken);
        HashMap roles = adminUserApi.adminRoleList(null);
        System.out.println(roles);
        model.addAttribute("roleList",roles);
        return "admin/addAccount";
    }

    @RequestMapping(value = "/admin/doAddAccount",method = RequestMethod.POST)
    @ResponseBody
    public String doAddAccount(@RequestParam(value = "userName") String name,
                               @RequestParam(value = "password") String password,
                               @RequestParam(value = "role") String role) {
        HashMap params = new HashMap();
        params.put("name",name);
        params.put("password",password);
        params.put("role",role);
        Boolean result = adminUserApi.createAdminUser(params);
        if(!result) {
            return "failed";
        }
        return "success";
    }

    //重置密码页面
    @RequestMapping(value = "/admin/resetPassword",method = RequestMethod.GET)
    public String resetPassword(Model model,HttpServletRequest request) {
        String pageToken = this.generatePageToken(request);
        model.addAttribute("userName",request.getSession().getAttribute("userName"));
        model.addAttribute("token",pageToken);
        return "admin/resetPassword";
    }

    //重置密码
    @RequestMapping(value = "/admin/doResetPassword",method = RequestMethod.POST)
    @ResponseBody
    public String doResetPassword(@RequestParam(value = "oldPassword") String oldPassword,
                                  @RequestParam(value = "newPassword") String newPassword,
                                  HttpSession session){

        HashMap<String,String> params = new HashMap<String, String>();
        String name = (String) session.getAttribute("userName");
        params.put("name",name);
        params.put("password",oldPassword);
        params.put("newPassword",newPassword);
        String result = adminUserApi.doResetPassword(name,oldPassword,newPassword);

        return result;
    }

    //用户登录
    @RequestMapping(value = "/admin/dologin",method = RequestMethod.POST)
    public String dologin(@RequestParam(value = "name") String userName,
                          @RequestParam(value = "password") String password,
                          HttpSession session){

        HashMap result = adminUserApi.checkLogin(userName,password);
        if(result.get("loginResult").equals("success")) {
            session.setAttribute("availableResource",result.get("availableResource"));
            session.setAttribute("userName",result.get("userName"));
            session.setAttribute("userId",result.get("userId"));
            session.setAttribute("userStatus",result.get("userStatus"));
            return "redirect:/admin/index";
        } else {
            return "redirect:/admin/login";
        }
    }

    @RequestMapping(value = "/admin/showtest")
    @ResponseBody
    public String showTest(){
        String uri = "/admin/show";
        uri = uri.substring(0,uri.lastIndexOf("/")) + "/*";
        return uri;
    }

    @RequestMapping(value = "/admin/test",method = RequestMethod.POST)
    @ResponseBody
    public String test(){
        return adminUserApi.testAcrossCall("this is the params");
//        return  "this is the test";
    }
}
