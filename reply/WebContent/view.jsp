<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<form action="reply.do" method="post">
		<textarea name="reply" cols="40" rows="8"></textarea>
		<input type="submit" value="등록">
	</form>
	<table border="1" width="200">
		<c:forEach items="${list}" var="reply">
			<tr>
				<td>${reply.text}<a href="#" onclick="deleteReply(${reply.num})">[삭제]</a></td>
				
			</tr>
		</c:forEach>
	</table>
</body>
</html>