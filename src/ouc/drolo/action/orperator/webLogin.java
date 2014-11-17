package ouc.drolo.action.orperator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.OperatorDao;
import ouc.drolo.dao.areaDao;

import wph.iframework.Action;
import wph.iframework.dao.db.Database;

public class webLogin extends Action {
	

    private String username;
    private String password;
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
    {
    	String shenfen = request.getParameter("shenfen");
    	
        Database db = getDatabase();
        OperatorDao oDao = new  OperatorDao(db);
     
    int oid = oDao.login(username, password);
     
        
        logger.debug("oid:"+oid);
        
        if (oid > 0)
        {
            request.getSession().setAttribute("oid", oid);
            request.getSession().setAttribute("name", username);
        }
        logger.debug("身份："+shenfen+"  登陆名:"+username+"  密码:"+password); 
        
        return "" + oid;
    }
    
    
    //----------------------------------------
    public String getUsername()
    {
        return username;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
}
