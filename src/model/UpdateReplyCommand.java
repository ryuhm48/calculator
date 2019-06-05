package model;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAO;
import DO.DO;
public class UpdateReplyCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response){
			
			int num = Integer.parseInt(request.getParameter("num"));
			String text = request.getParameter("text");
			DAO dao= DAO.getInstance();
			DO g= new DO();
			g.setNum(num);
			g.setText(text);
			dao.updateDB(g);
	}
}