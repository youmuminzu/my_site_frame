package module.admin.service;
import module.admin.dao.AdminUserDao;
import module.admin.entity.AdminPermission;
import module.admin.entity.AdminRole;
import module.admin.entity.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class AdminUserService {

    @Autowired
    private AdminUserDao adminUserDao;

    public AdminUser getAdminUser(HashMap params) {
        return adminUserDao.getAdminUser(params);
    }

    public List<AdminPermission> getUserPermission(int userId) {
        return adminUserDao.getAdminUserPermissions(userId);
    }

    public Boolean updateAdminUser(AdminUser user){

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            user.setUpdateTime(dateFormat.format(new Date()));
            adminUserDao.updateAdminUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public AdminRole getAdminRole(int userId) {
        return adminUserDao.getAdminUserRole(userId);
    }

    public List<AdminRole> getRoleLit(int[] ids) {
        return adminUserDao.getRoleList(ids);
    }

    public Boolean createAdminUser(AdminUser user) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = dateFormat.format(new Date());
            user.setUpdateTime(date);
            user.setCreateTime(date);
            adminUserDao.createAdminUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
