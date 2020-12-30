<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>应学后台管理系统</title>
    <link rel="icon" href="${path}/bootstrap/img/arrow-up.png" type="image/x-icon">
    <link rel="stylesheet" href="${path}/bootstrap/css/bootstrap.css">

    <%--引入jqgrid中主题css--%>
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/css/css/hot-sneaks/jquery-ui-1.8.16.custom.css">
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/boot/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入js文件--%>
    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <script src="${path}/bootstrap/js/bootstrap.js"></script>
    <script src="${path}/bootstrap/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${path}/bootstrap/jqgrid/boot/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${path}/bootstrap/js/ajaxfileupload.js"></script>

    <style type="text/css">


        p {
            text-align: center;

        }

        button {
            text-align: center;
            background-color: orange;
        }
    </style>
</head>
<body>

<!--顶部导航-->
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="nav-header">
            <a class="navbar-brand" href="#">应学视频App后台管理系统</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><span class="glyphicon glyphicon-user"></span> 欢迎：${sessionScope.username} </a></li>
            <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> 退出</a></li>
        </ul>
    </div>
</nav>

<!--栅格系统-->
<div class="container-fluid">
    <div class="row">
        <div class="col-md-2">
            <div class="panel-group" id="acc">
                <div class="panel panel-danger">
                    <div class="panel-heading">
                        <a href="#tg1" class="panel-title" data-toggle="collapse" data-parent="#acc"><p>用户管理</p></a>
                    </div>
                    <div class="panel-collapse collapse" id="tg1">
                        <div class="panel-body">
                            <a role="menuitem" tabindex="-1" class="text-center" href="#">
                                <div class="panel-body col-sm-offset-2">
                                    <button class="btn btn-info">
                                        <a href="javascript:$('#mainId').load('${path}/user/showUser.jsp')">用户信息</a>
                                    </button><br><br>
                                    <button class="btn btn-info">
                                        <a href="javascript:$('#mainId').load('${path}/test/echarts.jsp')">用户统计</a>
                                    </button><br><br>
                                    <button class="btn btn-info">用户分布</button>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
                <HR>
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <a href="#tg2" class="panel-title" data-toggle="collapse" data-parent="#acc"><p>分类管理</p></a>
                    </div>
                    <div class="panel-collapse collapse" id="tg2">
                        <div class="panel-body">
                            <a role="menuitem" tabindex="-1" class="text-center" href="#">
                                <div class="panel-body col-sm-offset-2">
                                    <button class="btn btn-success">
                                        <a href="javascript:$('#mainId').load('${path}/category/showCategory.jsp')">类别信息</a>
                                    </button>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
                <HR>
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <a href="#tg3" class="panel-title" data-toggle="collapse" data-parent="#acc"><p>视频管理</p></a>
                    </div>
                    <div class="panel-collapse collapse" id="tg3">
                        <div class="panel-body">
                            <a role="menuitem" tabindex="-1" class="text-center" href="#">
                                <div class="panel-body">
                                    <button class="btn btn-warning col-sm-offset-3">
                                        <a href="javascript:$('#mainId').load('${path}/video/video.jsp')">视频信息</a>
                                        </button>

                                </div>
                            </a>
                        </div>
                    </div>
                </div>
                <HR>
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <a href="#tg4" class="panel-title" data-toggle="collapse" data-parent="#acc"><p>日志管理</p></a>
                    </div>
                    <div class="panel-collapse collapse" id="tg4">
                        <div class="panel-body">
                            <a role="menuitem" tabindex="-1" class="text-center" href="#">
                                <div class="btn-group col-sm-offset-4">
                                    <button type="button" class="btn btn -default">java</button>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
                <HR>
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <a href="#tg5" class="panel-title" data-toggle="collapse" data-parent="#acc"><p>管理员信息</p></a>
                    </div>
                    <div class="panel-collapse collapse" id="tg5">
                        <div class="panel-body">
                            <a role="menuitem" tabindex="-1" class="text-center" href="#">

                                <div class="panel-body col-sm-offset-2">
                                    <button class="btn btn-danger">
                                        <a href="javascript:$('#mainId').load('${path}/user/adminUser.jsp')">管理员信息</a>
                                    </button>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>

            </div>

            <%--            <div class="col-sm-offset-1"><hr color="blue" size=1px></div>--%>


        </div>
        <div class="col-md-10" id="mainId">


            <div class="well well-lg">
                <h1><p>欢迎来到应学视频App后台管理系统</p></h1>
            </div>


            <div id="myCarousel" class="carousel slide">
                <!-- 轮播（Carousel）指标 -->
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                    <li data-target="#myCarousel" data-slide-to="3"></li>
                </ol>
                <!-- 轮播（Carousel）项目 -->
                <div class="carousel-inner">
                    <div class="item active">
                        <p><img src="${pageContext.request.contextPath}/bootstrap/img/pic1.jpg" alt="First slide"></p>
                    </div>
                    <div class="item">
                        <p><img src="${pageContext.request.contextPath}/bootstrap/img/pic2.jpg" alt="Second slide"></p>
                    </div>
                    <div class="item">
                        <p><img src="${pageContext.request.contextPath}/bootstrap/img/pic3.jpg" alt="Third slide"></p>
                    </div>
                    <div class="item">
                        <p><img src="${pageContext.request.contextPath}/bootstrap/img/pic4.jpg" alt="Third slide"></p>
                    </div>
                </div>
                <!-- 轮播（Carousel）导航 -->
                <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
    </div>
</div>
</div>
<nav class="navbar navbar-inverse navbar-fixed-bottom" role="navigation" style="min-height: 25px">
    <div class="container-fluid">
        <div class="nav-header text-center">
            <span style="color: white">baizhi@.com</span>
        </div>
    </div>
</nav>



<!--左边手风琴部分-->
<!--  右边部分  -->
<!--巨幕开始-->
<!--右边轮播图部分-->
<!--页脚-->
<!--栅格系统-->
</body>
</html>

