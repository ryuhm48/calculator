<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="DAO.*" import="DO.*" import = "java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
var httpRequest = null;

// httpRequest 객체 생성
function getXMLHttpRequest(){
    var httpRequest = null;

    if(window.ActiveXObject){
        try{
            httpRequest = new ActiveXObject("Msxml2.XMLHTTP");    
        } catch(e) {
            try{
                httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e2) { httpRequest = null; }
        }
    }
    else if(window.XMLHttpRequest){
        httpRequest = new window.XMLHttpRequest();
    }
    return httpRequest;    
}

	function deleteReply(num)
	{
		var param="num=" + num;

        httpRequest = getXMLHttpRequest();
        httpRequest.onreadystatechange = checkFunc;
        httpRequest.open("POST", "delete.co", true);    
        httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=EUC-KR'); 
        httpRequest.send(param);
        alert("삭제되었습니다.")
        location.reload();
    }
	
	function updateReply(num)
	{
		 window.name = "parentForm";
         window.open("updateForm.co?num="+num, "updateForm", "width=570, height=350, resizable = no, scrollbars = no");
	}
    function checkFunc(){
        if(httpRequest.readyState == 4){
            // 결과값을 가져온다.
            var resultText = httpRequest.responseText;
            if(resultText == 1){ 
                document.location.reload(); // 상세보기 창 새로고침
            }
        }
    }
</script>
</head>
<body>
<%
DAO dao = new DAO();
ArrayList<DO> list = new ArrayList<DO>();
ArrayList<DO> reqlist = (ArrayList<DO>)request.getAttribute("list");

int pagenum = 1;  // 페이지 번호
if(request.getParameter("memberPage") != null){
 pagenum = Integer.parseInt(request.getParameter("memberPage"));
}
int size = 10;
int tot;
int cnt = dao.memberCount();
tot = cnt/size;
if(cnt%size != 0){
 tot++;
}
int start = (pagenum - 1) * size;
int end = pagenum * size - 1;
if(reqlist==null){
 list = dao.selectAllMember(start, end);
}else{
 list = reqlist;
}
%>
	<form action="reply.do" method="post">
		<textarea name="reply" cols="40" rows="8"></textarea>
		<input type="submit" value="등록">
	</form>
	<table border="1">
		<c:forEach items="${list}" var="reply">
			<tr>
				<td>${reply.num}</td>
				<td>${reply.text}</td>
				<td><a href="#" onclick="deleteReply(${reply.num})">[삭제]</a></td>
				<td><a href="#" onclick="updateReply(${reply.num})">[수정]</a></td>
			</tr>
		</c:forEach>
			<tr>
				<td colspan="16" align="center">
  					<%
  					for(int i = 1; i<=tot; i++){ %>
   					<a href="list.do?memberPage=<%=i%>"><%=i%></a>
  					<%} %>
  				</td>
 			</tr>
	</table>
</body>
</html>