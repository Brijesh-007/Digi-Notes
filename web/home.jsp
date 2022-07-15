<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html lang="en">
    <!--home JSP page to show and  todo-->
    <head>
        <meta charset="utf-8" />
        <link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
        <link rel="icon" type="image/png" href="assets/img/favicon.png">
        <title>
            Digi-Notes List Home
        </title>
        <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport'/>
        <!--     Fonts and icons     -->
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
        <!-- CSS Files -->
        <link href="assets/css/material-dashboard.css?v=2.1.0" rel="stylesheet"/>
        <!-- CSS Just for demo purpose, don't include it in your project -->
        <link href="assets/demo/demo.css" rel="stylesheet" />

    </head>

    <body class="dark-edition">
        <sql:setDataSource url="jdbc:mysql://localhost:3306/todolist"
                           driver="com.mysql.cj.jdbc.Driver"
                           user="root"
                           password="" />
        <sql:query sql="SELECT * FROM todo WHERE uid=${sessionScope.uid} and status=0" var="r">

            
        </sql:query>

        <div class="wrapper ">
            <div class="sidebar" data-color="purple" data-background-color="black" data-image="assets/img/sidebar-2.jpg">
                <div class="logo"><a href="" class="simple-text logo-normal">
                        Digi-Notes List
                    </a></div>
                <div class="sidebar-wrapper">
                    <ul class="nav">
                        <li class="nav-item active">
                            <a class="nav-link" href="./home.jsp">
                                <i class="material-icons">content_paste</i>
                                <p>Digi-Notes List</p>
                            </a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" href="./done.jsp">
                                <i class="material-icons">dashboard</i>
                                <p>Completed Tasks</p>
                            </a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" href="./user.jsp">
                                <i class="material-icons">person</i>
                                <p>User Profile</p>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="main-panel">

                <!-- Navbar -->
                <nav class="navbar navbar-expand-lg navbar-transparent navbar-absolute fixed-top " id="navigation-example">
                    <div class="container-fluid">
                        <div class="navbar-wrapper">
                            <a class="navbar-brand" href="javascript:void(0)">Digi-Notes List &nbsp;&nbsp;  Hello, <%= session.getAttribute("username")%></a>
                            <p class="text-muted"></p><br>
                        </div>


                        <button class="navbar-toggler" type="button" data-toggle="collapse" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation" data-target="#navigation-example">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="navbar-toggler-icon icon-bar"></span>
                            <span class="navbar-toggler-icon icon-bar"></span>
                            <span class="navbar-toggler-icon icon-bar"></span>
                        </button>


                        <div class="collapse navbar-collapse justify-content-end">
                            <ul class="navbar-nav">
                                <li>
                                    <form action="Logout" method="post">
                                        <button class="btn btn-danger" type="submit">Log Out</button>
                                    </form>
                                </li> 
                            </ul>
                        </div>
                    </div>
                </nav>
                <!-- End Navbar -->
                <div class="content">
                    <% if (request.getAttribute("message") != null) {%>
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        <strong><%= request.getAttribute("message")%></strong>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <% }%>

                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                        Add Digi-Notes
                    </button>
                    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Add Digi-Notes</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form action="AddList" class="logo-normal" method="post">
                                        <div class="form-group">
                                            <label for="exampleInput1" class="bmd-label-floating">Title</label>
                                            <input type="text" class="form-control text-dark" id="exampleInput1" name="title">
                                            <span class="bmd-help">Add title here</span>
                                        </div><hr>

                                        <div class="form-group">
                                            <label for="exampleInput1" class="bmd-label-floating" >Description</label>
                                            <input type="text" class="form-control text-dark" id="exampleInput1" name="description">
                                            <span class="bmd-help">Enter Details.</span>
                                        </div>
                                        <hr>
                                        <div class="form-group">
                                            <label for="exampleInput1" class="bmd-label-floating" >Type</label>
                                            <select class="form-control text-dark" id="exampleInput1" name="type">
                                                <option>General</option>
                                                <option>Office</option>
                                            </select>
                                        </div><hr>
                                        <div class="form-group">
                                            <label for="exampleInput1" class="bmd-label-floating" >Deadline</label><br>
                                            <input type="date" class="form-control text-dark" id="exampleInput1" name="deadline">
                                            <span class="bmd-help">Enter Deadline</span>
                                        </div>
                                        <br>

                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                            <button type="submit" class="btn btn-primary">Add</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <c:forEach items="${r.rows}" var="r">
                            <div class="card card-nav-tabs mb-5">
                                <h4 class="card-header card-header-info"><b><c:out value="${r.title}" /></b></h4>
                                <div class="card-body">
                                    <h4 class="card-title"><b><c:out value="${r.details}" /></b></h4>
                                    <p class="card-text">Type: <c:out value="${r.type}" /></p>
                                    <p class="card-text">Deadline: <c:out value="${r.deadline}" /></p>
                                    <form action="DoneList" method="POST">
                                        <input type="hidden" name="nid" value="${r.nid}" />
                                        <button type="submit" class="btn btn-success">Mark Done</button>
                                    </form>
                                    <form action="RemoveList" method="POST">
                                        <input type="hidden" name="nid" value="${r.nid}" />
                                        <button type="submit" class="btn btn-danger">Delete</button>
                                    </form>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <footer class="footer float-right">
                    <div class="copyright float-right" id="date">

                    </div>
            </div>
        </footer>
        <script>
            const x = new Date().getFullYear();
            let date = document.getElementById('date');
            date.innerHTML = '&copy; ' + x + date.innerHTML;
        </script>
    </div>
</div>

<!--   Core JS Files   -->
<script src="assets/js/core/jquery.min.js"></script>
<script src="assets/js/core/popper.min.js"></script>
<script src="assets/js/core/bootstrap-material-design.min.js"></script>
<script src="https://unpkg.com/default-passive-events"></script>
<script src="assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>
<!-- Place this tag in your head or just before your close body tag. -->
<script async defer src="https://buttons.github.io/buttons.js"></script>

<!-- Chartist JS -->
<script src="assets/js/plugins/chartist.min.js"></script>
<!--  Notifications Plugin    -->
<script src="assets/js/plugins/bootstrap-notify.js"></script>
<!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
<script src="assets/js/material-dashboard.js?v=2.1.0"></script>
<!-- Material Dashboard DEMO methods, don't include it in your project! -->
<script src="assets/demo/demo.js"></script>
<script>
            $(document).ready(function () {
                $().ready(function () {
                    $sidebar = $('.sidebar');

                    $sidebar_img_container = $sidebar.find('.sidebar-background');

                    $full_page = $('.full-page');

                    $sidebar_responsive = $('body > .navbar-collapse');

                    window_width = $(window).width();




                    $('.switch-sidebar-image input').change(function () {
                        $full_page_background = $('.full-page-background');

                        $input = $(this);

                        if ($input.is(':checked')) {
                            if ($sidebar_img_container.length != 0) {
                                $sidebar_img_container.fadeIn('fast');
                                $sidebar.attr('data-image', '#');
                            }

                            if ($full_page_background.length != 0) {
                                $full_page_background.fadeIn('fast');
                                $full_page.attr('data-image', '#');
                            }

                            background_image = true;
                        } else {
                            if ($sidebar_img_container.length != 0) {
                                $sidebar.removeAttr('data-image');
                                $sidebar_img_container.fadeOut('fast');
                            }

                            if ($full_page_background.length != 0) {
                                $full_page.removeAttr('data-image', '#');
                                $full_page_background.fadeOut('fast');
                            }

                            background_image = false;
                        }
                    });

                    $('.switch-sidebar-mini input').change(function () {
                        $body = $('body');

                        $input = $(this);

                        if (md.misc.sidebar_mini_active == true) {
                            $('body').removeClass('sidebar-mini');
                            md.misc.sidebar_mini_active = false;

                            $('.sidebar .sidebar-wrapper, .main-panel').perfectScrollbar();

                        } else {

                            $('.sidebar .sidebar-wrapper, .main-panel').perfectScrollbar('destroy');

                            setTimeout(function () {
                                $('body').addClass('sidebar-mini');

                                md.misc.sidebar_mini_active = true;
                            }, 300);
                        }

                        // we simulate the window Resize so the charts will get updated in realtime.
                        var simulateWindowResize = setInterval(function () {
                            window.dispatchEvent(new Event('resize'));
                        }, 180);

                        // we stop the simulation of Window Resize after the animations are completed
                        setTimeout(function () {
                            clearInterval(simulateWindowResize);
                        }, 1000);

                    });
                });
            });
</script>
<script>
    $(document).ready(function () {
        // Javascript method's body can be found in assets/js/demos.js
        md.initDashboardPageCharts();
    });
</script>
</body>

</html>