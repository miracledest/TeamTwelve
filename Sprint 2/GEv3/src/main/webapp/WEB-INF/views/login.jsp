<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<form method="POST" action="login">
		<table>
			<tr>
				<td><label id="Label1">Username: </label></td>
				<td><input type="text" name="textboxUser" style="width: 155px;"></td>
			</tr>
			<tr>
				<td><label id="Label1">Password: </label></td>
				<td><input type="password" name="textboxPw"
					style="width: 155px;"></td>
			</tr>
		</table>
		<label id="Label1">${error}</label> <br /> <input name="Buttonlogin"
			type="submit" value="login" style="width: 199px" />
	</form>
</body>
</html>