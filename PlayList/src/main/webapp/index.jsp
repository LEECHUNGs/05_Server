<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>PlayList 메인 페이지</title>
	<script src="https://kit.fontawesome.com/2bc76866e9.js" crossorigin="anonymous"></script>
</head>
<body>
	<main>
		<%-- 로그인 여부 확인 --%>
		<c:choose>
			<%-- 로그인 안되있을 시 --%>
			<c:when test="${empty sessionScope.loginMember}">
				<%-- 로그인 구역 --%>
				<section class="login-section">
					<form action="/login" method="post">
						<div class="id-div">
							<p>아이디</p>
							<input type="text" name="inputId" required>
						</div>

						<div class="pw-div">
							<p>비밀번호</p>
							<input type="password" name="inputPw" required>
						</div>

						<div class="login-btn-div">
							<button>로그인</button>
							<a href="/signup">회원가입</a>
						</div>
					</form>
				</section>
			</c:when>
			
			<%-- 로그인 되있을 시 --%>
			<c:otherwise>
				<section class="user-section">
					<%-- 노래 목록 버튼 --%>
					<a href="/songList" class="fa-solid fa-music"><br>노래 목록</a>
				
					<c:choose>
						<%-- 관리자 계정으로 로그인 시 --%>
						<c:when test="${sessionScope.loginMember.memberNo == 1}">
							<%-- 노래 등록 버튼 --%>
							<a href="/songAdd" class="fa-regular fa-square-plus"><br>노래 추가</a>

						</c:when>
						
						<%-- 일반 사용자 계정으로 로그인 시 --%>
						<c:otherwise>
							<%-- 재생목록 버튼 --%>
							<a href="/playList" class="fa-solid fa-list"><br>재생 목록</a>
			
						</c:otherwise>
					</c:choose>
				</section>
				
				<%-- 로그아웃/회원탈퇴 영역 --%>
				<section class="member-manage-section">
					<a href="/logout" class="logout-btn">로그아웃</a>
					<a href="/deleteMember" class="deleteMember-btn" 
						onclick="return confirm('정말 삭제하시겠습니까?')" >
						회원탈퇴
					</a>
				</section>
				
			</c:otherwise>
		</c:choose>
	</main>
	
	<c:if test="${not empty sessionScope.message}">
		<script>
			alert('${message}');
	
		</script>
		
		<c:remove var="message"/>
	</c:if>
	
</body>
</html>