<%@ page import="ru.geekbrains.comand.geetterbackend.entities.dto.UserDto" %>
<%@ page import="ru.geekbrains.comand.geetterbackend.helpers.JspUsers" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.URI" %>
<%@ page import="ru.geekbrains.comand.geetterbackend.helpers.JspHelper" %>
<%@ page import="org.json.simple.parser.ParseException" %><%--
  Created by IntelliJ IDEA.
  User: Pavel
  Date: 28.03.2021
  Time: 1:02
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%
    UserDto updateUser = new UserDto();
    updateUser.setId(Long.parseLong(request.getParameter("id")));
    updateUser.setUsername(request.getParameter("username"));
    if (request.getParameter("password") != null) updateUser.setPassword(request.getParameter("password"));
    updateUser.setFirstName(request.getParameter("firstName"));
    updateUser.setLastName(request.getParameter("lastName"));
    updateUser.setEmail(request.getParameter("email"));
    updateUser.setPhone(request.getParameter("phone"));

    String url = JspHelper.generateAbsURL(request);
    String result = JspUsers.updateUser(updateUser, url);
%>
<html>
<head>
    <jsp:include page="include_head.jsp"/>
    <title>Add user from</title>
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
                    <h1 class="h3 mb-0 text-gray-800">Updating user with Login: <%=updateUser.getUsername()%>
                    </h1>
                    <a href="${pageContext.request.contextPath}/users"
                       class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                            class="fas fa-chevron-left fa-sm text-white-50"></i> Back to users</a>
                </div>

                <!-- Content Row -->
                <div class="row pr-5">

                    <p>Result = <%=result%>

                    <p>User with login: <%=updateUser.getUsername()%> successfully created. You will be redirected to
                        Users
                        list in 3 seconds</p>
                    <script>
                        const timer = setTimeout(function () {
                            window.location = '${pageContext.request.contextPath}/users'
                        }, 3000);
                    </script>

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
