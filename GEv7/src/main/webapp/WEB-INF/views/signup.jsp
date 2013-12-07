<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Sign Up</title>
<style>
body{
width: 800px;
margin: 0 auto;
}
</style>
</head>
<body>
	<div>
		<fieldset>
			<legend>Logged In</legend>
			<label>${user_msg}</label>
			<form method="GET" action="logout">
				<input type="hidden" value="true" name="logout" /> <input
					type="submit" value="Logout" />
			</form>
		</fieldset>
	</div>
	<div>
		<fieldset style="padding: 10px;">
			<legend>Menu</legend>
			<ul>${user_menu_1} ${user_menu_2}
			</ul>
		</fieldset>
	</div>
	<div>
		<fieldset style="padding: 10px;">
			<legend>Available Session</legend>
			<form method="POST" action="signup" name="sign">
				<table>${listOfSession}
				</table>
				${signup_button}
				<input type="hidden" value="" name="signup_session[]" />
			</form>

		</fieldset>
	</div>
</body>
</html>
