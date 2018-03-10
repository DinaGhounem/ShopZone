<%-- 
    Document   : addProduct
    Created on : Mar 9, 2018, 12:51:36 PM
    Author     : Hanaa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="jtech.shopzone.view.controller.ShowProductServlet" %>
<%@page import="jtech.shopzone.view.controller.AddProduct" %>
<html lang="en">
    <head>
        <title>Colo Shop</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="description" content="Colo Shop Template">
        <meta name="viewport" content="width=device-width, initial-scale=1">
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
        <link rel="stylesheet" href="css/pagination.css"/>
        <style>
            #drop-area {
                border: 2px dashed #ccc;
                border-radius: 20px;
                width: 480px;
                height: 300px;
                font-family: sans-serif;
                margin: 20px auto;
                padding: 20px;
            }
            #drop-area.highlight {
                border-color: #92b0b3;
            }
            p {
                margin-top: 0;
            }
            .my-form {
                margin-bottom: 10px;
            }
            #gallery {
                margin-top: 10px;
            }
            #gallery img {
                width: 150px;
                margin-bottom: 10px;
                margin-right: 10px;
                vertical-align: middle;
            }
            .button {
                display: inline-block;
                padding: 10px;
                background: #ccc;
                cursor: pointer;
                border-radius: 5px;
                border: 1px solid #ccc;
            }
            .button:hover {
                background: #ddd;
            }
            #productImg {
                width:100%;
                height:100%;

            }
            .box__icon
            {
                width: 100%;
                height: 80px;
                fill: #92b0b3;
                display: block;
                margin-bottom: 40px;
                margin: -40% -5%;
                position: relative;
                z-index: -1;
            }

        </style>
    </head>

    <body onload="getCategories()">

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

                                        <!--  / / My Account -->

                                        <li class="account">
                                            <a href="#">
                                                My Account
                                                <i class="fa fa-angle-down"></i>
                                            </a>
                                            <ul class="account_selection">
                                                <li><a href="#"><i class="fa fa-sign-in" aria-hidden="true"></i>Logout</a></li>
                                                <li><a href="#"><i class="fa fa-user-plus" aria-hidden="true"></i>Register</a></li>
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
                    <form action="AddProduct" class="form-horizontal" role="form" method="post" enctype="multipart/form-data">

                        <div class="form-group">

                        </div>
                </div> <!-- form-group // -->

                <div class="form-group">
                    <label for="name" class="col-sm-3 control-label">Product Name</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" name="name" id="name" placeholder="enter product helpful name" required/>
                        <label id="name_error"></label>
                    </div>
                </div> <!-- form-group // -->
                <div class="form-group">
                    <label for="name" class="col-sm-3 control-label">Product Price</label>
                    <div class="col-sm-9">
                        <input type="number" class="form-control" name="price" id="price" placeholder="ex:200" min="1" required/>
                        <label id="price_error"></label>
                    </div>
                </div> <!-- form-group // -->
                <div class="form-group">
                    <label for="qty" class="col-sm-3 control-label">Quantity</label>
                    <div class="col-sm-3">
                        <input type="number" class="form-control" name="quantity" id="quantity" placeholder="quantity" min="1" required/>
                        <label id="quantity_error"></label>
                    </div>
                </div> <!-- form-group // -->
                <div class="form-group">
                    <label for="about" class="col-sm-3 control-label">Description</label>
                    <div class="col-sm-9">
                        <textarea class="form-control" name="description" id="description" required></textarea>
                        <label id="description_error"></label>
                    </div>


                </div> <!-- form-group // -->
                <div class="form-group">
                    <label for="name" class="col-sm-3 control-label">Product image</label>
                    <div class="col-sm-3">
                        <label class="control-label small" for="file_img">image(jpg/png):</label>
                        <div id="drop-area">

                            <input type="file" name="file_img" id="productImg"  accept="image/x-png,image/gif,image/jpeg" onchange="change()" required>
                            <svg class="box__icon" xmlns="http://www.w3.org/2000/svg" width="50" height="43" viewBox="0 0 50 43"><path d="M48.4 26.5c-.9 0-1.7.7-1.7 1.7v11.6h-43.3v-11.6c0-.9-.7-1.7-1.7-1.7s-1.7.7-1.7 1.7v13.2c0 .9.7 1.7 1.7 1.7h46.7c.9 0 1.7-.7 1.7-1.7v-13.2c0-1-.7-1.7-1.7-1.7zm-24.5 6.1c.3.3.8.5 1.2.5.4 0 .9-.2 1.2-.5l10-11.6c.7-.7.7-1.7 0-2.4s-1.7-.7-2.4 0l-7.1 8.3v-25.3c0-.9-.7-1.7-1.7-1.7s-1.7.7-1.7 1.7v25.3l-7.1-8.3c-.7-.7-1.7-.7-2.4 0s-.7 1.7 0 2.4l10 11.6z"/></svg>
                            <label id="img_error"></label>
                        </div>
                    </div>

                </div>
                <!-- form-group // -->
                <div class="form-group">
                    <label for="tech" class="col-sm-3 control-label">Product Category</label>
                    <div class="col-sm-3">
                        <select id="size" name="categoryId" required>

                        </select>

                    </div>
                </div> <!-- form-group // -->
                <hr/>
                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-9">
                        <input type="submit" class="btn btn-primary"  value="Add Product"/>
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
                            <div class="cr">©2018 All Rights Reserverd. This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="#">Colorlib</a></div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>





    </div>
    <script>

let dropArea = document.getElementById('drop-area')
;['dragenter', 'dragover', 'dragleave', 'drop'].forEach(eventName => {
  dropArea.addEventListener(eventName, preventDefaults, false)
})

function preventDefaults (e) {
  
}
;['dragenter', 'dragover'].forEach(eventName => {
  dropArea.addEventListener(eventName, highlight, false)
})

;['dragleave', 'drop'].forEach(eventName => {
  dropArea.addEventListener(eventName, unhighlight, false)
})

function highlight(e) {
  dropArea.classList.add('highlight')
}

function unhighlight(e) {
  dropArea.classList.remove('highlight')
}

    </script>
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="css/bootstrap4/popper.js"></script>
    <script src="css/bootstrap4/bootstrap.min.js"></script>
    <script src="plugins/Isotope/isotope.pkgd.min.js"></script>
    <script src="plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
    <script src="plugins/easing/easing.js"></script>
    <script src="js/custom.js"></script>
    <script src="js/admin_show_product.js"></script>
    <script src="js/jquery.min.js"></script>

    <script src="css/bootstrap4/popper.js"></script>
    <script src="css/bootstrap4/bootstrap.min.js"></script>
    <script src="plugins/Isotope/isotope.pkgd.min.js"></script>
    <script src="plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
    <script src="plugins/easing/easing.js"></script>
    <script type="text/javascript" src="js/bootpag.js"></script>
    <script src="js/custom.js"></script>
    <script src="js/add_product.js"></script>
</body>

</html>
