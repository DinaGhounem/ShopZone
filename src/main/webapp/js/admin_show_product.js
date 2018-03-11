/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var pageNum = 20;
var categoryId = 0;
currentPage = 1;
var Cart = null;
searchFlag = true;
categoryPriceFlag = false;
searchFrom = 0;
searchTo = 0;

function getProducts(page, categoryId) {

    $.get("ShowProductServlet?page=" + page + "&categoryId=" + categoryId, callback);



}

function callback(response, statusTxt, xhr) {
    if (statusTxt == "success") {
        //object contain array of products
        var content = "";
        var height = 0;
        object = JSON.parse(response);
        for (i = 0; i < object.length; i++) {
            if (object[i].deletedFlg==1) {
                content += "<div class=\"product-item men\" style='float:left;margin:2%'>" +
                    "<form action=\"editProduct.jsp\"method='POST'>" +
                    "<input type=\"number\" name=\"productId\" value=" + object[i].productId + " hidden>" +
                    "<div class=\"product discount product_filter\">" +
                    "<div class=\"product_image\">" +
                    "<img src=\"" + object[i].img + "\" alt=\"\">" +
                    "</div>" +
                    "<div class=\"favorite favorite_left\"></div>" +
                    "<div class=\"product_bubble product_bubble_right  d-flex flex-column align-items-center\"><span>" +
                    "<image src=\"img/x.png\" style=\"width:20px;height\" onclick=\"onclickEdit(" + object[i].productId + ")\"/>" +
                    "</span></div>" +
                    "<div class=\"product_info\">" +
                    "<h6 class=\"product_name\"><a href=\"single.html\">" + object[i].productName + "(" + object[i].description + ")</a></h6>" +
                    "<div class=\"product_price\">$" + object[i].price + "<span>$590.00</span></div>" +
                    "</div>" +
                    "</div>" +
                    "<div>" + "<button class=\"red_button add_to_cart_button\" onclick=\"setPID(" + object[i].productId + ")\" type=\"submit\">" + "Edit Product" + "</button></div>" +
                    "</form>" +
                    "</div>";
                /* if (i % 4 == 0) {
                     height += 400;
                 }*/
            }
        }
        $(products).html(content);
        $(products).css("height", "800px");

    }
}

function getProductsCount(categoryId) {

    $.get("ShowProductServlet?categoryId=" + categoryId, ProductsCountcallback);


}

function getProductsCountBTWRang(categoryId) {

    $.get("ShowProductServlet?categoryId=" + categoryId + "&flag=true&from=" + searchFrom + "&to=" + searchTo, ProductsCountcallback);


}

function ProductsCountcallback(response, statusTxt, xhr) {
    if (statusTxt == "success") {
        if (response % 8 == 0 || response % 8 >= 5 || response < 8) {
            pageNum = Math.round(response / 8);
        } else {
            pageNum = Math.round(response / 8) + 1;
        }
        $('#pagination-here').bootpag({
            total: pageNum,
            page: 1,
            maxVisible: 3,
            leaps: true,
            href: "#result-page-{{number}}",
        })

//page click action
        $('#pagination-here').on("page", function (event, num) {
            //show / hide content or pull via ajax etc
            currentPage = num;
            if (searchFlag) {
                getProducts(num, categoryId);
            } else {
                searchByPrice(num, searchFrom, searchTo);
            }
        });
    }
}

function addProduct(productId) {

    $.post("AddProductToCart", {productId: productId}, showProductCallback);
    getCart();

}

function showProductCallback(response, statusTxt, xhr) {
    if (statusTxt == "success") {
        getProductsCount(categoryId);
        getProducts(currentPage, 0);

    }
}


function onclickEdit(PID) {
    $.get("RemoveProduct?productId=" + PID);
    getProductsCount(categoryId);
        getProducts(currentPage, 0);

}

function setPID(PID) {

    //put id in session
}
