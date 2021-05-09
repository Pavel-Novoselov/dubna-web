<%@ page import="ru.geekbrains.comand.geetterbackend.helpers.JspUsers" %>
<%@ page import="ru.geekbrains.comand.geetterbackend.helpers.JspHelper" %>
<%@ page import="ru.geekbrains.comand.geetterbackend.helpers.JspTwits" %>

<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    final String tweetId = request.getParameter("id");
    final String deletingResult = JspTwits.deleteTweet(tweetId, JspHelper.generateAbsURL(request));

%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="include_head.jsp"/>
    <title></title>
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
                    <h1 class="h3 mb-0 text-gray-800">Deleting twits with id <%=tweetId%>
                    </h1>
                    <a href="${pageContext.request.contextPath}/tweets" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                            class="fas fa-chevron-left fa-sm text-white-50"></i> Back to twits</a>
                </div>

                <!-- Content Row -->
                <div class="row">

                    <%
                        if (deletingResult.isEmpty()) {
                            response.sendRedirect(request.getContextPath() + "/tweets");
                        }
                    %>
                    <h2 class="h3 mb-0 text-danger">
                        <%=deletingResult%>
                    </h2>


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
