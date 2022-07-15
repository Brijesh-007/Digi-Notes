<!DOCTYPE html>
<html lang="en">
    <!--JSP page to for login-->
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Digi-Notes - Login</title>
        <link href="assets/css/material-dashboard.css?v=2.1.0" rel="stylesheet" />
        <script src="assets/js/core/jquery.min.js" type="text/javascript"></script>
        <script src="assets/js/core/popper.min.js" type="text/javascript"></script>
        <script src="assets/js/core/bootstrap-material-design.min.js" type="text/javascript"></script>
        <script src="assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>
        <!--Google Maps Plugin-->
        <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
        <!-- Chartist JS -->
        <script src="assets/js/plugins/chartist.min.js"></script>
        <!--  Notifications Plugin    -->
        <script src="assets/js/plugins/bootstrap-notify.js"></script>
        <!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
        <script src="assets/js/material-dashboard.js?v=2.1.0" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
    </head>
    <body class="dark-edition">
        <% if (session.getAttribute("username") != null) {
                response.sendRedirect("home.jsp");
            }
        %>
        <div class="wrapper ">

            <div class="sidebar" data-color="purple" data-background-color="black">
                <div class="logo">
                    <a href="" class="simple-text logo-normal">
                        Digi-Notes List
                    </a>
                </div>
                <div class="sidebar-wrapper">
                    <ul class="nav">
                        <li class="nav-item active">
                            <a class="nav-link" href="javascript:void(0)">
                                 <i class="material-icons">dashboard</i> 
                                 <p>Log in/ Sign up</p>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="main-panel">

                <!-- Navbar -->
                <nav class="navbar navbar-expand-lg navbar-transparent navbar-absolute fixed-top ">
                    <div class="container-fluid">
                        <div class="navbar-wrapper">
                            <a class="navbar-brand" href="javascript:void(0)">Login</a>
                        </div>
                       
                </nav>

                <div class="content">
                    <div class="container-fluid">
                        <form action="Login" class="simple-text logo-normal pull-center" method="post">
                            <div class="form-group">
                                <label for="exampleInput1" class="bmd-label-floating" >Email address</label>
                                <input type="email" class="form-control" id="exampleInput1" name="email" required>
                                <span class="bmd-help">We'll never share your email with anyone else.</span>
                            </div>
                            <br>
                            <div class="form-group">
                                <label for="exampleInput1" class="bmd-label-floating">Password</label>
                                <input type="password" class="form-control" id="exampleInput1" name="password" required>
                                <span class="bmd-help">Enter password</span>
                            </div>
                            <br>
                            <button type="submit" class="btn btn-primary pull-center">
                                <div class="nav-link">Login</div>
                            </button><br>
                            <button class="btn btn-info btn-sm pull-center" >
                                <a class="nav-link" href="signup.jsp">
                                    (Do not have an account?)SignUp
                                </a>
                            </button>
                        </form>
                    </div>
                    <% if (request.getAttribute("error") != null) {%>
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <strong>Oops!!</strong> ${error}
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <% } else {
                        }%>
                </div>
                <footer class="footer">
                    <div class="container-fluid">
                        <nav class="float-left">
                            <ul>
                                <li>

                                </li>
                            </ul>
                        </nav>
                    </div>
                    <!-- your footer here -->
            </div>
        </footer>
    </div>
</div>
</body>
</html>