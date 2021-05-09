<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Geetter register page">
    <meta name="author" content="Geetter and CO">

    <jsp:include page="include_head.jsp"/>
    <title>Geeter - Register</title>

</head>

<body class="bg-gradient-primary">

    <div class="container">

        <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                    <div class="col-lg-7">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                            </div>
                            <form class="user" action="${pageContext.request.contextPath}/register_user">
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <label for="firstName"></label>
                                        <input type="text" name="firstName" class="form-control form-control-user" id="firstName"
                                                                                     placeholder="First Name">
                                    </div>
                                    <div class="col-sm-6">
                                        <label for="lastName"></label>
                                        <input type="text" name="lastName" class="form-control form-control-user" id="lastName"
                                                                                    placeholder="Last Name">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="email"></label>
                                    <input type="email" name="email" class="form-control form-control-user" id="email"
                                                                                  placeholder="Email Address">
                                </div>
                                <div class="form-group">
                                    <label for="username"></label>
                                    <input type="login" name="username" class="form-control form-control-user" id="username"
                                                                                  placeholder="Login" required>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <label for="password"></label>
                                        <input type="password" name="password" class="form-control form-control-user"
                                                                                         id="password" placeholder="Password" required>
                                    </div>
                                    <div class="col-sm-6">
                                        <label for="repeatPassword"></label>
                                        <input type="password" name="repeatPassword" class="form-control form-control-user"
                                                                                          id="repeatPassword" placeholder="Repeat Password">
                                    </div>
                                </div>
                                <a href="javascript:;" onclick="parentNode.submit();" class="btn btn-primary btn-user btn-block">
                                    Register Account
                                </a>
                            </form>
                            <hr>
                            <div class="text-center">
                                <a class="small" href="${pageContext.request.contextPath}/forgot_password">Forgot Password?</a>
                            </div>
                            <div class="text-center">
                                <a class="small" href="${pageContext.request.contextPath}/login">Already have an account? Login!</a>
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