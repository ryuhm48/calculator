package model;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAO;
public class DeleteReplyCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response){
		    int num = Integer.parseInt(request.getParameter("num"));
	        
	        DAO dao = new DAO();
	        dao.deleteDB(num);
	}
}
