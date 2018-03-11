<%-- 
    Document   : showUser
    Created on : Mar 5, 2018, 1:41:45 AM
    Author     : Hanaa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jtech.shopzone.view.controller.ShowUserToAdmin" %>
<%@page import="jtech.shopzone.view.controller.UserHistory" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Shop Zone</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="description" content="Colo Shop Template">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="css/bootstrap4/bootstrap.min.css">
        <link rel="stylesheet" href="styles/kendo.common.min.css"/>
        <link rel="stylesheet" href="styles/kendo.default.min.css"/>
        <link rel="stylesheet" href="styles/kendo.default.mobile.min.css"/>
        <link rel="stylesheet" type="text/css" href="css/bootstrap4/bootstrap.min.css">
        <link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
        <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
        <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/animate.css">
        <link rel="stylesheet" type="text/css" href="css/main_styles.css">
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <link rel="stylesheet" href="css/pagination_styles.css">

        <link rel="stylesheet" type="text/css" href="cartincludes/css/main.css">




        <style>

            #user_profile{
                position: relative;
                float: right;
                top:-200px;
                background: #fff;  



            }

            .product-item {
                width: 50%;
                margin: 0% 10%;
                float: right;

                background: #fff;

            }
            #user_profile_section{
                min-height: 500px;
                max-height: 500px;
                overflow-y: auto;

            }
            .product_image img{
                border-radius: 50%;
                border: solid 1px white;
                width: 40%;
                margin: 5% 30%;

            }
            #ex1{
                margin: 17% 30%;
                top: 0px;
                overflow-y: auto;
                height: 300px;
            }
            .modal a.close-modal{

                top:-5px;
                right:-3px;
            }
            #data2{
                position: absolute;
                top: 10%;
                left: 50%;
                transform: translateX(-50%) translateY(-50%);

            }
            ::-webkit-scrollbar
            {
                width: 5px;  /* for vertical scrollbars */
                height: 12px; /* for horizontal scrollbars */
                border-radius: 5%;
            }

            ::-webkit-scrollbar-track
            {
                background: rgba(0, 0, 0, 0.1);
            }

            ::-webkit-scrollbar-thumb
            {
                background: rgba(0, 0, 0, 0.5);
            }
            .a:hover, .a:active {

                text-decoration: none;
                text-align: none;
                color: #333;
            }

            th,.headerColor{
                color: #23527c;
                font: bold;
            }
            thead{

            }
            #table_content{
                width:50%;
                margin-left:-5%;
                max-height: 500px;
                overflow-y: auto;
            }

        </style>
    </head><!--/head-->

    <body>
        <div class="super_container">
            <!-- Header -->
            <header class="header trans_300">
                <div class="top_nav">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="top_nav_left">Welcome Admin:) </div>
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
                                        <li><a href="adminHome.jsp">Home</a></li>
                                        <li><a href="Category.jsp">Categories</a></li>
                                        <li><a href="addProduct.jsp">Add Products</a></li>
                                        <li><a href="showUser.jsp">Clients</a></li>
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
            <!--/#cart_items-->
           
            <div style="margin-top: 150px;">
                <section id="cart_items">
                    <div class="container">
                         <div class="row">
                             <div class="col text-center" style="margin-bottom:50px;">
                            <div class="section_title new_arrivals_title">
                                <h2>Show Clients Profile & History</h2>
                            </div>
                        </div>
                    </div>
                        <div class="table-responsive cart_info table-hover" id="table_content">
                            <table class="table table-condensed" >
                                <thead class="thead-inverse">
                                    <tr class="cart_menu">
                                        <th class="image">Name</th>
                                        <th class="price">Email</th>
                                        <th class="total">Total Price </th>

                                    </tr>
                                </thead>
                                <tbody id="user_table">



                                </tbody>
                            </table>

                        </div>
                        <div id="user_profile" class="product-grid" style="width:50%"  >

                            <div class="card text-center">
                                <div class="card-header">
                                    <ul class="nav nav-tabs card-header-tabs">
                                        <li class="nav-item " onclick="showUserProfile(0)">
                                            <a class="nav-link headerColor" href="#" id="profile">Profile</a>
                                        </li>
                                        <li class="nav-item" onclick="showHistory2()">
                                            <a class="nav-link headerColor " href="#" id="historySec">History</a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="card-block">
                                    <div id="user_profile_section">
                                    </div>
                                </div>
                            </div>

                        </div>
                </section>
            </div>
            
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
                                <div class="cr">?2018 All Rights Reserverd. This template is made with <i class="fa fa-heart-o"
                                                                                                          aria-hidden="true"></i>
                                    by <a>ShopZone</a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </footer>
        </div>


        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/jquery.min.js"></script>
        <script src="js/kendo.all.min.js"></script>
        <script src="css/bootstrap4/popper.js"></script>
        <script src="css/bootstrap4/bootstrap.min.js"></script>
        <script src="plugins/Isotope/isotope.pkgd.min.js"></script>
        <script src="plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
        <script src="plugins/easing/easing.js"></script>
        <script type="text/javascript" src="js/bootpag.js"></script>
        <script src="js/custom.js"></script>
        <script src="js/show_users.js"></script>

    </body>
</html>
