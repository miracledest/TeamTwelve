<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Administrator Configuration</title>
<style>
td {
	padding: 5px;
}
</style>
</head>
<body>
	<fieldset style="padding: 10px;">
		<legend>Menu</legend>
		<ul>
			<li><a href="home">Home</a></li>
			<li><a href="session">Session Setup</a></li>
			<li><a href="timetable">View Timetable</a></li>
		</ul>
	</fieldset>
	<fieldset style="padding: 10px;">
		<legend>Session Setup</legend>
		<form>
			<table>
				<tr>
					<td><label>Name:</label></td>
					<td><input type="text" name="session_name" required="required" /></td>
				</tr>
				<tr>
					<td><label>Date:</label></td>
					<td><input type="date" name="session_date" required="required" /></td>
				</tr>
				<tr>
					<td><label>Time:</label></td>
					<td><input type="time" name="session_time" required="required" /></td>
				</tr>
				<tr>
					<td><label>Duration (Minutes):</label></td>
					<td><input type="number" name="session_duration" min="30"
						max="240" step="30" required="required" /></td>
				</tr>
				<tr>
					<td><label>Frequency:</label></td>
					<td><input type="number" name="session_frequency1" min="1"
						max="51" step="1" required="required" /> <select
						name="session_frequency2">
							<option value="null">-Select-</option>
							<option value="week">Week(s)</option>
							<option value="month">Month(s)</option>
							<option value="year">Year(s)</option>
					</select></td>
				</tr>
				<tr>
					<td><label>Lecturer:</label></td>
					<td><select name="session_lecturer">
							<option value="null">-Select-</option>
							<option value="let_1">Lecture 1</option>
							<option value="let_2">Lecture 2</option>
							<option value="let_3">Lecture 3</option>
					</select></td>
				</tr>
				<tr>
					<td><label>Max Attendance:</label></td>
					<td><input type="number" name="session_max_attendance" min="1"
						max="100" step="1" required="required" /></td>
				</tr>
				<tr>
					<td><label>Compulsory</label></td>
					<td><input type="radio" value="yes" name="session_compulsory" />Yes
						<input type="radio" value="no" name="session_compulsory" />No</td>
				</tr>
				<tr>
					<td><label>Venue:</label></td>
					<td><select name="session_venue">
							<option value="null">-Select-</option>
							<option value="ven_1">Venue 1</option>
							<option value="ven_2">Venue 2</option>
							<option value="ven_3">Venue 3</option>
					</select></td>
				</tr>
				<tr>
					<td><input type="submit" value="Create Session" /></td>
				</tr>
			</table>
		</form>
	</fieldset>

</body>
</html>
