<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.myapp.struts.cataloguing.SuggestTitle"%>
<%
    SuggestTitle db = new SuggestTitle();

    String query = request.getParameter("q");

    List<String> countries = db.getSuggestionList(query);

    Iterator<String> iterator = countries.iterator();
    while(iterator.hasNext()) {
        String country = (String)iterator.next();
        out.println(country);
    }
%>
