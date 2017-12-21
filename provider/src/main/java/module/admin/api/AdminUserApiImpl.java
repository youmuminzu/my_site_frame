package module.admin.api;

import define.admin.api.AdminUserApi;
import lib.helper.PasswordHelper;
import module.admin.entity.AdminPermission;
import module.admin.entity.AdminRole;
import module.admin.entity.AdminUser;
import module.admin.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.List;

@Component
public class AdminUserApiImpl implements AdminUserApi {

    @Autowired
    private AdminUserService adminUserService;

    private AdminUser adminUser;
    @Override
    public HashMap checkLogin(String userName, String password) {

        HashMap<String,Object> args = new HashMap<String,Object>();
        args.put("name",userName);
        AdminUser user = adminUserService.getAdminUser(args);
        HashMap<String,Object> ret = new HashMap<String, Object>();
        if(user == null) {
            ret.put("loginResult","failed");
            ret.put("message","user name not exist");
            return ret;
        }
        this.adminUser = user;
        String salt = user.getSalt();
        String storedPassword = user.getPassword();
        String generatePassword  = PasswordHelper.generatePassword(password,salt);

        if(storedPassword.equals(generatePassword)) {
            //用户密码验证成功
            List<AdminPermission> permissions = adminUserService.getUserPermission(user.id);
            if(permissions != null) {
                String availableResource = "";
                for (AdminPermission p: permissions
                        ) {
                    availableResource += (p.getAvailableResources()+",");
                }
                ret.put("loginResult","success");
                ret.put("availableResource",availableResource);
                ret.put("userName",user.name);
                ret.put("userId",user.id);
                ret.put("userStatus",user.getStatus());
                ret.put("message","success");
            }
        } else {
            //用户密码验证失败
            ret.put("loginResult","failed");
            ret.put("message","wrong password");
        }
        return ret;
    }

    @Override
    public String doResetPassword(String userName, String password, String newPassword) {
        HashMap<String,Object> checkLoginResult = this.checkLogin(userName,password);
        String checkResult = (String) checkLoginResult.get("loginResult");
        if(checkResult.equals("success")) {
            String salt = this.adminUser.getSalt();
            String generatedPassword = PasswordHelper.generatePassword(newPassword,salt);
            this.adminUser.setPassword(generatedPassword);
            Boolean result = adminUserService.updateAdminUser(this.adminUser);
            if(result) {
                return "success";
            }
        }
        return "failed";
    }

    @Override
    public HashMap<Integer, String> adminUserRoles(int userId) {
        AdminRole role = adminUserService.getAdminRole(userId);
        return null;
    }

    @Override
    public HashMap<Integer, HashMap<String,Object>> adminRoleList(int[] roleIds) {
        List<AdminRole> adminRoleList = adminUserService.getRoleLit(roleIds);
        HashMap<Integer, HashMap<String, Object>> result = new HashMap<Integer, HashMap<String, Object>>();

        for (AdminRole item:adminRoleList
             ) {
            HashMap<String,Object> itemBuffer = new HashMap<String, Object>();
            itemBuffer.put("id",item.getId());
            itemBuffer.put("role",item.getRole());
            itemBuffer.put("status",item.getStatus());
            result.put(item.getId(),itemBuffer);
        }
        return result;
    }

    @Override
    public Boolean createAdminUser(HashMap params) {
        AdminUser adminUser = new AdminUser();
        String salt = PasswordHelper.getSalt();
        String generatePassword = PasswordHelper.generatePassword((String)params.get("password"),salt);
        String name = (String) params.get("name");
        adminUser.setName(name);
        adminUser.setPassword(generatePassword);
        adminUser.setSalt(salt);
        adminUser.setRoleId(Short.parseShort((String) params.get("role")));
        return adminUserService.createAdminUser(adminUser);
    }

    @Override
    public String testAcrossCall(String param) {
        return null;
    }

}
