<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Administrator Configuration</title>
</head>
<body>
	<fieldset style="padding:10px;">
		<legend>Menu</legend>
		<ul>
			<li><a href="home">Home</a></li>
			<li><a href="session">Session Setup</a></li>
			<li><a href="timetable">View Timetable</a></li>
		</ul>
	</fieldset>
	<fieldset style="padding:10px;">
		<legend>Timetable View</legend>
		<ul>${listOfSession}</ul>
	</fieldset>

</body>
</html>
