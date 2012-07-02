<%@ page import="java.lang.management.*, java.util.*" %><%  
 response.setContentType("text/plain");  
 Iterator iter = ManagementFactory.getMemoryPoolMXBeans().iterator();  
 while(iter.hasNext()){  
     MemoryPoolMXBean item = (MemoryPoolMXBean) iter.next();  
       MemoryUsage mu = item.getUsage();  
       long used      = mu.getUsed() / 1024 / 1024;  
       long committed = mu.getCommitted() / 1024 / 1024;  
       long max       = mu.getMax() / 1024 / 1024;  
       %>  
 MEMORY TYPE: <%=item.getName()%>  
 Used:        <%=used%>  
 Committed:   <%=used%>  
 Max:         <%=max%>  
 <%}%>  

