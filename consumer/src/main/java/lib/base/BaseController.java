package lib.base;

import lib.helper.StringHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;
public class BaseController {
    protected Logger logger;

    protected Logger getLogger(String className){
        this.logger = LogManager.getLogger(className);
        return this.logger;
    }

    protected String generatePageToken(String pageURI, HttpSession session) {
        Random random = new Random();
        String token =  StringHelper.md5(String.valueOf(random.nextInt(99999)));
        session.setAttribute(pageURI,token);
        return token;
    }

    protected String generatePageToken(HttpServletRequest request) {
        String pageURI = request.getRequestURI();
        HttpSession session = request.getSession();
        Random random = new Random();
        String token =  StringHelper.md5(String.valueOf(random.nextInt(99999)));
        session.setAttribute(pageURI,token);
        return token;
    }
}
