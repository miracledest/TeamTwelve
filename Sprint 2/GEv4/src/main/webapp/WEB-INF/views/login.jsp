<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<fieldset>
		<legend>Login</legend>
		<form method="POST" action="login">
			<table>
				<tr>
					<td><label id="Label1">Username: </label></td>
					<td><input type="text" name="textboxUser" required="required"></td>
				</tr>
				<tr>
					<td><label id="Label1">Password: </label></td>
					<td><input type="password" name="textboxPw"
						required="required"></td>
				</tr>
				<tr>
					<td colspan="2"><input name="Buttonlogin" type="submit"
						value="Login" /></td>
				</tr>
			</table>
			${error}
		</form>
	</fieldset>

</body>
</html>