package ouc.drolo.action.orperator;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import ouc.drolo.action.diaodu.Dispatcher;


import wph.iframework.Action;

public class offline extends Action {
private String id;
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Dispatcher dispatcher = Dispatcher.getInstance();
        
        int s;
        if (null == id)
        {
            // 错误处理
            s = -1;
        }
        else
        {
            s = dispatcher.onOperatorOffline(id);//1已经离线，2离线成功
        }
        
        try
        {
            JSONObject obj = new JSONObject();
            obj.put("s", s);
            return obj.toString();
        }
        catch (Exception e)
        {
            return "";
        }
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
    
    
    
}
