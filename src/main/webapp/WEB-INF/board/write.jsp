<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="<%=request.getContextPath()%>">HOME</a>
	<a href="list">목록보기</a>
	<h1>글쓰기 페이지입니다.</h1>
	<form action="write.nowon">
		<p>
			<input type="text" name="writre" placeholder="작성자">
		</p>
		<p>
			<input type="text" name="subject" placeholder="제목을 입력하세요">
		</p>
		<p>
			<textarea name="content" rows="5" cols="100" placeholder="내용을 입력하세요"></textarea>
		</p>
		<p>
			<button type="submit">글쓰기 완료</button>
		</p>
	</form>
</body>
</html>