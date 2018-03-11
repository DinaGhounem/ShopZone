<%-- 
    Document   : home
    Created on : Mar 1, 2018, 4:24:16 AM
    Author     : Hanaa
--%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Shop Zone</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="description" content="Colo Shop Template">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="css/bootstrap4/bootstrap.min.css">
        <link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
        <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
        <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/animate.css">
        <link rel="stylesheet" type="text/css" href="css/main_styles.css">
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <%@page import="jtech.shopzone.view.controller.AdminEditProductServlet"%>
        <%@page import="jtech.shopzone.view.controller.UpdateProduct"%>
        <%@page import="jtech.shopzone.model.entity.ProductsInfoEntity"%>
        <%@page import="jtech.shopzone.view.controller.RemoveProduct"%>
    </head>

    <body onload="getProduct()">
        <input type=number value="${param['productId']}" id="productId"/>

        <div class="super_container">

            <!-- Header -->
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


            <!-- New Arrivals -->

            <div class="new_arrivals">
                <div class="container">
                    <div class="row">
                        <div class="col text-center">
                            <div class="section_title new_arrivals_title">
                                <h2>Admin Page</h2>
                            </div>
                        </div>
                    </div>
                    <form action="#" class="form-horizontal" role="form">

                        <div class="form-group">
                            <label for="name" class="col-sm-3 control-label">Product Category</label>
                            <div class="col-sm-9">

                            </div>
                        </div> <!-- form-group // -->

                        <div class="form-group">
                            <label for="name" class="col-sm-3 control-label">Product Name</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="name" id="name" disabled="disabled" placeholder="enter product helpful name">
                            </div>
                        </div> <!-- form-group // -->
                        <div class="form-group">
                            <label for="name" class="col-sm-3 control-label">Product Price</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="Pid" id="price" placeholder="ex:200">
                            </div>
                        </div> <!-- form-group // -->
                        <div class="form-group">
                            <label for="qty" class="col-sm-3 control-label">Quantity</label>
                            <div class="col-sm-3">
                                <input type="text" class="form-control" name="qty" id="quantity" placeholder="quantity">
                            </div>
                        </div> <!-- form-group // -->
                        <div class="form-group">
                            <label for="about" class="col-sm-3 control-label" >Description</label>
                            <div class="col-sm-9">
                                <textarea class="form-control" id="description" disabled="disabled"></textarea>
                            </div>


                        </div> <!-- form-group // -->
                        <div class="form-group">
                            <label for="name" class="col-sm-3 control-label">Product image</label>
                            <div class="col-sm-3">
                                <label class="control-label small" id="imglabel" for="file_img">image(jpg/png):</label>
                                <image src="img\x.jpeg" id="productImage" />
                            </div>

                        </div>
                        <!-- form-group // -->
                        <div class="form-group">
                            <label for="tech" class="col-sm-3 control-label">Product Category</label>
                            <div class="col-sm-3">
                                <select class="form-control" id="category" disabled="disabled">

                                </select>
                            </div>
                        </div> <!-- form-group // -->
                        <hr
                            <div class="form-group">
                            <div class="col-sm-offset-3 col-sm-9">
                                <button type="button" id="updateButton" class="btn btn-primary" >Save Product</button>
                                
                            </div>
                        </div> <!-- form-group // -->
                    </form>

                    <div class="row">
                        <div class="col">
                            <div id="productForm" class="product-grid" data-isotope='{ "itemSelector": ".product-item", "layoutMode": "fitRows" }'>
                                <div class="container">

                                    <section class="panel panel-default">
                                        <div class="panel-heading"> 
                                            <h3 class="panel-title">NEw Product</h3> 
                                        </div> 
                                        <div class="panel-body">


                                        </div><!-- panel-body // -->
                                    </section><!-- panel// -->


                                </div> <!-- container// -->

                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <!-- Newsletter -->

            <div class="newsletter">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="newsletter_text d-flex flex-column justify-content-center align-items-lg-start align-items-md-center text-center">
                                <h5><label id="result"></label></h5>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <form action="post">
                                <div class="newsletter_form d-flex flex-md-row flex-column flex-xs-column align-items-center justify-content-lg-end justify-content-center">
                                    <input id="newsletter_email" type="email" placeholder="Your email" required="required" data-error="Valid email is required.">
                                    <button id="newsletter_submit" type="submit" class="newsletter_submit_btn trans_300" value="Submit">subscribe</button>
                                </div>
                            </form>
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
    </body>
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="css/bootstrap4/popper.js"></script>
    <script src="css/bootstrap4/bootstrap.min.js"></script>
    <script src="plugins/Isotope/isotope.pkgd.min.js"></script>
    <script src="plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
    <script src="plugins/easing/easing.js"></script>
    <script src="js/custom.js"></script>
    <script src="js/edit_product.js"></script>
</html>
