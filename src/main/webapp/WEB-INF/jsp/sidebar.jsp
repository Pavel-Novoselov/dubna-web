<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="en" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/extras/spring-security">

<%
    final String navPath = request.getContextPath();
%>
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-left" href="..<%=navPath%>">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3"> Geet<sup>ter </sup></div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        Interface
    </div>

    <!-- Nav Item -->
    <li class="nav-item">
        <a class="nav-link" href="..<%=navPath%>/user_tweets">
            <i class="fab fa-twitter"></i>
            <span>Tweets</span>
        </a>
    </li>

    <!-- Nav Item -->
    <li class="nav-item">
        <a class="nav-link" href="..<%=navPath%>/user_subscriptions">
            <i class="fas fa-fw fa-users"></i>
            <span>Subscriptions</span>
        </a>
    </li>

    <!-- Nav Item -->
    <li class="nav-item">
        <a class="nav-link" href="..<%=navPath%>/profile">
            <i class="fas fa-fw fa-cog"></i>
            <span>Profile</span>
        </a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <div>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
<%--    <li class="nav-item active">--%>
<%--        <a class="nav-link" href="#">--%>
<%--            <i class="fas fa-fw fa-tachometer-alt"></i>--%>
<%--            <span sec:authorize="hasRole('ADMIN')">Admin Dashboard</span></a>--%>
<%--    </li>--%>

    <!-- Heading -->
    <div class="sidebar-heading">
        Admin tools
    </div>

    <!-- Nav Item - Pages Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link" href="..<%=navPath%>/users">
            <i class="fas fa-fw fa-users"></i>
            <span>Users</span>
        </a>
    </li>
    <%--            MENU TEMPLATE--%>
    <!-- Nav Item - Pages Collapse Menu -->
    <%--        <li class="nav-item">--%>
    <%--            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"--%>
    <%--               aria-expanded="true" aria-controls="collapsePages">--%>
    <%--                <i class="fas fa-fw fa-folder"></i>--%>
    <%--                <span>Users</span>--%>
    <%--            </a>--%>
    <%--            <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">--%>
    <%--                <div class="bg-white py-2 collapse-inner rounded">--%>
    <%--                    <h6 class="collapse-header">Login Screens:</h6>--%>
    <%--                    <a class="collapse-item" href="login.html">Login</a>--%>
    <%--                    <a class="collapse-item" href="register.html">Register</a>--%>
    <%--                    <a class="collapse-item" href="forgot-password.html">Forgot Password</a>--%>
    <%--                    <div class="collapse-divider"></div>--%>
    <%--                    <h6 class="collapse-header">Other Pages:</h6>--%>
    <%--                    <a class="collapse-item" href="404.html">404 Page</a>--%>
    <%--                    <a class="collapse-item" href="blank.html">Blank Page</a>--%>
    <%--                </div>--%>
    <%--            </div>--%>
    <%--        </li>--%>


    <!-- Nav Item -->
    <li class="nav-item">
        <a class="nav-link" href="..<%=navPath%>/tweets">
            <i class="fab fa-twitter"></i>
            <span>Tweets</span></a>
    </li>


    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">
    </sec:authorize>
    </div>

</ul>
