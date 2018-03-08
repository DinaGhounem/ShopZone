<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>User Profile</title>
        <link rel="stylesheet" type="text/css" href="css/main_styles.css">
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <link rel="stylesheet" type="text/css" href="css/contact_styles.css">
        <link rel="stylesheet" type="text/css" href="css/EditProfile.css">
        <link rel="stylesheet" href="styles/kendo.common.min.css" />
        <link rel="stylesheet" href="styles/kendo.default.min.css" />
        <link rel="stylesheet" href="styles/kendo.default.mobile.min.css" />
        <link rel="stylesheet" type="text/css" href="css/bootstrap4/bootstrap.min.css">
        <link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
        <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
        <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/animate.css">
        <link rel="stylesheet" type="text/css" href="css/main_styles.css">
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <link rel="stylesheet" href="css/pagination_styles.css">
    </head><!--/head-->

    <body onload="loadData()">
        <div class="super_container">
            <!-- Header -->

            <header class="header trans_300">
                <div class="top_nav">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="top_nav_left">Welcome In Our Online Shopping website ShopZone :) </div>
                            </div>
                            <>
                            <div class="top_nav_left" style="padding-left:35em;">
                                <form method="post" action="LogOutServlet">
                                    <button class="btn btn-outline-danger my-2 my-sm-0" type="submit">Logout</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Main Navigation -->

                <div class="main_nav_container">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12 text-right">
                                <div class="logo_container">
                                    <a>Shop<span>Zone</span></a>
                                </div>
                                <nav class="navbar">
                                    <ul class="navbar_menu">
                                        <li><a href="home.jsp">Home</a></li>
                                        <li><a href="UserProfile.jsp">My Profile</a></li>
                                        <li><a href="cart.jsp">My Cart</a></li>
                                    </ul>
                                    <ul class="navbar_user">
                                        <li><a href="UserProfile.jsp"><i class="fa fa-user" aria-hidden="true"></i></a></li>
                                        <li class="checkout">
                                            <a href="cart.jsp">
                                                <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                                                <span id="checkout_items" class="checkout_items"></span>
                                            </a>
                                        </li>
                                    </ul>
                                    <div class="hamburger_container">
                                        <i class="fa fa-bars" aria-hidden="true"></i>
                                    </div>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>

            </header>
            <div class="fs_menu_overlay"></div>
            <div class="hamburger_menu">
                <div class="hamburger_close"><i class="fa fa-times" aria-hidden="true"></i></div>
                <div class="hamburger_menu_content text-right"></div>
            </div>

            <!-- profile section-->

            <div style="margin-top: 200px;">
                <section id="profile_section">
                    <div class="container">
                        <div class="table-responsive cart_info">
                            <center>
                                <form action="UpdateProfileServlet" method="post" class="dark-matter">
                                    <table>
                                        <tr>
                                            <td>
                                                <table>
                                                    <tr>
                                                        <td>First Name:</td>
                                                        <td><input id="input_firstname" class="form_input input_name input_ph" type="text" name="fname"  required="required" data-error="First Name is required."></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Last Name:</td>
                                                        <td><input id="input_lastname" class="form_input input_name input_ph" type="text" name="lname" required="required" data-error="Last Name is required."></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Email :</td>
                                                        <td><input id="input_email" class="form_input input_email input_ph" type="email" name="email" disabled="true"></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Password :</td>
                                                        <td><input id="input_password" class="form_input input_passwrd input_ph" type="password" name="password" required="required" data-error="Valid password is required."></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Address :</td>
                                                        <td><input type="text" id="input_address" class="form_input input_name input_ph" name="address" required data-error="Please, write Your Address."></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Birth Date :</td>
                                                        <td><input id="input_date" class="form_input input_date input_ph" type="text" name="bdate" disabled="true"></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Job :</td>
                                                        <td><input id="input_job" class="form_input input_job input_ph" type="text" name="job" required="required" data-error="Job is required."></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Credit Limit :</td>
                                                        <td><input input id="input_creditLimit" type="number" min="1" max="200000" class="form_input input_name input_ph" name="credit" required="required" data-error="Credit Limit is required."></td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                    <div>
                                        <input id="button_submit" type="submit" class="btn btn-outline-danger my-2 my-sm-0" value="Save"/>
                                    </div>
                                </form>
                            </center>
                        </div>
                    </div>
                </section>
            </div>
            <br><br>
            <!-- Footer -->
            <footer class="footer">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="footer_nav_container d-flex flex-sm-row flex-column align-items-center justify-content-lg-start justify-content-center text-center">
                                <ul class="footer_nav">
                                    <li><a href="https://github.com/MuhammedMahrous/ShopZone">Blog</a></li>
                                    <li><a href="https://github.com/MuhammedMahrous/ShopZone">FAQs</a></li>
                                    <li><a href="https://github.com/MuhammedMahrous/ShopZone">Contact us</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="footer_social d-flex flex-row align-items-center justify-content-lg-end justify-content-center">
                                <ul>
                                    <li><a href="https://github.com/MuhammedMahrous/ShopZone"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
                                    <li><a href="https://github.com/MuhammedMahrous/ShopZone"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
                                    <li><a href="https://github.com/MuhammedMahrous/ShopZone"><i class="fa fa-instagram" aria-hidden="true"></i></a></li>
                                    <li><a href="https://github.com/MuhammedMahrous/ShopZone"><i class="fa fa-skype" aria-hidden="true"></i></a></li>
                                    <li><a href="https://github.com/MuhammedMahrous/ShopZone"><i class="fa fa-pinterest" aria-hidden="true"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="footer_nav_container">
                                <div class="cr">�2018 All Rights Reserverd. This template is made with <i class="fa fa-heart-o"
                                                                                                          aria-hidden="true"></i>
                                    by <a>ShopZone</a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </footer>

        </div>


        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/EditProfile.js"></script>
        <script src="css/bootstrap4/popper.js"></script>
        <script src="css/bootstrap4/bootstrap.min.js"></script>
        <script src="plugins/Isotope/isotope.pkgd.min.js"></script>
        <script src="plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
        <script src="plugins/easing/easing.js"></script>

    </body>
</html>
