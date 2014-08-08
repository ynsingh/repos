<!DOCTYPE HTML PUBLIC "-//W3C//DTD TML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" href="../css/style.css" type="text/css" />
</head>--%>

<link rel="stylesheet" href="../css/style.css" type="text/css" />
        <%
        try {
            String Id = session.getAttribute("username").toString();
            String menuItem, menuItemHref, nextMenuItem, menuEnvVariable;
            int FirstList, SecondList;
            int jIncremented, kIncremented, i, j, k;
            i = j = k = 0;
            out.println("<ul id='css3menu1' class='tomenu'>");


            menuItem = "Menu[" +  i + "][" + j  +"][" + 0 + "]";
            while(session.getAttribute(menuItem) != null){
                j = 0;
                FirstList = 0;
                SecondList = 0;
                menuItem = "Menu[" +  i + "][" + j  +"][" + 0 + "]";
                menuItemHref = "MenuHref[" +  i + "][" + j  +"][" + 0 + "]";
                out.println("<li class='topmenu'><a href='" +  session.getAttribute(menuItemHref) + "' style='height:18px;line-height:18px;width:142px;'>" + session.getAttribute(menuItem) +  "</a>");

                FirstList = FirstList + 1;
                
                jIncremented = j + 1;
                nextMenuItem = "Menu[" +  i + "][" + jIncremented  +"][" + 0 + "]";
               
                if(session.getAttribute(nextMenuItem) != null) {
                    SecondList = SecondList + 1;
                    out.println("1<ul>");
                }
                while (session.getAttribute(nextMenuItem) != null) {
                    k = 0;
                    j = j + 1;                    
                    menuItem = "Menu[" +  i + "][" + j  +"][" + k + "]";
                    menuItemHref = "MenuHref[" +  i + "][" + j  +"][" + 0 + "]";                
                    //out.println("<li><a href='" + session.getAttribute(menuItemHref)+ "'><span>" + session.getAttribute(menuItem) + "</span></a>");
                    //FirstList = FirstList + 1;
                    kIncremented = k + 1;
                    nextMenuItem = "Menu[" +  i + "][" + j  +"][" + kIncremented  + "]";
                    if(session.getAttribute(nextMenuItem) != null)
                            {
                                FirstList = FirstList + 1;
                                out.println("<li><a href='" + session.getAttribute(menuItemHref)+ "'><span>" + session.getAttribute(menuItem) + "</span></a>");
                                SecondList = SecondList + 1;
                                out.println("<ul>");
                            }
                    else {
                        out.println("<li><a href='" + session.getAttribute(menuItemHref)+ "'>" + session.getAttribute(menuItem) + "</a></li>");
                        FirstList = FirstList + 1;
                        }
                        while (session.getAttribute(nextMenuItem) != null) {
                            k = k + 1;
                            menuItem = "Menu[" +  i + "][" + j  +"][" + k + "]";
                            menuItemHref = "MenuHref[" +  i + "][" + j  +"][" + k + "]";                            
                            out.println("<li><a href='" + session.getAttribute(menuItemHref)+ "'>" + session.getAttribute(menuItem) + "</a></li>");
                            kIncremented = k + 1;
                            nextMenuItem = "Menu[" +  i + "][" + j  +"][" + kIncremented + "]";
                            if (SecondList > 0 && session.getAttribute(nextMenuItem) == null){
                                out.println("</ul>");
                                out.println("</li>");
                                SecondList = SecondList - 1;
                            }
                        }
                        jIncremented = j + 1;
                        nextMenuItem = "Menu[" +  i + "][" + jIncremented  +"][" + 0 + "]";                     
                        }
                if (FirstList > 0 && session.getAttribute(nextMenuItem) == null){
                    if (SecondList > 0) {
                        out.println("</ul>");
                        SecondList = SecondList - 1;
                        }
                        out.println("</li>");
                        FirstList = FirstList - 1;
                    }
                i = i + 1;
                j = k = 0;
                menuItem = "Menu[" +  i + "][" + j  +"][" + k + "]";

                } //End While
           // out.println("4</ul>");
            //out.println("====4</li>");
            }
            catch (Exception e) {
        }
        %>     	
                     <%
                     try {
                            String IM_SN = session.getAttribute("imshortname").toString();
                            String SIM_SN = session.getAttribute("simshortname").toString();
                            String DM_SN = session.getAttribute("dmshortname").toString();
                            String ERPMU_Full_Name = session.getAttribute("userfullname").toString();


                            out.println("8<li><a href='#' title='Current Profile' style='width:250px;'>" +
                                                                IM_SN + "/" + SIM_SN + "/" + DM_SN + "/" + ERPMU_Full_Name
                                                                + "</a>8</li>");
                            }
                    catch (Exception e) {
                            //out.println("Exception!!" + e.getMessage());
                        }
                    %>
</ul>

<%--</body>
</html>--%>