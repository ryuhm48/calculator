package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AddReplyCommand;
import model.Command;
import model.DeleteReplyCommand;
import model.ReplyCommand;
import model.UpdateFormCommand;
import model.UpdateReplyCommand;
@WebServlet("*.do")
public class controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
      

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  request.setCharacterEncoding("UTF-8");
		  String requestURI = request.getRequestURI();
	      String contextPath = request.getContextPath();
	      String com = requestURI.substring(contextPath.length());

	      
	      Command command  = null;
	      String nextPage = null;
	      
	      System.out.println(request);
	      
	      if(com.equals("/reply.do")){
	         command = new ReplyCommand();
	         command.execute(request, response);
	         command = new AddReplyCommand();
	         command.execute(request, response);
	         nextPage = "view.jsp";
	      }
	      else if(com.equals("/list.do")){
		         command = new AddReplyCommand();
		         command.execute(request, response);
		         nextPage = "view.jsp";
		  }
	      else if(com.equals("/delete.co")){
		         command = new DeleteReplyCommand();
		         command.execute(request, response);
		         command = new AddReplyCommand();
		         command.execute(request, response);
		         nextPage = "view.jsp";
	      }
	      else if(com.equals("/updateForm.co")){
		         command = new UpdateFormCommand();
		         command.execute(request, response);
		         nextPage = "childform.jsp";
	      }
	      else if(com.equals("/update.co")){
		         command = new UpdateReplyCommand();
		         command.execute(request, response);
		         command = new AddReplyCommand();
		         command.execute(request, response);
		         nextPage = "view.jsp";
	      }
	      
	      RequestDispatcher dis = request.getRequestDispatcher(nextPage);
	      dis.forward(request,response);
	}
}
