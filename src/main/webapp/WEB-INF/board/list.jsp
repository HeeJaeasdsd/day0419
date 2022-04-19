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
	<h1>게시글 페이지입니다.</h1>
	<a href="write">글쓰기</a>
	<table>
		<thead>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>조회수</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>
			<tr>

			</tr>
		</tbody>
		<!-- db의 게시글 내용을 뿌려주세요.. -->
		<tr>
			<td colspan="5" style="text-align: center;">
			게시글이 존재하지 않습니다.</td>
		</tr>
	</table>
</body>
</html>