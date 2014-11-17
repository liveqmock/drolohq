package ouc.drolo.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.ChangeDao;
import ouc.drolo.dao.UserExtraDao;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;

/**
 * 给数据库中邀请码空的添加内容
 * @author jeep
 *
 */
public class InviteCodeAction extends Action{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String phone = request.getParameter("phone");
		
		Database db = getDatabase();
		 ChangeDao cDao = new ChangeDao(db);
		 String inviteCode = cDao.getInviteCode();
		
		
		UserExtraDao uDao = new UserExtraDao(db);
		int flag = uDao.addInviteCode(phone,inviteCode);
		
		return flag+"";
	}

}
