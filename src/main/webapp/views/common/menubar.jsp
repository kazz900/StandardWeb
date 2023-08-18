<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="member.model.vo.Member" %>
	<% //로그인 확인을 위해서 내장된 session 객체를 이용 
	Member loginMember=(Member) session.getAttribute("loginMember"); %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="UTF-8">
			<title></title>
			<style type="text/css">
				nav>ul#menubar {
					list-style: none;
					position: relative;
					left: 150px;
				}
				nav>ul#menubar li {
					float: left;
					width: 120px;
					height: 30px;
					margin: 5px;
					padding: 0;
				}
				nav>ul#menubar li a {
					text-decoration: none;
					width: 120px;
					height: 30px;
					display: block;
					background: orange;
					color: navy;
					text-align: center;
					font-weight: bold;
					margin: 0;
					text-shadow: 1px 1px 2px white;
					padding-top: 5px;
				}
				nav>ul#menubar li a:hover {
					text-decoration: none;
					width: 120px;
					height: 30px;
					display: block;
					background: navy;
					color: white;
					text-align: center;
					font-weight: bold;
					margin: 0;
					text-shadow: 1px 1px 2px white;
					padding-top: 5px;
				}
				hr {
					clear: both;
				}
			</style>
		</head>

		<body>
			<% if(loginMember==null) { //로그인 하지 않았을 때 %>
				<nav>
					<ul id="menubar">
						<li><a href="/first/index.jsp">Home</a></li>
						<li><a href="/frist/nlist.admin">공지사항</a></li>
						<li><a href="/first/blist?page=1">게시글</a></li>
						<li><a href="#">QnA</a></li>
						<li><a href="/first/views/testapi/testList.html">테스트</a></li>
					</ul>
				</nav>
				<% } else if(loginMember.getAdmin().equals("Y")){ //관리자가 로그인 했을때 %>
					<nav>
						<ul id="menubar">
							<li><a href="/first/index.jsp">Home</a></li>
							<li><a href="/frist/nlist.admin">공지사항관리</a></li>
							<li><a href="/first/blist?page=1">게시글관리</a></li>
							<li><a href="/first/mlist">회원관리</a></li>
							<li><a href="/first/views/testapi/testList.html">테스트</a></li>
						</ul>
					</nav>
					<% } else { //일반회원이 로그인 했을때 %>
						<nav>
							<ul id="menubar">
								<li><a href="/first/index.jsp">Home</a></li>
								<li><a href="/frist/nlist">공지사항</a></li>
								<li><a href="/first/blist?page=1">게시글</a></li>
								<li><a href="#">암호화회원가입</a></li>
								<li><a href="/first/views/testapi/testList.html">테스트</a></li>
							</ul>
						</nav>
					<% } %>
		</body>

		</html>