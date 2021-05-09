<%@ page import="ru.geekbrains.comand.geetterbackend.helpers.JspUsers" %>
<%@ page import="ru.geekbrains.comand.geetterbackend.helpers.JspHelper" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.parser.ParseException" %>
<%@ page import="java.util.Objects" %>
<%@ page import="org.json.simple.JSONArray" %>

<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    final String username = org.springframework.security.core.context.SecurityContextHolder.getContext()
            .getAuthentication().getName();

    JSONObject user = null;
    try {
        user = JspUsers.getUserByUsername(username, JspHelper.generateAbsURL(request));
    } catch (final ParseException e) {
        e.printStackTrace();
    }

    final String userId = (user.get("id") == null) ? "0" : user.get("id").toString();
    final String userFirstName = (user.get("firstName") == null) ? "" : user.get("firstName").toString();
    final String userLastName = (user.get("lastName") == null) ? "" : user.get("lastName").toString();
    final String userEmail = (user.get("email") == null) ? "" : user.get("email").toString();
    final String userPhone = (user.get("phone") == null) ? "" : user.get("phone").toString();
    final JSONArray roles = (JSONArray) Objects.requireNonNull(user).get("roles");

    StringBuilder sb = new StringBuilder();
    for (final Object c : Objects.requireNonNull(roles)) {
        final JSONObject role = (JSONObject) c;
        final String userRole = (String) role.get("name");
        sb.append(userRole).append(", ");
    }
    String rolesString = sb.toString();
%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="include_head.jsp"/>
    <title>Profile page</title>
</head>
<body class="bg-gradient-primary">

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <jsp:include page="sidebar.jsp"/>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <jsp:include page="header.jsp"/>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800"><%=userFirstName%> <%=userLastName%> Profile Data</h1>
                    <%
                        out.println("<form id=\"delUser\" action=\".." + request.getContextPath() + "/delete_user\" method=\"GET\">");
                        out.println("<input type=\"hidden\" name=\"id\" value=\"" + Objects.requireNonNull(user).get("id") + "\">");
                    %>
                    <% //href="javascript:" onclick="document.getElementById('delUser').submit();" %>
                    <a href="javascript:" onclick="document.getElementById('delUser').submit();"
                       class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                            class="fas fa-user-plus fa-sm text-white-50"></i> Delete account</a>
                    <%
                        out.println("</form>");
                    %>
                </div>

                <!-- Content Row -->
                <div class="row pl-5">
                    <form action="${pageContext.request.contextPath}/update_profile" method="post">
                        <input type="hidden" name="id" value="<%=userId%>">
                        <input type="hidden" name="username" value="<%=username%>">
                        <div class="form-group">
                            <div for="username">Login</div>
                            <div><%=username%></div>
                            <div><%=rolesString%></div>
                        </div>
                        <div class="form-group">
                            <label for="firstName">Name</label>
                            <input class="form-control" name="firstName" type="text" id="firstName"
                                   value="<%=userFirstName%>">
                        </div>
                        <div class="form-group">
                            <label for="lastName">Last Name</label>
                            <input class="form-control" name="lastName" type="text" id="lastName"
                                   value="<%=userLastName%>">
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input class="form-control" name="email" type="email" id="email"
                                   aria-describedby="emailHelp"
                                   value="<%=userEmail%>">
                        </div>
                        <div class="form-group">
                            <label for="phone">Phone</label>
                            <input class="form-control" name="phone" type="tel" id="phone"
                                   value="<%=userPhone%>" onkeyup="this.value=this.value.replace(/[^\d]/,'')">
                        </div>
                        <div>
                            <button class="btn btn-success" type="submit">Update</button>
                        </div>
                    </form>

                </div>

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <jsp:include page="footer.jsp"/>
        <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->
</body>
<jsp:include page="#"/>
</html>


