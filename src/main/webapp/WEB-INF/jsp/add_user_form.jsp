<%@ page import="ru.geekbrains.comand.geetterbackend.helpers.JspUsers" %>
<%@ page import="ru.geekbrains.comand.geetterbackend.helpers.JspHelper" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.parser.ParseException" %>

<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
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
                    <h1 class="h3 mb-0 text-gray-800">Add User</h1>
                    <a href="${pageContext.request.contextPath}/users" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                            class="fas fa-chevron-left fa-sm text-white-50"></i> Back to users</a>

                </div>

                <!-- Content Row -->
                <div class="row pl-5">

                    <form action="${pageContext.request.contextPath}/new_user" method="post">
                        <div class="form-group">
                            <label for="username">Login</label>
                            <input class="form-control" name="username" type="text" id="username" placeholder="Enter Login..." required>
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input name="password" type="password" id="password" class="form-control" placeholder="Enter Password..." required>
                        </div>
                        <div class="form-group">
                            <label for="firstName">Name</label>
                            <input class="form-control" name="firstName" type="text" id="firstName" placeholder="Enter Firstname...">
                        </div>
                        <div class="form-group">
                            <label for="lastName">Last Name</label>
                            <input class="form-control" name="lastName" type="text" id="lastName"  placeholder="Enter Lastname...">
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" name="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Enter Email Address...">
                        </div>
                        <div class="form-group">
                            <label for="phone">Phone</label>
                            <input class="form-control" name="phone" type="tel" id="phone"
                                   onkeyup="this.value=this.value.replace(/[^\d]/,'')"  placeholder="Enter phone number...">
                        </div>
                        <div>
                            <button class="btn btn-success" type="submit">Add</button>
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


