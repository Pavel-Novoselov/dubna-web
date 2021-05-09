<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:url value="/doUserLogin" var="loginUrl"/>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Geeter Login page">
    <meta name="author" content="Geetter and CO">

    <jsp:include page="include_head.jsp"/>
    <title>Geetter - Login</title>

</head>

<body class="bg-gradient-primary">
<sec:authorize access="isAuthenticated()">
    <% response.sendRedirect(request.getContextPath() + "/user_tweets"); %>
</sec:authorize>
    <div class="container">

        <!-- Outer Row -->
        <div class="row justify-content-center">

            <div class="col-xl-10 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                            <div class="col-lg-6">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
                                    </div>
                                    <sec:authorize access="isAuthenticated()">
                                        <sec:authentication property="name"/>
                                    </sec:authorize>
                                    <sec:authorize access="!isAuthenticated()">
                                        Login
                                    </sec:authorize>
                                    <sec:authorize access="isAuthenticated()">
                                        Logout
                                    </sec:authorize>
                                    <form class="user" action="${loginUrl}" method="post">
<%--                                        <sec:csrfInput />--%>
                                        <c:if test="${param.error != null}">
                                            <p>
                                                Invalid username and password.
                                            </p>
                                        </c:if>
                                        <c:if test="${param.logout != null}">
                                            <p>
                                                You have been logged out.
                                            </p>
                                        </c:if>
                                        <div class="form-group">
                                            <label for="username"></label>
                                            <input name="username" type="username" class="form-control form-control-user"
                                                   id="username" aria-describedby="emailHelp" placeholder="Enter Login..." autofocus="true">
                                        </div>
                                        <div class="form-group">
                                            <label for="password"></label>
                                            <input name="password" type="password" class="form-control form-control-user"
                                                                                             id="password" placeholder="Password">
                                        </div>
                                        <div class="form-group">
                                            <div class="custom-control custom-checkbox small">
                                                <input type="checkbox" class="custom-control-input" id="customCheck">
                                                <label class="custom-control-label" for="customCheck">Remember
                                                    Me</label>
                                            </div>
                                        </div>
                                        <button class="btn btn-primary btn-user btn-block" type="submit">Login</button>
                                    </form>

                                    <hr>
                                    <div class="text-center">
                                        <a class="small" href="${pageContext.request.contextPath}/forgot_password">Forgot Password?</a>
                                    </div>
                                    <div class="text-center">
                                        <a class="small" href="${pageContext.request.contextPath}/register">Create an Account!</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>

    <!-- Footer -->
    <jsp:include page="footer.jsp"/>
    <!-- End of Footer -->

</body>

</html>