<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Administrator Configuration</title>
<style>
body{
width: 800px;
margin: 0 auto;
}
td {
	padding: 5px;
}

.fieldset-auto-width {
	display: inline-block;
}
</style>
<script type="text/javascript">
${session_script}
</script>
</head>
<body>
	<div>
		<fieldset>
		<legend>Logged In</legend>
		<label>${admin_msg}</label>
		<form method="GET" action ="logout">
				<input type="hidden" value="true" name="logout" />
				<input type="submit" value="Logout" />
			</form>
		</fieldset>
	</div>
	<div>
		<fieldset style="padding: 10px;">
			<legend>Menu</legend>
			<ul>
				${admin_menu_1}
			</ul>
			
		</fieldset>
	</div>
	<div>
		<fieldset style="padding: 10px;">
			<legend>Session Setup</legend>
			<form method="POST" action="session">
				<table>
					<tr>
						<td><label>Name:</label></td>
						<td><input type="text" name="session_name"
							required="required" /></td>
					</tr>
					<tr>
						<td><label>Date:</label></td>
						<td><input type="date" name="session_date"
							required="required" /></td>
					</tr>
					<tr>
						<td><label>Time:</label></td>
						<td><input type="time" name="session_time"
							required="required" /></td>
					</tr>
					<tr>
						<td><label>Duration (Minutes):</label></td>
						<td><input type="number" name="session_duration" min="30"
							max="240" step="30" required="required" /></td>
					</tr>
					<tr>
						<td><label>Frequency:</label></td>
						<td><input type="number" name="session_frequency1" min="0"
							max="52" step="1" required="required" /> <select
							name="session_frequency2">
								<option value="week">Week(s)</option>
								<option value="month">Month(s)</option>
						</select></td>
					</tr>
					<tr>
						<td><label>Lecturer:</label></td>
						<td><select name="session_lecturer">
								<option value="Shi Jie">Shi Jie</option>
								<option value="Larry">Larry</option>
								<option value="Zhi Yong">Zhi Yong</option>
								<option value="Jack">Jack</option>
						</select></td>
					</tr>
					<tr>
						<td><label>Max Attendance:</label></td>
						<td><input type="number" name="session_max_attendance"
							min="1" max="100" step="1" required="required" /></td>
					</tr>
					<tr>
						<td><label>Compulsory</label></td>
						<td><input type="radio" value="Yes" name="session_compulsory" />Yes
							<input type="radio" value="No" name="session_compulsory" />No</td>
					</tr>
					<tr>
						<td><label>Venue:</label></td>
						<td><select name="session_venue">
								<option value="Lab 1">Lab 1</option>
								<option value="Lecture Hall 1">Lecture Hall 1</option>
								<option value="Example Lab 1">Example Lab 1</option>
						</select></td>
					</tr>
					<tr>
						<td><input type="submit" value="Create Session" /></td>
					</tr>
				</table>
			</form>
		</fieldset>
	</div>
</body>
</html>
