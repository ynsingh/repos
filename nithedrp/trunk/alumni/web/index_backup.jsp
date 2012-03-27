<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page session="true" import="java.util.Calendar" %>
<%@page import="java.util.Date" %>
<%@page import="java.io.IOException"%>
<%@page import="java.util.Properties"%>
<%@page import="java.io.*"%>
<%
   response.setHeader( "Pragma", "no-cache" );
   response.setHeader( "Cache-Control", "no-cache" );
   response.setDateHeader( "Expires", 0 );
%>
<%
              Properties props = new Properties();
              // try {
              String fileName = getServletContext().getRealPath("config.properties");
              InputStream in = new FileInputStream(fileName);

              //props.load(new FileInputStream(fileName));
              props.load(in);
              String message = props.getProperty("conf");
              if (in != null) {
                  in.close();
              }
              if (message.equals("false")) {
                  response.sendRedirect("configure.jsp");
              } //           }
              //catch exception in case properties file does not exist
              //
              //           catch(IOException e)
              //         {
              //       e.printStackTrace();
              //     }
              //if(db.getconf()==0)
              //{
              // response.sendRedirect("configure.jsp");
              //}
	else
	 {
  %>
 
  
  <html>
      <head>
       	<link rel="stylesheet" type="text/css" href="style.css" title="style" />
        <link type="text/css" rel="stylesheet" href="yui/build/calendar/assets/skins/sam/calendar.css" />
        <link rel="stylesheet" type="text/css" href="yui/build/container/assets/skins/sam/container.css" />
        <link rel="stylesheet" type="text/css" href="yui/build/editor/assets/skins/sam/editor.css" />
    	<script type="text/javascript" src="yui/build/yahoo/yahoo.js"></script> 
	<script type="text/javascript" src="yui/build/event/event.js" ></script> 
        <script type="text/javascript" src="yui/build/dom/dom.js" ></script> 
        <script type="text/javascript" src="yui/build/calendar/calendar.js"></script> 
        <script type="text/javascript" src="yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
        <script type="text/javascript" src="yui/build/dragdrop/dragdrop-min.js"></script>
        <script type="text/javascript" src="yui/build/container/container-min.js"></script>
        <script type="text/javascript" src="yui/build/element/element-min.js"></script>
        <script type="text/javascript" src="yui/build/editor/editor-min.js"></script>
      
          <style>
		  #container{
			  position:absolute;
		  }
          </style>    
      </head>
      <%@ include file="includes/header.jsp" %>
           <body>
           
           	<!-- header -->
            <div class="content">
           	                          
          	<div class="calContainer" style="background-color:#F8F8F8">
                 <div class="yui-skin-sam" style="margin:0.5em 0.5em 0.5em 0.5em;">
                  <h2>Event Calendar</h2>
                <div id="cal1Container" style="margin-bottom:1em;"></div>
                <p><a style="font-weight:bolder" href="listevents.jsp">&#187;&#187;List Events</a> 
                <%
				if((session.getAttribute("username")!=null)&&(session.getAttribute("type").equals("Administrator")))
				{				
				%>||         
                <a style="font-weight:bolder" id="update">&#187;&#187;Add Event</a></p><%}%>                   
              	</div><!-- End Calendar-->
            </div>  
          <!-- End leftContainer-->
          
          <!-- div right info -->        
          <div class="rightContainer">
          <%@ include file="includes/rightcontainer.jsp" %> 	
          </div>   
          <!-- End rightContainer--> 
          
          <!-- div main info--> 
          <div class="centerContainer" style="background-color: #F4FFFF">
          		<!-- div main info -->
          		<% quer = " select title,message from divtext where (divid='welcome'&& divtype='center')";
				rs = stmt.executeQuery(quer);
				if(rs.next())
				{
				%>
                	<h2><%= rs.getString(1)%></h2>
                	<p> <%=rs.getString(2)%></p>
                <%
				}		
				
                if((session.getAttribute("username")!=null)&&(session.getAttribute("type").equals("Administrator")))
		  		{
			  	%>
			  		<p><a id="welcomeEdit"><span >&#187;&#187;Edit Welcome Message</span></a></p>
		  		<%
          		}
		  		%>                
          
          		
            <!-- end of div main info-->
          </div>
          <!-- end of div main container -->
          
          <!--JSP and javascript code to create eventarray -->
          <%
          Calendar c = Calendar.getInstance();
          int currMonth = c.get(Calendar.MONTH);
		  Date d = c.getTime();
		  java.sql.Date sqldate = new java.sql.Date(d.getTime());
		  
		  quer = "select date,ttip,title from calendar1 where date>='"+sqldate+"'";
          rs = stmt.executeQuery(quer);
		  %>
          <script type="text/javascript">
		  function evnt()
                      {
                          this.day = 0;
                          this.month = 0;
                          this.year=0;
						  this.ttip="";
						  this.title="";
                      }
				var evntArray =new Array();
				i=0;
			</script>
			<%
				while(rs.next())
				{
					sqldate = rs.getDate(1);
			%>		
			<script type="text/javascript">
				evntArray[i]=new evnt();
				evntArray[i].day=<%=sqldate.getDate()%>;
				evntArray[i].month=<%=sqldate.getMonth()%>;
				evntArray[i].year=<%=sqldate.getYear()%>;
				evntArray[i].year+=1900;
				evntArray[i].ttip="<%=rs.getString(2)%>";
				evntArray[i].title="<%=rs.getString(3)%>";
				i++;
            </script>
			<%
				}
			%>
      	  <!-- end of JSP code -->
          
          <!-- Script to generate calendar on home page --> 
          <script type="text/javascript" src="includes/eventcal.js"></script> 
                
          <!-- script to generate panels -->   
          <script>
              function init1() 
              {
				  YAHOO.example.calendar.newspanel = new YAHOO.widget.Panel("newspanel", {width:'800px', underlay:'shadow', modal:true , visible:false, constraintoviewport:true});
				  YAHOO.example.calendar.centerpanel = new YAHOO.widget.Panel("centerpanel", { width:'800px', underlay:'shadow', modal:true , visible:false, constraintoviewport:true});
				  YAHOO.example.calendar.headerpanel = new YAHOO.widget.Panel("headerpanel", { underlay:'shadow', modal:true , visible:false, constraintoviewport:true}); 
                  YAHOO.example.calendar.panel1 = new YAHOO.widget.Panel("panel1", {width:'800px', underlay:'shadow', modal:true , visible:false, constraintoviewport:true } );
                  YAHOO.example.calendar.panel1.render();
				  YAHOO.example.calendar.newspanel.render();
				  YAHOO.example.calendar.centerpanel.render();
				  YAHOO.example.calendar.headerpanel.render();
                  YAHOO.util.Event.addListener("newsEdit", "click", YAHOO.example.calendar.newspanel.show, YAHOO.example.calendar.newspanel,true);
				  YAHOO.util.Event.addListener("update", "click", YAHOO.example.calendar.panel1.show, YAHOO.example.calendar.panel1, true);
				  YAHOO.util.Event.addListener("headerEdit", "click", YAHOO.example.calendar.headerpanel.show, YAHOO.example.calendar.headerpanel,true);
				  YAHOO.util.Event.addListener("welcomeEdit", "click", YAHOO.example.calendar.centerpanel.show, YAHOO.example.calendar.centerpanel,true);
              }
              YAHOO.util.Event.addListener(window, "load", init1);
          </script>
 			      
          <!-- end of script to generate panels -->
          
      	  <!-- script to generate multiselect calendar on add event panel -->
          <script  type="text/javascript" src="includes/multiselectCal.js"></script>
		             
          <!-- script to generate rich text editor-->
          <script>
				var myEditor = new YAHOO.widget.Editor('message', {
				height: '300px',
				width: 'inherit',
				dompath: true, //Turns on the bar at the bottom
				animate: true, //Animates the opening, closing and moving of Editor windows
				handleSubmit: true,
				focusAtStart: true
				});
				myEditor.render();
				
				var myEditor1 = new YAHOO.widget.Editor('news_message', {
				height: '150px',
				width: 'inherit',
				dompath: true, //Turns on the bar at the bottom
				animate: true, //Animates the opening, closing and moving of Editor windows
				handleSubmit: true,
				focusAtStart: true
				});
				myEditor1.render();
				
				var myEditor2 = new YAHOO.widget.Editor('center_message', {
				height: '200px',
				width: 'inherit',
				dompath: true, //Turns on the bar at the bottom
				animate: true, //Animates the opening, closing and moving of Editor windows
				handleSubmit: true,
				focusAtStart: true
				});
				myEditor2.render();
		   </script>
          <!-- end of script to generate rich text editor -->              
         
         <%
				if((session.getAttribute("username")!=null)&&(session.getAttribute("type").equals("Administrator")))
				{%>
          <div class="yui-skin-sam">
                <!-- add event panel -->
                <div id="panel1" style=" font-size: 12px; font-family:'Times New Roman', Times, serif;">
                    <div class="hd" style="font-size:14px">Add/Edit Event</div>
                    
                    	<div class="bd">
                        	<div  style="margin-right:16em;">
                            	<form action="includes/entry.jsp"><br>
                                  <b>Title &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b> <input type="text" name="title" id="title"  style="width:500px"><br><br>
                                  <b>ToolTip Text :</b> <input type="text" name="ttip" id="ttip"  style="width:500px"><br><br> 
                                  <textarea id="message" name="message"></textarea>
                                  <br> Date :
                                  <input type="text" name="in" id="in">To<input type="text" name="out"  id="out"><br><br>
                                  <input type=submit value=submit>
                                  <input type="reset" value="clear">
                                 </form>
                            </div>
                            
                            <div style=" position:absolute; top:35px; right:1em; width:15em;" ><div style="text-align:center; width:100%; font-size:16px;"><b>Select Period</b></div>
                            <div id="cal2Container"></div>
                            </div>
                            
                     	</div>
                </div>	
                <!-- end add event panel -->
                	
          		<!-- edit news panel -->
                  <div id="newspanel">
                    <div class="hd">ADD/EDIT NEWS</div>
                    <div class="bd">
                          <form action="includes/editnews.jsp">
                          Div : 
                          <select  name="menu">
                          <option value="news1">news1</option> 
                          <option value="news2">news2</option> 
                          <option value="news3">news3</option>
                          <option value="news4">news4</option>
                          <option value="news5">news5</option>
                          <option value="news6">news6</option>
                          </select>
                          <br /><br />
                          Heading : <input type="text" name="title" id="title"/><br><br />
                          Message : <br /><br />
                          <textarea id="news_message" name="news_message"></textarea><br />
                          <input type=submit value=submit>
                          <input type="reset" value="clear">
                          </form>
                    </div>
                  </div>
                  <!-- end edit news panel -->
                  <!--edit header panel -->
                  <div id="headerpanel">
                    <div class="hd">EDIT COLLEGE NAME AND LOGO</div>
                    <div class="bd">
                          <form action="includes/editheader.jsp">
                                            
                          College Name : <input type="text" name="collegename" id="collegename" value="National Institute of Technology, Hamirpur"/><br><br />
                          Logo Url&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : <input type="text" name="logourl" id="logourl" value="includes/logo-nith.png"/>
                          <br />
                          <p><input type=submit value=submit>
                          <input type="reset" value="clear"></p>
                          </form>
                    </div>
                  </div>
                  <!-- end edit header panel -->
                  <!--edit welcome message panel -->
                  <div id="centerpanel">
                    <div class="hd">Edit Welcome Message</div>
                    <div class="bd">
                          <form action="includes/centerEdit.jsp">
                          Title : <input type="text" name="title" id="title" value="Welcome Message"/><br><br />
                          Message : 
                          <textarea id="center_message" name="center_message"></textarea><br />
                          <br />
                          <p><input type=submit value=submit>
                          <input type="reset" value="clear"></p>
                          </form>
                    </div>
                  </div>
                  <!-- end edit welcome message panel -->
          </div><%}%>
           <!-- end yui-skin-sam -->
          <!-- div for tool tip panel -->
          <div id="container" class="yui-skin-sam" style="font-size:12px;"></div>
          <!-- end of panel for tool tip -->
          </div>
      </body>
  </html>
  <%
  }
  %>