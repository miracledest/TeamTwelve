<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Administrator Configuration</title>
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
			<ul>
				${user_menu_1}
			</ul>
		</fieldset>
	</div>
	<div>
		<fieldset style="padding: 10px;">
			<legend>Timetable View</legend>
			<form method="POST" action="timetable">
				<select name="view_option">
					<option value="today">Today</option>
					<option value="week">This Week</option>
					<option value="all">All</option>
				</select> <input type="submit" value="View" />
			</form>
			${timetableHeader} ${listOfSession}
		</fieldset>
	</div>
</body>
</html>
