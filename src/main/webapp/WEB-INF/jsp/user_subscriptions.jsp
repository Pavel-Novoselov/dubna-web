<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page import="ru.geekbrains.comand.geetterbackend.helpers.JspUsers" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.parser.ParseException" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="ru.geekbrains.comand.geetterbackend.helpers.JspHelper" %>
<%@ page import="java.util.Objects" %>

<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    final String currentUsername = org.springframework.security.core.context.SecurityContextHolder.getContext()
            .getAuthentication().getName();

    JSONObject currentUser = null;
    try {
        currentUser = JspUsers.getUserByUsername(currentUsername, JspHelper.generateAbsURL(request));
    } catch (final ParseException e) {
        e.printStackTrace();
    }

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
    <title>User subscriptions page</title>
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
                    <h1 class="h3 mb-0 text-gray-800">User Subscriptions</h1>

                </div>

                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">All Users list</h6>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table stripe table-bordered" id="dataTable">
                                <thead>
                                <tr>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th data-orderable="false">Subscribe/Unsubscribe</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th data-orderable="false">Subscribe/Unsubscribe</th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <%
                                    if (jsonArrayUsers == null) {
                                        out.println("Error loading users!!!");
                                    }
                                    final JSONArray subscriptionUsers = (JSONArray) Objects.requireNonNull(currentUser).get("subscriptions");
                                    for (final Object o : Objects.requireNonNull(jsonArrayUsers)) {
                                        final JSONObject targetUser = (JSONObject) o;
                                        if (targetUser.get("username").equals(currentUsername)) continue;
                                        out.println("<tr>");
                                        out.println("<td> " + targetUser.get("firstName") + "</td>");
                                        out.println("<td>" + targetUser.get("lastName") + "</td>");
                                        out.println("<td style='white-space: nowrap;'>");
                                        if (!subscriptionUsers.toJSONString().contains(targetUser.get("username").toString())) {
                                            out.println("<form action=\".." + request.getContextPath() + "/subscribe\" method=\"POST\" style='display: inline-block;'> " +
                                                    "<input type=\"hidden\" name=\"id\" value=\"" + currentUser.get("id") + "\">" +
                                                    "<input type=\"hidden\" name=\"target\" value=\"" + targetUser.get("id") + "\">" +
                                                    "<button class=\"btn btn-sm btn-success\" type=\"submit\"><i class=\"fas fa-user-plus\"></i></button>" +
                                                    "</form>");
                                        } else {
                                            out.println("<form action=\".." + request.getContextPath() + "/unsubscribe\" method=\"POST\" style='display: inline-block;'> " +
                                                    "<input type=\"hidden\" name=\"id\" value=\"" + currentUser.get("id") + "\">" +
                                                    "<input type=\"hidden\" name=\"target\" value=\"" + targetUser.get("id") + "\">" +
                                                    "<button class=\"btn btn-sm btn-danger\" type=\"submit\"><i class=\"fas fa-user-slash\"></i></button>" +
                                                    "</form>");
                                        }
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
