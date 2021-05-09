<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.parser.ParseException" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="ru.geekbrains.comand.geetterbackend.helpers.JspHelper" %>
<%@ page import="java.util.Objects" %>
<%@ page import="ru.geekbrains.comand.geetterbackend.helpers.JspTwits" %>
<%@ page import="ru.geekbrains.comand.geetterbackend.helpers.JspUsers" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.OffsetDateTime" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="ru.geekbrains.comand.geetterbackend.services.UserAuthService" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%
    final String username = org.springframework.security.core.context.SecurityContextHolder.getContext()
            .getAuthentication().getName();

    JSONObject user = null;
    try {
        user = JspUsers.getUserByUsername(username, JspHelper.generateAbsURL(request));
    } catch (final ParseException e) {
        e.printStackTrace();
    }

    final String userId = (user.get("id") == null) ? "0" : user.get("id").toString();
    final String userFirstName = (user.get("firstName") == null) ? "" : user.get("firstName").toString();
    final String userLastName = (user.get("lastName") == null) ? "" : user.get("lastName").toString();
    final JSONArray subscriptionUsers = (JSONArray) Objects.requireNonNull(user).get("subscriptions");

    JSONArray jsonArrayTweets = new JSONArray();
//    try {
//        jsonArrayTweets = JspTwits.getAllTwits(JspHelper.generateAbsURL(request));
//    } catch (final ParseException e) {
//        e.printStackTrace();
//    }
    try {
        jsonArrayTweets = JspTwits.getTwitsOwnAndFromSubscription(userId, JspHelper.generateAbsURL(request));
    } catch (final ParseException e) {
        e.printStackTrace();
    }
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    final int dateLength = 19;
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
                    <%--                    <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i--%>
                    <%--                            class="fas fa-user-plus fa-sm text-white-50"></i> New tweet</a>--%>
                </div>

                <!-- Content Row -->
                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card border-left-primary shadow">
                        <!-- Content Row -->

                        <div class="card-body">
                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                Hello <%=userFirstName%>,<br>
                                Make Tweet by using form below
                            </div>
                            <form action="${pageContext.request.contextPath}/create_user_tweet" method="post">
                                <input type="hidden" name="userId" value="<%=userId%>">
                                <div class="form-group">
                                    <label for="text"></label>
                                    <input class="form-control form-control-user" name="text" type="text" id="text"
                                           placeholder="Your tweet type here..." required>
                                </div>
                                <div class="form-check form-switch mb-3">
                                    <input class="form-check-input form-control-user" type="checkbox"
                                           name="is_published" id="is_published" value="is_published" CHECKED>
                                    <label class="form-check-label" for="is_published">Public tweet</label>
                                </div>
                                <div>
                                    <button class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"
                                            type="submit"><i class="fab fa-twitter"></i> Tweet
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>

                    <div class="card-header py-3">
                        <h6 class="mb-3 font-weight-bold text-primary">All tweets list</h6>
                        <%

                            if (jsonArrayTweets == null) {
                                out.println("Error loading users!!!");
                            }
                            for (final Object o : Objects.requireNonNull(jsonArrayTweets)) {
                                final JSONObject tweet = (JSONObject) o;
                                final JSONObject tweetOwnerUser = (JSONObject) (tweet.get("user"));
                                final String tweetOwnerUserFirstName = Objects.requireNonNull(tweetOwnerUser).get("firstName").toString();
                                final String tweetOwnerUserName = Objects.requireNonNull(tweetOwnerUser).get("username").toString();
                                final String tweetOwnerId = Objects.requireNonNull(tweetOwnerUser).get("id").toString();

                                final JSONArray comments = (JSONArray) tweet.get("comments");
                                final String commentsList = comments.toString().replaceAll("},", "<br>").replaceAll("[\\[\\]{}]", "");
                                final LocalDate createdFormatted = LocalDateTime.parse(tweet.get("created").toString().substring(0, dateLength), formatter).toLocalDate();
                        %>
                        <!-- Dropdown Card -->
                        <div class="card shadow mb-4">
                            <!-- Card Header - Dropdown -->
                            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                <h6 class="m-0 font-weight-bold text-primary">
                                    <% out.println(tweetOwnerUserFirstName + " (" + createdFormatted + ")"); %>
                                </h6>
                                <div class="dropdown no-arrow">
                                    <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                         aria-labelledby="dropdownMenuLink">
                                        <div class="dropdown-header">Actions:</div>
                                        <a class="dropdown-item" href="#">Like</a>
                                        <a class="dropdown-item" href="#">Follow author</a>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="#">Comment</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Card Body -->
                            <div class="card-body">
                                <div>
                                <%=tweet.get("text") +
                                        "</div>" +
                                        "<div>" +
                                        "<b>Likes</b>: " + tweet.get("likes")+
                                        "</div>"
                                        %>
                                    <div>
                                <%
                                    if (!subscriptionUsers.toJSONString().contains(tweetOwnerUserName) && !tweetOwnerUserName.equals(username.replace("\"",""))) {
                                        out.println("<form action=\".." + request.getContextPath() + "/subscribe\" method=\"POST\" style='display: inline;'> " +
                                                "<input type=\"hidden\" name=\"id\" value=\"" + userId + "\">" +
                                                "<input type=\"hidden\" name=\"target\" value=\"" + tweetOwnerId + "\">" +
                                                "<button class=\"d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm\" type=\"submit\"><i class=\"fas fa-user-plus\"> Follow</i></button>" +
                                                "</form>");
                                    }
                                %>

                                <form action="${pageContext.request.contextPath}/do_like" method="get" style="display:inline;">
                                    <input type="hidden" name="userId" value="<%=userId%>">
                                    <input type="hidden" name="tweetId" value="<%=tweet.get("id")%>">
                                    <button class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm" type="submit"><i class="fas fa-thumbs-up"> Like</i></button>
                                </form>
                                </div>
                                <!-- Collapsable Card -->
                                <div class="card shadow  mt-3">
                                    <!-- Card Header - Accordion -->
                                    <a href="#<%out.print("collapseCard"+tweet.get("id"));%>"
                                       class="d-block card-header py-2 collapsed"
                                       data-toggle="collapse"
                                       role="button" aria-expanded="true"
                                       aria-controls="<%out.print("collapseCard"+tweet.get("id"));%>">
                                        <h6 class="m-0 font-weight-bold text-primary">Comments</h6>
                                    </a>
                                    <!-- Card Content - Collapse -->
                                    <div class="collapse" id="<%out.print("collapseCard"+tweet.get("id"));%>">
                                        <div class="card-body">
                                            <%
                                                for (final Object c : Objects.requireNonNull(comments)) {
                                                    final JSONObject comment = (JSONObject) c;
                                                    final JSONObject userCommenter = (JSONObject) comment.get("user");
                                                    final LocalDate commentedTimeFormatted = LocalDateTime.parse(comment.get("created").toString().substring(0, dateLength), formatter).toLocalDate();

                                                    out.println("<div><strong>" + userCommenter.get("username") + " (" + commentedTimeFormatted + ")</strong></div>");
                                                    out.println("<div>" + comment.get("text") + "</div>");
                                                }
                                            %>
                                            <form action="${pageContext.request.contextPath}/create_comment_by_tweet" method="post">
                                                <input type="hidden" name="userId" value="<%=userId%>">
                                                <input type="hidden" name="tweetId" value="<%=tweet.get("id")%>">
                                                <div class="form-group">
                                                    <label for="text_comment">Add new comment</label>
                                                    <input class="form-control" name="text_comment" type="text" id="text_comment"
                                                           value="" required>
                                                </div>
                                                <div>
                                                    <button class="btn btn-success" type="submit">Create comment</button>
                                                </div>
                                            </form>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%}%>
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
</div>
</body>
<jsp:include page="#"/>
</html>
