<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Mohamed Mahrous
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <title>Checkout</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="Colo Shop Template">
    <meta name="viewport" content="width=device-width, initial-scale=1">
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

</head>

<body>

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

    <!-- Slider -->


    <!-- New Arrivals -->

    <div class="new_arrivals">
        <div class="container" style="margin-top: 200px">
                <div class="heading cf">
                    <h1>Checkout Report</h1>

                </div>
            <table class="table table-striped table-hover">
                <thead class="thead-inverse">
                <th>
                    Product ID
                </th>
                <th>
                    Product
                </th>
                <th>
                    Quantity
                </th>
                <th>
                    Total price
                </th>
                <th>
                    Success
                </th>
                <th>
                    Comment
                </th>
                </thead>
                <tbody>

                <c:forEach items="${sessionScope.transactions}" var="transaction" varStatus="loop">
                    <tr>
                        <td>
                            <p>#QUE-${transaction.cartEntity.productsInfoEntity.productId}</p>
                        </td>
                        <td>
                                <%--<img src="${transaction.cartEntity.productsInfoEntity.img}" style="height: 150px;width: 150px"--%>
                                <%--alt="${transaction.cartEntity.productsInfoEntity.productName}" class="itemImg"/>--%>
                            <h3>${transaction.cartEntity.productsInfoEntity.productName}</h3>
                        </td>
                        <td>
                                ${transaction.cartEntity.quantity}
                        </td>
                        <td>
                            <p class="cart_total_price">
                                $${transaction.cartEntity.quantity * transaction.cartEntity.productsInfoEntity.price}
                            </p>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${transaction.status eq 'OK'}">
                                    <i class="fa fa-check-circle" style="color: #0c9083; font-size:20pt"></i>
                                </c:when>
                                <c:otherwise>
                                    <i class="fa fa-times-circle" style="color: #ff1235; font-size:20pt"></i>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                                ${transaction.comment}
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>

            <div class="heading cf">
                <a href="home.jsp" class="continue">Continue Shopping</a>
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
                            <div class="cr">�2018 All Rights Reserverd. This template is made with <i
                                    class="fa fa-heart-o" aria-hidden="true"></i> by <a href="#">Colorlib</a></div>
                        </div>
                    </div>
                </div>
            </div>

        </footer>


    </div>
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
<script src="js/show_product.js"></script>
</body>

</html>
