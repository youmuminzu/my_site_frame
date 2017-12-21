package define.admin.api;

import java.util.HashMap;

public interface AdminUserApi {
    public HashMap checkLogin(String userName, String password);
    public String doResetPassword(String userName,String password, String newPassword);
    public String testAcrossCall(String param);
    public HashMap<Integer,String> adminUserRoles(int userId);
    public HashMap<Integer,HashMap<String,Object>> adminRoleList(int[] roleIds);
    public Boolean createAdminUser(HashMap params);
}
