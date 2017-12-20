package admin.api;

import java.util.HashMap;

public interface AdminUserApi {
    public HashMap checkLogin(String userName, String password);
    public String doResetPassword(String userName,String password, String newPassword);
}
