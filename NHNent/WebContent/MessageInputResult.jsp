<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
		var aa =<%=request.getAttribute("result")%>;
		document.write(typeof aa);
		if(aa == 'success'){
			alert("등록 되었습니다.");
		}else{
			alert("등록에 실패했습니다.");
		}
	</script>
</head>
	
<body>
	
</body>
</html>