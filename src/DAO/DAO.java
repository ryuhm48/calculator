package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DO.DO;

public class DAO {
   Connection conn=null;
   PreparedStatement pstmt=null;
   String jdbc_driver="com.mysql.cj.jdbc.Driver";
   String jdbc_url = "jdbc:mysql://localhost:3306/jbhdb?useUnicode=true&useJDBCCompilantTimezoneShifr=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
   private static DAO instance;
   public static DAO getInstance() {
      if(instance==null)
         instance=new DAO();
      return instance;
   }
   
   void connect() {
      try {
         Class.forName(jdbc_driver);
         
         conn=DriverManager.getConnection(jdbc_url,"jbhong","asdf1234");
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }
   void disconnect() {
      if(pstmt!=null) {
         try {
            pstmt.close();
         }
         catch(SQLException e) {
            e.printStackTrace();
         }
      }
      if(conn!=null) {
         try {
            conn.close();
         }catch(SQLException e) {
            e.printStackTrace();
         }
      }
   }
   public boolean insertDB(DO userdb) {
      connect();
      
      String sql="insert into reply(text)value(?)";
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1,userdb.getText());
        
         pstmt.executeUpdate();

      }
      catch(SQLException e) {
         e.printStackTrace();
         return false;
      }
      finally {
         
      }
      sql="alter table reply auto_increment=1";
      try {
          pstmt=conn.prepareStatement(sql);
          pstmt.executeUpdate();
       }
       catch(SQLException e){
          e.printStackTrace();
          return false;
       }
       finally {
          
       }
      sql="set @count=0";
      try {
          pstmt=conn.prepareStatement(sql);
          pstmt.executeUpdate();
       }
       catch(SQLException e){
          e.printStackTrace();
          return false;
       }
       finally {
          
       }
      sql="update reply set reply.num=@count:=@count+1";
      try {
          pstmt=conn.prepareStatement(sql);
          pstmt.executeUpdate();
       }
       catch(SQLException e){
          e.printStackTrace();
          return false;
       }
       finally {
          disconnect();
       }
      return true;
   }
   public ArrayList<DO> list(){
	      ArrayList<DO> replylist=new ArrayList<DO>();
	      
	      try {
	     connect();
	      String sql="SELECT * FROM reply;";
		   pstmt = conn.prepareStatement(sql);
		   ResultSet rs=pstmt.executeQuery();
	      
	      while(rs.next()) {
	         DO data=new DO();
	         data.setText(rs.getString("text"));
	         data.setNum(rs.getInt("num"));
	         replylist.add(data);
	      }
	      }catch(Exception e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            if (pstmt != null)
	               pstmt.close();
	            if (conn != null)
	               conn.close();
	         } catch (Exception e) {
	            e.printStackTrace();
	         }
	      }
	      return replylist;
	  }
   public boolean deleteDB(int num) {
	      connect();
	      
	      String sql="delete from reply where num=?";
	      try {
	          pstmt=conn.prepareStatement(sql);
	          pstmt.setInt(1,num);
	          pstmt.executeUpdate();
	       }
	       catch(SQLException e){
	          e.printStackTrace();
	          return false;
	       }
	       finally {
	          
	       }
	      sql="alter table reply auto_increment=1";
	      try {
	          pstmt=conn.prepareStatement(sql);
	          pstmt.executeUpdate();
	       }
	       catch(SQLException e){
	          e.printStackTrace();
	          return false;
	       }
	       finally {
	          
	       }
	      sql="set @count=0";
	      try {
	          pstmt=conn.prepareStatement(sql);
	          pstmt.executeUpdate();
	       }
	       catch(SQLException e){
	          e.printStackTrace();
	          return false;
	       }
	       finally {
	          
	       }
	      sql="update reply set reply.num=@count:=@count+1";
	      try {
	          pstmt=conn.prepareStatement(sql);
	          pstmt.executeUpdate();
	       }
	       catch(SQLException e){
	          e.printStackTrace();
	          return false;
	       }
	       finally {
	          disconnect();
	       }
	       return true;
	   }
   
   public boolean updateDB(DO userdb) {
	      connect();
	      
	      String sql="UPDATE reply SET text=? where num=?";
	      try {
	          pstmt=conn.prepareStatement(sql);
		      pstmt.setString(1,userdb.getText());
		      pstmt.setInt(2, userdb.getNum());
	          pstmt.executeUpdate();
	       }
	       catch(SQLException e){
	          e.printStackTrace();
	          return false;
	       }
	       finally {
	          disconnect();
	       }
	       return true;
	   }
   
   public DO getText(int num)
   {
	   connect();
       DO text = null;
       String sql="SELECT * FROM reply where num=?";
       
       try {
           pstmt=conn.prepareStatement(sql);
           pstmt.setInt(1, num);
           ResultSet rs=pstmt.executeQuery();
           while(rs.next())
           {
               text = new DO();
               text.setNum(rs.getInt("num"));
               text.setText(rs.getString("text"));
           }
       } catch (Exception e) {
           throw new RuntimeException(e.getMessage());
       }
       
       return text;
   }
   public int memberCount(){
	   connect();
	   int cnt=0;
	   try{
	    String sql = "select count(*) from reply";
	    pstmt=conn.prepareStatement(sql);
	    ResultSet rs=pstmt.executeQuery();
	    rs.next();
	    cnt = rs.getInt("count(*)");
	   }catch(Exception e){
	    e.printStackTrace();
	   }finally{
		   disconnect();
	   }
	   return cnt;
	  }
   public ArrayList<DO> selectAllMember(int start, int end){
	   ArrayList<DO> list = new ArrayList<DO>();
	   connect();
	   try{
	    String sql = "select * from reply order by num asc limit  ?, ?";
	    pstmt=conn.prepareStatement(sql);
	    pstmt.setInt(1, start);
	    pstmt.setInt(2, end);
	    ResultSet rs=pstmt.executeQuery();
	    while(rs.next()){
	     DO vo = new DO();
	     vo.setText(rs.getString("text"));
	     vo.setNum(rs.getInt("num"));
	     list.add(vo);
	    }
	   }catch(Exception e){
	    e.printStackTrace();
	   }finally{
		   disconnect();
	   }
	   return list;
	  }
}
   


