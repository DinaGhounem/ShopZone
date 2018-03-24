<%-- 
    Document   : home
    Created on : Mar 1, 2018, 4:24:16 AM
    Author     : Hanaa
--%>

<!DOCTYPE html>
<%@page import="jtech.shopzone.view.controller.ShowProductServlet" %>
<html lang="en">
    <head>
        <title>Shop Zone </title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="description" content="Colo Shop Template">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" type="image/png" href="signincludes/images/icons/favicon.ico"/>
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
       
        <link rel="stylesheet" href="css/pagination.css"/>
        <style>
            .product_bubble_right{
                right: -5px;
                top: 5px;
            }
            .product_bubble_right:after{
                color: transparent;
            }
        </style>
        <%@page import="jtech.shopzone.view.controller.AdminProductServlet" %>
    </head>

    <body onload="
            getProducts(1, 0);
            ">

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
           


            <!-- New Arrivals -->

            <div class="new_arrivals" style="margin-top:10%">
                <div class="container">
                    <div class="row">
                        <div class="col text-center">
                            <div class="section_title new_arrivals_title"> 
                                <h2> Products In Store</h2>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <div id="products" class="product-grid" data-isotope='{ "itemSelector": ".product-item", "layoutMode": "fitRows" }'>

                                <h3>loading...</h3>
                            </div>
                            <div class="wrapper">
                                    <div class="container">
                                        <div id="pagination-here"></div></div></div>
                        </div>
                    </div>
                </div>
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
        <script src="js/admin_show_product.js"></script>
    </body>

</html>
