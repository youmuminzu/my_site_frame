package module.admin.dao;

import module.admin.entity.AdminUser;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public interface AdminUserDao {
    public AdminUser getAdminUser(HashMap params);
}
