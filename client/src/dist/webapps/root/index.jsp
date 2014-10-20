<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<% String contextPath = request.getContextPath(); %>
<% response.sendRedirect(response.encodeRedirectURL(contextPath + "/dashboard/splash.main")); %>