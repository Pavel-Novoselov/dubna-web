<%@ page import="ru.geekbrains.comand.geetterbackend.helpers.JspUsers" %>
<%@ page import="ru.geekbrains.comand.geetterbackend.helpers.JspHelper" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.parser.ParseException" %>
<%@ page import="java.util.Objects" %>
<%@ page import="ru.geekbrains.comand.geetterbackend.helpers.JspTwits" %>

<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String userId = request.getParameter("userId");
    if (userId == null){
        userId = "1";
    }
%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="include_head.jsp"/>
    <title>Create tweet form page</title>
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

                <h3>Tweet for UserId <%=userId%></h3>
                <!-- Content Row -->
                <div class="row pl-5">
                    <form action="${pageContext.request.contextPath}/create_tweet" method="post">
                        <input type="hidden" name="userId" value="<%=userId%>">
                        <div class="form-group">
                            <label for="text">Text</label>
                            <input class="form-control" name="text" type="text" id="text"
                                   value="" required>
                        </div>
                        <div class="form-group">
                            <label for="is_published">Want to public? (true or false)</label>
                            <input class="form-control" name="is_published" type="text" id="is_published"
                                   value="true or false" required>
                        </div>
                        <div>
                            <button class="btn btn-success" type="submit">Create</button>
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


