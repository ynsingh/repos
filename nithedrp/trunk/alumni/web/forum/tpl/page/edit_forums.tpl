<% 
	int boxid = 0;
	int eboxid = 0;
	int uboxid = 0;
	int umemberid = 0;
	List boxes, members;
	if(session.getAttribute("username") == null) {
 %>
 	<jsp:forward page="index.jsp?page=login"/>
 
<% }
	
	else {
		type = (String) session.getAttribute("type");
		if(!type.equals("Moderator") && !type.equals("Administrator")) {
 %>
 		<jsp:forward page="index.jsp?page=home"/>
 
<% 		}
		else {
			username = (String) session.getAttribute("username");
			Members.getMember(username);
			memberid = Members.getMemberid();
						
			if(request.getParameter("create") != null) {
%>
				<jsp:setProperty name="Boxes" property="memberid" value="<%= memberid %>"/>
				<jsp:setProperty name="Boxes" property="boxname"/>
				<jsp:setProperty name="Boxes" property="sortdesc"/>
<%
				if(Boxes.setBox())
					request.setAttribute("create", "Success");
				else
					request.setAttribute("create", "Error");
			}
			
			if(request.getParameter("delete") != null) {
				boxid = Integer.parseInt(request.getParameter("boxid"));
				
				if(Boxes.delBox(boxid))
					request.setAttribute("delete", "Success");
				else
					request.setAttribute("delete", "Error");
			}
			
			if(request.getParameter("save") != null) {
				String eboxname = request.getParameter("eboxname");
				String esortdesc = request.getParameter("esortdesc");
				boxid = Integer.parseInt(request.getParameter("boxid"));
%>
				<jsp:setProperty name="Boxes" property="boxname" value="<%= eboxname %>"/>
				<jsp:setProperty name="Boxes" property="sortdesc" value="<%= esortdesc %>"/>
<%
				if(Boxes.setBox(boxid))
					request.setAttribute("save", "Success");
				else
					request.setAttribute("save", "Error");
			}
			
			if(request.getParameter("assign") != null) {
				uboxid = Integer.parseInt(request.getParameter("uboxid"));
				umemberid = Integer.parseInt(request.getParameter("umemberid"));
				
				if(Boxes.assignBox(uboxid, umemberid))
					request.setAttribute("assign", "Success");
				else
					request.setAttribute("assign", "Error");
			}
			
			if(request.getAttribute("create") != null) {
				if(request.getAttribute("create").equals("Success"))
					message = "<img src='images/pass.gif'>&nbsp;<span class=message>New forum has been create. Thank you</span>";
				if(request.getAttribute("create").equals("Error"))
					message = "<img src='images/fail.gif'>&nbsp;<span class=errormsg>Could not create new forum. Please try again</span>";
				request.removeAttribute("create");
			}
			
			if(request.getAttribute("delete") != null) {
				if(request.getAttribute("delete").equals("Success"))
					message = "<img src='images/pass.gif'>&nbsp;<span class=message>Selected forum and all its threads have been deleted. Thank you</span>";
				if(request.getAttribute("delete").equals("Error"))
					message = "<img src='images/fail.gif'>&nbsp;<span class=errormsg>Could not delete the selected forum. Please try again</span>";
				request.removeAttribute("delete");
			}
			
			if(request.getAttribute("save") != null) {
				if(request.getAttribute("save").equals("Success"))
					message = "<img src='images/pass.gif'>&nbsp;<span class=message>Selected forum has been saved. Thank you</span>";
				if(request.getAttribute("save").equals("Error"))
					message = "<img src='images/fail.gif'>&nbsp;<span class=errormsg>Could not save the selected forum. Please try again</span>";
				request.removeAttribute("save");
			}
			
			if(request.getAttribute("assign") != null) {
				if(request.getAttribute("assign").equals("Success"))
					message = "<img src='images/pass.gif'>&nbsp;<span class=message>Selected forum has been assigned. Thank you</span>";
				if(request.getAttribute("assign").equals("Error"))
					message = "<img src='images/fail.gif'>&nbsp;<span class=errormsg>Could not assign the selected forum. Please try again</span>";
				request.removeAttribute("assign");
			}
		}
	}
%>

<script language="JavaScript">
	function confirmDel()
	{
		var agree=confirm("Are you sure to delete this forum and all of its threads?");
		if (agree)
			return true ;
		else
			return false ;
	}

	function checkSel()
	{
		if(document.selectFrm.eboxid.value == "")
		{
			alert("Please select a forum to edit");
			document.selectFrm.eboxid.focus();
			return false;
		}
		return true;
	}
	
	function checkAss()
	{
		if(document.assignFrm.uboxid.value == "")
		{
			alert("Please select a forum to assign");
			document.assignFrm.uboxid.focus();
			return false;
		}
		
		if(document.assignFrm.memberid.value == "")
		{
			alert("Please select a member to assign");
			document.assignFrm.memberid.focus();
			return false;
		}
		return true;
	}

	function checkCre() {
		if(document.createFrm.boxname.value == "") {
			alert("Please enter a forum name");
			document.createFrm.boxname.focus();
			return false;
		}
		if(document.createFrm.sortdesc.value == "") {
			alert("Please enter a forum description");
			document.createFrm.sortdesc.focus();
			return false;
		}
		return true;
	}

	function checkSave()
	{
		if(document.editFrm.eboxname.value == "") {
			alert("Please enter a forum name");
			document.editFrm.eboxname.focus();
			return false;
		}
		if(document.editFrm.esortdesc.value == "") {
			alert("Please enter a forum description");
			document.editFrm.esortdesc.focus();
			return false;
		}
		return true;
	}
</script>
<tr>
	<td valign="top" align="center">
		<span class="sub"><%= title %></span>
	</td>
</tr>
<tr>
	<td height="10"></td>
</tr>
<tr>
	<td align="center"><%= message %></td>
</tr>
<tr>
	<td height="10"></td>
</tr>

<form name="selectFrm" method="post" action="index.jsp?page=edit_forums">
<tr>
	<td align="center">
		<table border="0" cellspacing="1" cellpadding="3" align="center">
			<tr bgcolor="#6699CC"> 
				<td colspan="3" align="center"><font color="#FFFFFF">
					<b>Select Forum to Edit</b></font>
				</td>
			</tr>
			<tr bgcolor="#E7E7E7"> 
				<td width="120">
					&nbsp;Forums:
				</td>
				<td align="right">
					<select type="text" name="eboxid" style="width:255px;height:20px;">
						<option value="" selected>Select Forum</option>
					<% 
						if(type.equals("Administrator"))
							boxes = Boxes.getBoxes();
						else
							boxes = Boxes.getBoxes(memberid);
						
						if(boxes.size() != 0) {
							Iterator i1 = boxes.iterator();
							while(i1.hasNext()) {
								boxid = ((Integer) i1.next()).intValue();
								Boxes.getBox(boxid);

								out.print("<option value=" + Boxes.getBoxid() + " ");
								if(request.getParameter("eboxid") != null && boxid == Integer.parseInt(request.getParameter("eboxid")))
									out.println("selected=selected ");
								out.println(">" + Boxes.getBoxname() + "</option>");
							}
						}
					%>	
					</select>
				</td>
				<td align="right"><input type="submit" value="Select" onCLick="return checkSel()"></td>
			</tr>						
		</table>
	</td>
</tr>
</form>

<% if(request.getParameter("eboxid") != null) {
	eboxid = Integer.parseInt(request.getParameter("eboxid"));
	Boxes.getBox(eboxid);
%>
<form method="post" name="editFrm" action="index.jsp?page=edit_forums">
<input type="hidden" name="boxid" value="<%= Boxes.getBoxid() %>">
<tr>
	<td align="center">
		<table border="0" cellspacing="1" cellpadding="3" align="center">
			<tr bgcolor="#6699CC"> 
				<td colspan="2" align="center"><font color="#FFFFFF">
					&nbsp;&nbsp;<b>Edit Forum</b></font>
				</td>
			</tr>
			<tr bgcolor="#E7E7E7"> 
				<td width="120">
					&nbsp;Forum Name:
				</td>
				<td>
					<font face="Verdana"><input type = "text" name = "eboxname" size = 50 maxlength = 100 value = "<%= Boxes.getBoxname() %>"></font>
				</td>
			</tr>
			<tr bgcolor="#E7E7E7"> 
				<td width="120">
					&nbsp;Forum Description:
				</td>
				<td>
					<font face="Verdana"><input type = "text" name = "esortdesc" size = 50 maxlength = 100 value = "<%= Boxes.getSortdesc() %>"></font>
				</td>
			</tr>
			<tr bgcolor="#D9DBDE"> 
				<td colspan="2">
					<table width="100%">
						<tr>
							<td width="50%" align="left"><input name="delete" type="submit" value="Delete" onClick="return confirmDel()"></td>
							<td width="50%" align="right"><input name="save" type="submit" value="Save" onClick="return checkSave()"></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</td>
</tr>
</form>
<% } %>

<tr>
	<td height="10"></td>
</tr>
<form method="post" name="createFrm" action="index.jsp?page=edit_forums">
<tr>
	<td align="center">
		<table border="0" cellspacing="1" cellpadding="3" align="center">
			<tr bgcolor="#6699CC"> 
					<td colspan="2" align="center"><font color="#FFFFFF">
						&nbsp;&nbsp;<b>Create New Forum</b></font>
					</td>
				</tr>
			<tr bgcolor="#E7E7E7"> 
				<td  width="100">
					&nbsp;Forum Name:
				</td>
				<td align="right"><input type=text name="boxname" size="50"></td>
			</tr>
			<tr bgcolor="#E7E7E7"> 
				<td  width="120">
					&nbsp;Forum Description:
				</td>
				<td align="right"><input type=text name="sortdesc" size="50"></td>
			</tr>
			<tr bgcolor="#D9DBDE">
				<td colspan="3" align="right">
					<input type="submit" name="create" value="Create" onCLick="return checkCre()">
				</td>
			</tr>						
		</table>
	</td>
</tr>
</form>

<% if(type.equals("Administrator")) { 
	boxes = Boxes.getUnallocBoxes();
%>
<form method="post" name="assignFrm" action="index.jsp?page=edit_forums">
<tr>
	<td height="10"></td>
</tr>
<tr>
	<td align="center">
		<table border="0" cellspacing="1" cellpadding="3" align="center">
			<tr bgcolor="#6699CC"> 
				<td colspan="3" align="center"><font color="#FFFFFF">
					<b>Unassigned Forums</b></font>
				</td>
			</tr>
			<tr bgcolor="#E7E7E7"> 
				<td>
					<select type="text" name="uboxid" style="width:250px;height:20px;">
						<option value="" selected>Select Forum</option>
					<% 
						if(boxes.size() != 0) {
							Iterator i1 = boxes.iterator();
							while(i1.hasNext()) {
								boxid = ((Integer) i1.next()).intValue();
								Boxes.getBox(boxid);

								out.print("<option value=" + Boxes.getBoxid() + " ");
								if(request.getParameter("uboxid") != null && boxid == Integer.parseInt(request.getParameter("uboxid")))
									out.println("selected=selected ");
								out.println(">" + Boxes.getBoxname() + "</option>");
							}
						}
					%>	
					</select>
				</td>
				<td width="70" align="center">
					assign to:
				</td>
				<td>
					<select type="text" name="umemberid" style="width:120px;height:20px;">
						<option value="" selected>Select Member</option>
					<% 
						members = Members.getMods();

						if(members.size() != 0) {
							Iterator i2 = members.iterator();
							while(i2.hasNext()) {
								memberid = ((Integer) i2.next()).intValue();
								Members.getMember(memberid);

								out.print("<option value=" + Members.getMemberid() + " ");
								if(request.getParameter("umemberid") != null && memberid == Integer.parseInt(request.getParameter("umemberid")))
									out.println("selected=selected ");
								out.println(">" + Members.getUsername() + "</option>");
							}
						}
					%>	
					</select>
				</td>
			</tr>
			<tr bgcolor="#D9DBDE">
				<td colspan="3" align="right">
					<input type="submit" name="assign" value="Assign" onCLick="return checkAss()">
				</td>
			</tr>
		</table>
	</td>
</tr>
</form>
<% } %>