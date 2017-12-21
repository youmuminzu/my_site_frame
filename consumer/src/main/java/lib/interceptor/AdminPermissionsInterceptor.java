package lib.interceptor;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

public class AdminPermissionsInterceptor extends HandlerInterceptorAdapter  {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        int userStatus = -1;
        if(session.getAttribute("userStatus") != null) {
            userStatus =  (Integer) session.getAttribute("userStatus");
        }

        if(session.getAttribute("userId") == null  || userStatus!=1) {
            response.sendRedirect("/admin/login");
            return false;
        }

        String uri = request.getRequestURI();

        //判断是不是需要进行csrf攻击过滤验证
        String classPath = this.getClass().getResource("/").getPath();
        File file = new File(classPath + "config/safety/csrf.xml");
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(file);
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
        String nodeToFind = "data" + uri;
        Node node = document.selectSingleNode(nodeToFind);
        if(node!=null && node.getText().equals("1")) {
            if("post".equals(request.getMethod().toLowerCase())) {
                String postedToken = request.getParameter("token");
                String pageToken = (String)session.getAttribute(uri);
                if(pageToken == null || !pageToken.equals(postedToken)) {
                    return false;
                } else {
                    session.removeAttribute(uri);
                }
            }
        }

        //验证是否有访问权限
        String availableResources = (String)session.getAttribute("availableResource");
        String batchPermissionString = uri.substring(1,uri.lastIndexOf("/")) + "/*";
        if(availableResources.contains(batchPermissionString)) {
            return true;
        } else if(!availableResources.contains(uri)) {
            response.sendRedirect("/noAuthority.jsp");
            return false;
        }
        return true;
    }
}
