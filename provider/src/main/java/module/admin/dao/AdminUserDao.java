package module.admin.dao;

import module.admin.entity.AdminPermission;
import module.admin.entity.AdminRole;
import module.admin.entity.AdminUser;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public interface AdminUserDao {
    public AdminUser getAdminUser(HashMap params);
    public AdminRole getAdminUserRole(int userId);
    public List<AdminPermission> getAdminUserPermissions(int userId);
    public void updateAdminUser(AdminUser user);
    public List<AdminRole> getRoleList(int[] ids);
    public Boolean createAdminUser(AdminUser user);
}
