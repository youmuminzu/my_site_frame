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
}
