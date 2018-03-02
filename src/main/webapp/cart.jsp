<%--
  Created by IntelliJ IDEA.
  User: Mohamed Mahrous
  Date: 3/2/2018
  Time: 5:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Cart | ShopZone</title>
    <link href="cartincludes/css/bootstrap.min.css" rel="stylesheet">
    <link href="cartincludes/css/font-awesome.min.css" rel="stylesheet">
    <link href="cartincludes/css/prettyPhoto.css" rel="stylesheet">
    <link href="cartincludes/css/price-range.css" rel="stylesheet">
    <link href="cartincludes/css/animate.css" rel="stylesheet">
    <link href="cartincludes/css/main.css" rel="stylesheet">
    <link href="cartincludes/css/responsive.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
    <link rel="shortcut icon" href="cartincludes/images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="cartincludes/images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="cartincludes/images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="cartincludes/images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="cartincludes/images/ico/apple-touch-icon-57-precomposed.png">
</head><!--/head-->

<body>

<section id="cart_items">
    <div class="container">
        <div class="table-responsive cart_info">
            <table class="table table-condensed">
                <thead>
                <tr class="cart_menu">
                    <td class="image">Item</td>
                    <td class="description"></td>
                    <td class="price">Price</td>
                    <td class="quantity">Quantity</td>
                    <td class="total">Total</td>
                    <td></td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.cartEntities}" var="cartEntity">
                    <tr>
                        <td class="cart_product">
                            <a href=""><img src="${cartEntity.productsInfoEntity.img}" alt="${cartEntity.productsInfoEntity.productName}"></a>
                        </td>
                        <td class="cart_description">
                            <h4><a href="">${cartEntity.productsInfoEntity.productName}</a></h4>
                            <p>${cartEntity.productsInfoEntity.description}</p>
                        </td>
                        <td class="cart_price">
                            <p>$${cartEntity.productsInfoEntity.price}</p>
                        </td>
                        <td class="cart_quantity">
                            <div class="cart_quantity_button">
                                <a class="cart_quantity_up" href=""> + </a>
                                <input class="cart_quantity_input" type="text" name="quantity" value="${cartEntity.quantity}" autocomplete="off" size="2">
                                <a class="cart_quantity_down" href=""> - </a>
                            </div>
                        </td>
                        <td class="cart_total">
                            <p class="cart_total_price">$${cartEntity.quantity * cartEntity.productsInfoEntity.price}</p>
                        </td>
                        <td class="cart_delete">
                            <a class="cart_quantity_delete" href=""><i class="fa fa-times"></i></a>
                        </td>
                    </tr>
                </c:forEach>

                <%--<tr>--%>
                    <%--<td class="cart_product">--%>
                        <%--<a href=""><img src="cartincludes/images/cart/two.png" alt=""></a>--%>
                    <%--</td>--%>
                    <%--<td class="cart_description">--%>
                        <%--<h4><a href="">Colorblock Scuba</a></h4>--%>
                        <%--<p>Web ID: 1089772</p>--%>
                    <%--</td>--%>
                    <%--<td class="cart_price">--%>
                        <%--<p>$59</p>--%>
                    <%--</td>--%>
                    <%--<td class="cart_quantity">--%>
                        <%--<div class="cart_quantity_button">--%>
                            <%--<a class="cart_quantity_up" href=""> + </a>--%>
                            <%--<input class="cart_quantity_input" type="text" name="quantity" value="1" autocomplete="off" size="2">--%>
                            <%--<a class="cart_quantity_down" href=""> - </a>--%>
                        <%--</div>--%>
                    <%--</td>--%>
                    <%--<td class="cart_total">--%>
                        <%--<p class="cart_total_price">$59</p>--%>
                    <%--</td>--%>
                    <%--<td class="cart_delete">--%>
                        <%--<a class="cart_quantity_delete" href=""><i class="fa fa-times"></i></a>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td class="cart_product">--%>
                        <%--<a href=""><img src="cartincludes/images/cart/three.png" alt=""></a>--%>
                    <%--</td>--%>
                    <%--<td class="cart_description">--%>
                        <%--<h4><a href="">Colorblock Scuba</a></h4>--%>
                        <%--<p>Web ID: 1089772</p>--%>
                    <%--</td>--%>
                    <%--<td class="cart_price">--%>
                        <%--<p>$59</p>--%>
                    <%--</td>--%>
                    <%--<td class="cart_quantity">--%>
                        <%--<div class="cart_quantity_button">--%>
                            <%--<a class="cart_quantity_up" href=""> + </a>--%>
                            <%--<input class="cart_quantity_input" type="text" name="quantity" value="1" autocomplete="off" size="2">--%>
                            <%--<a class="cart_quantity_down" href=""> - </a>--%>
                        <%--</div>--%>
                    <%--</td>--%>
                    <%--<td class="cart_total">--%>
                        <%--<p class="cart_total_price">$59</p>--%>
                    <%--</td>--%>
                    <%--<td class="cart_delete">--%>
                        <%--<a class="cart_quantity_delete" href=""><i class="fa fa-times"></i></a>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                </tbody>
            </table>
        </div>
    </div>
</section> <!--/#cart_items-->
<script src="cartincludes/js/jquery.js"></script>
<script src="cartincludes/js/bootstrap.min.js"></script>
<script src="cartincludes/js/jquery.scrollUp.min.js"></script>
<script src="cartincludes/js/jquery.prettyPhoto.js"></script>
<script src="cartincludes/js/main.js"></script>
</body>
</html>
