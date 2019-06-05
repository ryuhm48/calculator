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
	
	function checkValue()
    {
        //var form = document.forms[0];
        // 전송할 값을 변수에 담는다.    
        var num = "${g.num}";
        var text = document.getElementById("reply").value; //form.text.value;
        if(!text)
        {
            alert("내용을 입력하세요");
            return false;
        }
        else{
            var param="num="+num+"&text="+text;
            httpRequest = getXMLHttpRequest();
            httpRequest.onreadystatechange = checkFunc;
            httpRequest.open("POST", "update.co", true);   
            httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=UTF-8'); 
            httpRequest.send(param);
            alert("수정되었습니다.");
            window.close();
            window.opener.location.href("list.do");
        }
    }

    function checkFunc(){
        if(httpRequest.readyState == 4){
            // 결과값을 가져온다.
            var resultText = httpRequest.responseText;
            if(resultText == 1){
                if (opener != null) {
                    // 부모창 새로고침
                    window.opener.document.location.reload(); 
                    opener.updateForm = null;
                    self.close();
                }
            }
        }
    }
</script>
</head>
<body>
	<div>
		<br><font size="5" color="gray">댓글수정</font></b>
		<hr size="1" width=550">
		<br>
		
		<div>
			<form target="parentForm">
				<textarea id="reply" name="reply" cols="70" rows="7">${g.text}</textarea>
				<br><br>
				<input type="button" value="등록" onclick="checkValue()">
				<input type="button" value="창닫기" onclick="window.close()">
			</form>
		</div>
	</div>
</body>
</html>