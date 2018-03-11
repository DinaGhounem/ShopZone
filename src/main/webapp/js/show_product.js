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

getCart();
getMaxPrice();



function callback(response, statusTxt, xhr)
{
    if (statusTxt == "success") {
        //object contain array of products
        var content = "";
        object = JSON.parse(response);
        for (i = 0; i < object.length; i++) {
            if (object[i].quantity > 0) {
                content += "<div class=\"product-item men\" style='float:left;margin:2%'>" +
                        "<div class=\"product discount product_filter\">" +
                        "<div class=\"product_image\">" +
                        "<img src=\"" + object[i].img + "\" alt=\"\" class='img'>" +
                        "</div>" +
                        "<div class=\"favorite favorite_left\"></div>";
                if (object[i].quantity == 1) {
                    content += "<div class=\"product_bubble product_bubble_right product_bubble_red d-flex flex-column align-items-center\"><span>latest</span></div>";
                }
                content += "<div class=\"product_info\">" +
                        "<h6 class=\"product_name\"><a href=\"single.html\">" + object[i].productName + "(" + object[i].description + ")</a></h6>" +
                        "<div class=\"product_price\">$" + object[i].price;
                for (j = 0; j < Cart.length; j++) {
                    if (Cart[j].productsInfoEntity.productId == object[i].productId) {

                        if ((Cart[j].quantity) >= object[i].quantity - 1) {

                            content += "<span>Out Of Stock</span>";
                        }
                    }
                }
                content += "</div></div>" +
                        "</div>" +
                        "<div class=\"red_button add_to_cart_button\" onclick=\"addProduct(" + object[i].productId + ")\">add to cart</div>" +
                        "</div>";
            }
        }
        $(products).html(content);
        $(products).css("height", "800px");

        /* just for testing
         * if(object[0]!=null)
         console.log(object[0].productName,object[0].productId,object[0].price,object[0].description,object[0].img,object[0].quantity,object[0].categoryId);
         */
    }
}
function getProducts(page, categoryId) {

    $.get("ShowProductServlet?page=" + page + "&categoryId=" + categoryId, callback);



}
function getProductsCount(categoryId) {

    $.get("ShowProductServlet?categoryId=" + categoryId, ProductsCountcallback);


}
function getProductsCountBTWRang(categoryId) {

    $.get("ShowProductServlet?categoryId=" + categoryId + "&flag=true&from=" + searchFrom + "&to=" + searchTo, ProductsCountcallback);


}
function ProductsCountcallback(response, statusTxt, xhr)
{
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
function showProductCallback(response, statusTxt, xhr)
{
    if (statusTxt == "success") {
        getProductsCount(categoryId);
        getProducts(currentPage, 0);

    }
}

function getCategories() {

    $.get("ShowCategory", getCategoriesCallBack);


}
function getCategoriesCallBack(response, statusTxt, xhr)
{
    if (statusTxt == "success") {

        content = "<option value='0'>All</option>";
        object = JSON.parse(response);
        for (i = 0; i < object.length; i++) {
            content += "<option value='" + object[i].categoryId + "'>" + object[i].categoryName + "</option>";

        }

        $("#size").html(content);
        $("#size").kendoDropDownList();





    }
}
function changeCategory() {
    categoryId = document.getElementById("size").value;
    getProductsCount(categoryId);
    getProducts(1, categoryId);


}
function getCart() {

    $.get("CartProducts", getCartCallBack);


}
function getCartCallBack(response, statusTxt, xhr)
{
    if (statusTxt == "success") {


        Cart = JSON.parse(response);
    }
}

function searchByPriceAction() {

    $("#category").html("<h4 style='margin-top: 2em;'><label for='size' >Price</label></h4><label class='control-label' style='float:left'>From</label><input type='number' class='form-control' id='from' onblur='searchFun()' style='float:left'/><label class='control-label' style='float:left'>To</label><input type='number' class='form-control' id='to' onblur='searchFun()' style='float:left'/>")

}
function searchByCategory() {
    $("#category").html("<h4 style=\"margin-top: 2em;\"><label for=\"size\">Category</label></h4>" +
            "<select id=\"size\" style=\"width: 100%;float: left\" onchange=\"changeCategory()\" ></select>");
    getProducts(1, 0);
    getProductsCount(0);
    getCategories();
    
}

function searchFun() {

    var from = document.getElementById("from").value;
    var to = document.getElementById("to").value;

    searchFlag = false;
    if (from == "") {
        from = 0;
    }
    if (to == "") {
        getMaxPrice();
    } else {

    }
    searchTo = to;
    searchFrom = from;
    getProductsCountBTWRang(0);
    searchByPrice(1, from, to);
}
function searchByPrice(page, from, to) {
    $.get("ShowProductServlet?page=" + page + "&categoryId=0&from=" + from + "&to=" + to, callback);
}
function getMaxPrice() {
    $.get("MaxPrice", getMaxPriceCallBack);
}
function getMaxPriceCallBack(response, statusTxt, xhr)
{
    if (statusTxt == "success") {
        to = response;
    }
}