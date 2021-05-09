<%@ page import="ru.geekbrains.comand.geetterbackend.helpers.JspUsers" %>
<%@ page import="ru.geekbrains.comand.geetterbackend.helpers.JspHelper" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.parser.ParseException" %>
<%@ page import="java.util.Objects" %>

<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    final String userId = request.getParameter("id");
    JSONObject user = null;
    try {
        user = JspUsers.getUserById(userId, JspHelper.generateAbsURL(request));
    } catch (final ParseException e) {
        e.printStackTrace();
    }
%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="include_head.jsp"/>
    <title>Update user form page</title>
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
                    <h1 class="h3 mb-0 text-gray-800">User</h1>
                    <%
                        out.println("<form id=\"delUser\" action=\".." + request.getContextPath() + "/delete_user\" method=\"GET\">");
                        out.println("<input type=\"hidden\" name=\"id\" value=\"" + Objects.requireNonNull(user).get("id") + "\">");
                    %>
                    <a href="javascript:" onclick="document.getElementById('delUser').submit();"
                       class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                            class="fas fa-user-plus fa-sm text-white-50"></i> Delete user</a>
                    <%
                        out.println("</form>");
                    %>
                </div>

                <!-- Content Row -->
                <div class="row pl-5">

                    <form action="${pageContext.request.contextPath}/update_user" method="post">
                        <input type="hidden" name="id" value="<%=userId%>">
                        <div class="form-group">
                            <label for="username">Login</label>
                            <input class="form-control" name="username" type="text" id="username"
                                   value="<%=user.get("username")%>" required>
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input class="form-control" name="password" type="password" id="password"
                                   value="<%=user.get("password")%>" required>
                        </div>
                        <div class="form-group">
                            <label for="firstName">Name</label>
                            <input class="form-control" name="firstName" type="text" id="firstName"
                                   value="<%=user.get("firstName")%>">
                        </div>
                        <div class="form-group">
                            <label for="lastName">Last Name</label>
                            <input class="form-control" name="lastName" type="text" id="lastName"
                                   value="<%=user.get("lastName")%>">
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input class="form-control" name="email" type="email" id="email" aria-describedby="emailHelp"
                                   value="<%=user.get("email")%>">
                        </div>
                        <div class="form-group">
                            <label for="phone">Phone</label>
                            <input class="form-control" name="phone" type="tel" id="phone"
                                   value="<%=user.get("phone")%>" onkeyup="this.value=this.value.replace(/[^\d]/,'')">
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


