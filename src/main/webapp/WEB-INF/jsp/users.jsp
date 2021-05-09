<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page import="ru.geekbrains.comand.geetterbackend.helpers.JspUsers" %>

<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.parser.ParseException" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="ru.geekbrains.comand.geetterbackend.helpers.JspHelper" %>
<%@ page import="java.util.Objects" %>

<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    JSONArray jsonArrayUsers = null;
    try {
        jsonArrayUsers = JspUsers.getAllUsers(JspHelper.generateAbsURL(request));
    } catch (final ParseException e) {
        e.printStackTrace();
    }
%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="include_head.jsp"/>
    <title>Home page</title>
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
                    <h1 class="h3 mb-0 text-gray-800">Users</h1>
                    <a href="${pageContext.request.contextPath}/add_user_form" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                            class="fas fa-user-plus fa-sm text-white-50"></i> New user</a>
                </div>

                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Users list</h6>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table stripe table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Login</th>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>E-mail</th>
                                    <th>Phone</th>
                                    <th data-orderable="false">Actions</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>Id</th>
                                    <th>Login</th>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>E-mail</th>
                                    <th>Phone</th>
                                    <th>Actions</th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <%
                                    if (jsonArrayUsers == null) {
                                        out.println("Error loading users!!!");
                                    }
                                    for (final Object o : Objects.requireNonNull(jsonArrayUsers)) {
                                        final JSONObject user = (JSONObject) o;
                                        out.println("<tr>");
                                        out.println("<td>" + user.get("id") + "</td>");
                                        out.println("<td>" + user.get("username") + "</td>");
                                        out.println("<td> " + user.get("firstName") + "</td>");
                                        out.println("<td>" + user.get("lastName") + "</td>");
                                        out.println("<td>" + user.get("email") + "</td>");
                                        out.println("<td>" + user.get("phone") + "</td>");
                                        out.println("<td style='white-space: nowrap;'><form action=\".." + request.getContextPath() + "/user\" method=\"GET\" style='display: inline-block;'>");
                                        out.println("<input type=\"hidden\" name=\"id\" value=\"" + user.get("id") + "\">");
                                        out.println("<button class=\"btn btn-sm btn-success\" type=\"submit\"><i class=\"fas fa-user-edit\"></i></button>");
                                        out.println("</form>");
                                        out.println("<form action=\".." + request.getContextPath() + "/delete_user\" method=\"GET\" style='display: inline-block;'>");
                                        out.println("<input type=\"hidden\" name=\"id\" value=\"" + user.get("id") + "\">");
                                        out.println("<button class=\"btn btn-sm btn-danger\" type=\"submit\"><i class=\"fas fa-trash-alt\"></i></button>");
                                        out.println("</form>");
                                        out.println("</td>");
                                        out.println("</tr>");
                                    }
                                %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <!-- Content Row -->
                <div class="row">

                </div>
            </div>

        </div>
        <!-- /.container-fluid -->

    </div>
    <!-- End of Main Content -->

</div>
<!-- End of Content Wrapper -->
<!-- Footer -->
<jsp:include page="footer.jsp"/>
<!-- End of Footer -->

<!-- End of Page Wrapper -->
</body>
<jsp:include page="#"/>
</html>
