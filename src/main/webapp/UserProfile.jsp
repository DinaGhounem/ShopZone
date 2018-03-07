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
        <link href="cartincludes/css/bootstrap.min.css" rel="stylesheet">
        <link href="cartincludes/css/font-awesome.min.css" rel="stylesheet">
        <link href="cartincludes/css/prettyPhoto.css" rel="stylesheet">
        <link href="cartincludes/css/price-range.css" rel="stylesheet">
        <link href="cartincludes/css/animate.css" rel="stylesheet">
        <link href="cartincludes/css/main.css" rel="stylesheet">
        <link href="cartincludes/css/responsive.css" rel="stylesheet">
        <link rel="shortcut icon" href="cartincludes/images/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144"
              href="cartincludes/images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114"
              href="cartincludes/images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72"
              href="cartincludes/images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="cartincludes/images/ico/apple-touch-icon-57-precomposed.png">
        <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
        <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
        <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/animate.css">
        <link rel="stylesheet" type="text/css" href="css/main_styles.css">
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <link rel="stylesheet" type="text/css" href="css/contact_styles.css">
    </head><!--/head-->

    <body onload="loadData()">
        <div class="super_container">
            <!-- Header -->

            <header class="header trans_300">

                <!-- Top Navigation -->

                <div class="top_nav">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="top_nav_left">free shipping on all u.s orders over $50</div>
                            </div>
                            <div class="col-md-6 text-right">
                                <div class="top_nav_right">
                                    <ul class="top_nav_menu">

                                        <!-- Currency / Language / My Account -->

                                        <li class="currency">
                                            <a href="#">
                                                usd
                                                <i class="fa fa-angle-down"></i>
                                            </a>
                                            <ul class="currency_selection">
                                                <li><a href="#">cad</a></li>
                                                <li><a href="#">aud</a></li>
                                                <li><a href="#">eur</a></li>
                                                <li><a href="#">gbp</a></li>
                                            </ul>
                                        </li>
                                        <li class="language">
                                            <a href="#">
                                                English
                                                <i class="fa fa-angle-down"></i>
                                            </a>
                                            <ul class="language_selection">
                                                <li><a href="#">French</a></li>
                                                <li><a href="#">Italian</a></li>
                                                <li><a href="#">German</a></li>
                                                <li><a href="#">Spanish</a></li>
                                            </ul>
                                        </li>
                                        <li class="account">
                                            <a href="#">
                                                My Account
                                                <i class="fa fa-angle-down"></i>
                                            </a>
                                            <ul class="account_selection">
                                                <li><a href="#"><i class="fa fa-sign-in" aria-hidden="true"></i>Sign In</a></li>
                                                <li><a href="#"><i class="fa fa-user-plus" aria-hidden="true"></i>Register</a>
                                                </li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
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
                                    <a href="#">colo<span>shop</span></a>
                                </div>
                                <nav class="navbar">
                                    <ul class="navbar_menu">
                                        <li><a href="#">home</a></li>
                                        <li><a href="#">shop</a></li>
                                        <li><a href="#">promotion</a></li>
                                        <li><a href="#">pages</a></li>
                                        <li><a href="#">blog</a></li>
                                        <li><a href="contact.html">contact</a></li>
                                    </ul>
                                    <ul class="navbar_user">
                                        <li><a href="#"><i class="fa fa-search" aria-hidden="true"></i></a></li>
                                        <li><a href="#"><i class="fa fa-user" aria-hidden="true"></i></a></li>
                                        <li class="checkout">
                                            <a href="#">
                                                <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                                                <span id="checkout_items" class="checkout_items">2</span>
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
                <div class="hamburger_menu_content text-right">
                    <ul class="menu_top_nav">
                        <li class="menu_item has-children">
                            <a href="#">
                                usd
                                <i class="fa fa-angle-down"></i>
                            </a>
                            <ul class="menu_selection">
                                <li><a href="#">cad</a></li>
                                <li><a href="#">aud</a></li>
                                <li><a href="#">eur</a></li>
                                <li><a href="#">gbp</a></li>
                            </ul>
                        </li>
                        <li class="menu_item has-children">
                            <a href="#">
                                English
                                <i class="fa fa-angle-down"></i>
                            </a>
                            <ul class="menu_selection">
                                <li><a href="#">French</a></li>
                                <li><a href="#">Italian</a></li>
                                <li><a href="#">German</a></li>
                                <li><a href="#">Spanish</a></li>
                            </ul>
                        </li>
                        <li class="menu_item has-children">
                            <a href="#">
                                My Account
                                <i class="fa fa-angle-down"></i>
                            </a>
                            <ul class="menu_selection">
                                <li><a href="#"><i class="fa fa-sign-in" aria-hidden="true"></i>Sign In</a></li>
                                <li><a href="#"><i class="fa fa-user-plus" aria-hidden="true"></i>Register</a></li>
                            </ul>
                        </li>
                        <li class="menu_item"><a href="#">home</a></li>
                        <li class="menu_item"><a href="#">shop</a></li>
                        <li class="menu_item"><a href="#">promotion</a></li>
                        <li class="menu_item"><a href="#">pages</a></li>
                        <li class="menu_item"><a href="#">blog</a></li>
                        <li class="menu_item"><a href="#">contact</a></li>
                    </ul>
                </div>
            </div>

            <!-- profile section-->

            <div style="margin-top: 200px;">
                <section id="profile_section">
                    <div class="container">
                        <div class="table-responsive cart_info">
                            <center>
                                <form action="post">
                                    <div>
                                        <div class="login100-pic js-tilt" data-tilt>
                                            <img src="signincludes/images/img-01.png" alt="IMG">
                                        </div>
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
                                        <div>
                                            <button id="button_submit" type="submit" class="red_button message_submit_btn trans_300" value="Submit">Save</button>
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
                                    <li><a href="#">Blog</a></li>
                                    <li><a href="#">FAQs</a></li>
                                    <li><a href="contact.html">Contact us</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="footer_social d-flex flex-row align-items-center justify-content-lg-end justify-content-center">
                                <ul>
                                    <li><a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
                                    <li><a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
                                    <li><a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a></li>
                                    <li><a href="#"><i class="fa fa-skype" aria-hidden="true"></i></a></li>
                                    <li><a href="#"><i class="fa fa-pinterest" aria-hidden="true"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="footer_nav_container">
                                <div class="cr">�2018 All Rights Reserverd. This template is made with <i class="fa fa-heart-o"
                                                                                                          aria-hidden="true"></i>
                                    by <a href="#">Colorlib</a></div>
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
