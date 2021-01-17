<style><%@include file="/WEB-INF/views/css/login.css"%></style>

${error}
<link href="../css/style.css" rel="stylesheet" type="text/css">
<div class="center">
	<div class="inner">
		<h1>Login Page</h1>
	</div>
	 
	<div class="inner">
		<form method="post" action="/login">
			<input type="text" name="username" placeholder="Username" value="${requestScope.email}"/>
			<input type="password" name="password" placeholder="Password" value="${requestScope.password}"/>
			<input type="submit" value="submit"/>
		</form> 
	</div>
</div>