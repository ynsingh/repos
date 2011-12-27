<h2><?=$institute->name?></h2>
<table id="right">
	<thead>
		<tr>
			<th>Degree</th>
			<th>Discipline</th>
			<th>Seats</th>
		</tr>
	</thead>
<?foreach($courses as $course):?>
	<tr>
		<td><?=R::load('degree',$course->degree)->name;?></td>
		<td><?=R::load('discipline',$course->discipline)->name;?></td>
		<td><?=$course->seats;?></td>
	</tr>
<?endforeach;?>
</table>
<form method="POST" id="form_left">
Add a new Course
	<input type="hidden" name="action" value="add">
	<table>
		<tr><td>Degree Name</td><td>
			<select name="degree">
				<option value="B.Tech">B.Tech</option>
				<option value="B.B.A">B.B.A</option>
				<option value="M.Tech">M.Tech</option>
				<option value="MBA">MBA</option>
			</select>
		</td></tr>
		<tr>
			<td>Discipline</td>
			<td><select name="discipline">
				<option value="Computer Science & Engineering">Computer Science & Engineering</option>
				<option value="Chemical Engineering">Chemical Engineering</option>
				<option value="Something else">Something else</option>
				<option value="Add other disciplines here">Add other disciplines here</option>
			</select><br>Other: <input name="discipline2" placeholder="Any other discipline"></td>
		</tr>
		<tr>
			<td>Seats</td>
			<td><input type="number" value="30" name="seats"></td>
		</tr>
		<tr><td><input type="reset" value="Clear"></td><td><input type="submit" value="Add Course"></td></tr>
	</table>
</form>
<br><br>
<span class="clear"></span>

