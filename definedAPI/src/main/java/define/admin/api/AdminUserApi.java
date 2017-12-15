package api.admin.service;

import java.util.HashMap;

public interface AdminUserService {
    public String getAdminUser(int id);
    public HashMap checkLogin(String userName, String password);
}
