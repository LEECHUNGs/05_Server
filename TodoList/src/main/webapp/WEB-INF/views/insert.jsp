<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/resources/css/main.css">
	<title>To do 등록하기</title>
</head>
<body>
	<main>
		<h1>To do 등록하기</h1>
		
		<form action="/insert" method="post">
			<p>제목</p>
			<input type="text" name="title" required>
			
			<p>메모</p>
			<textarea name="memo" style="resize: none; font-size: 18px" rows="5" cols="19"></textarea>
			
			<br>
			<button class="insert-btn">등록하기</button>
			
		</form>
	</main>
	
</body>
</html>