package module.admin.service;

import api.admin.service.AdminUserService;
import module.admin.dao.AdminUserDao;
import module.admin.entity.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserDao adminUserDao;
    @Override
    public String getAdminUser(int id) {
        HashMap<String,Integer> params = new HashMap<String, Integer>();
        params.put("id",1);
        AdminUser adminUser = adminUserDao.getAdminUser(params);
        return adminUser.name;
    }


    @Override
    public HashMap checkLogin(String userName, String password) {

        HashMap<String,Object> args = new HashMap<String,Object>();
        args.put("name",params.get("name"));
        AdminUserService adminUserService = new AdminUserService();
        AdminUser user = adminUserService.getAdminUser(args);
        HashMap<String,Object> ret = new HashMap<String, Object>();
        if(user == null) {
            ret.put("loginResult","failed");
            ret.put("message","user name not exist");
            return ret;
        }
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
                ret.put("userObject",user);
            }
        } else {
            //用户密码验证失败
            ret.put("loginResult","failed");
            ret.put("message","wrong password");
        }
        return ret;
    }
}
