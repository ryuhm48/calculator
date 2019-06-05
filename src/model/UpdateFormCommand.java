package model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ActionForward;
import model.Command;


import DAO.DAO;
import DO.DO;
public class UpdateFormCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response){
			ActionForward forward = new ActionForward();
			int num = Integer.parseInt(request.getParameter("num"));
			DAO dao= DAO.getInstance();
			DO g= dao.getText(num);
			request.setAttribute("g", g);
			
			forward.setRedirect(false);
	        forward.setNextPath("childform.jsp");
	}
}