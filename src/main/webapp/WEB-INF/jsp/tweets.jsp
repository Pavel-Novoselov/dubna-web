<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.parser.ParseException" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="ru.geekbrains.comand.geetterbackend.helpers.JspHelper" %>
<%@ page import="java.util.Objects" %>
<%@ page import="ru.geekbrains.comand.geetterbackend.helpers.JspTwits" %>

<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    JSONArray jsonArrayTweets = null;
    try {
        jsonArrayTweets = JspTwits.getAllTwits(JspHelper.generateAbsURL(request));
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
                    <h1 class="h3 mb-0 text-gray-800">Tweets</h1>
                    <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                            class="fas fa-user-plus fa-sm text-white-50"></i> New tweet</a>
                </div>

                <!-- Content Row -->
                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">All tweets list</h6>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table stripe table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Created</th>
                                    <th>User(login)</th>
                                    <th>Text</th>
                                    <th>Comments</th>
                                    <th>isPublished</th>
                                    <th data-orderable="false">Actions</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>Id</th>
                                    <th>Created</th>
                                    <th>User(login)</th>
                                    <th>Text</th>
                                    <th>Comments</th>
                                    <th>isPublished</th>
                                    <th>Actions</th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <%
                                    // TODO: развернуть список твитов в обратном порядке
                                    if (jsonArrayTweets == null) {
                                        out.println("Error loading users!!!");
                                    }
                                    for (final Object o : Objects.requireNonNull(jsonArrayTweets)) {
                                        final JSONObject tweet = (JSONObject) o;
                                        final JSONObject user = (JSONObject) tweet.get("user");
                                        final JSONArray comments = (JSONArray) tweet.get("comments");
                                        final String commentsList = comments.toString().replaceAll("},", "<br>").replaceAll("[\\[\\]{}]","");
                                        out.println("<tr>");
                                        out.println("<td>" + tweet.get("id") + "</td>");
                                        out.println("<td> " + tweet.get("created") + "</td>");
                                        out.println("<td>" + user.get("username") + "</td>");
                                        out.println("<td>" + tweet.get("text") + "</td>");
                                        out.println("<td>");
                                        for (final Object c: Objects.requireNonNull(comments)) {
                                            final JSONObject comment = (JSONObject) c;
                                            final JSONObject userCommenter = (JSONObject) comment.get("user");
                                            out.println(comment.get("created") + ", text: " + comment.get("text") + ", user: " + userCommenter.get("username"));
                                            out.println("<br>");
                                        }
                                        out.println("</td>");
                                        out.println("<td>" + tweet.get("isPublished") + "</td>");
                                        out.println("<td style='white-space: nowrap;'><form action=\".." + request.getContextPath() + "/tweet\" method=\"GET\" style='display: inline-block;'>");
                                        out.println("<input type=\"hidden\" name=\"userId\" value=\"" + user.get("id") + "\">");
                                        out.println("<button class=\"btn btn-sm btn-success\" type=\"submit\"><i class=\"fas fa-user-edit\"></i></button>");
                                        out.println("</form>");
                                        out.println("<form action=\".." + request.getContextPath() + "/delete_tweet\" method=\"GET\" style='display: inline-block;'>");
                                        out.println("<input type=\"hidden\" name=\"id\" value=\"" + tweet.get("id") + "\">");
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
                <h4>Create tweet for User with ID</h4>
                <div class="row pl-5">

                    <form action="${pageContext.request.contextPath}/tweet" method="get">
                        <div class="form-group">
                            <label for="userId">User ID</label>
                            <input class="form-control" name="userId" type="text" id="userId"
                                   value="" required>
                        </div>
                        <div>
                            <button class="btn btn-success" type="submit">Create tweet</button>
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
