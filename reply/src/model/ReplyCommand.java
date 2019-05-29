package model;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.DAO;
import DO.DO;
public class ReplyCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		DO g= new DO();
		g.setText(request.getParameter("reply"));
		DAO dao= new DAO();
		dao.insertDB(g);
	}
}
