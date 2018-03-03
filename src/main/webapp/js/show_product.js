/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var pageNum = 20;
getProductsCount();
currentPage=1;
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
                        "<img src=\"img/" + object[i].img + "\" alt=\"\">" +
                        "</div>" +
                        "<div class=\"favorite favorite_left\"></div>";
                if (object[i].quantity == 1) {
                    content += "<div class=\"product_bubble product_bubble_right product_bubble_red d-flex flex-column align-items-center\"><span>latest</span></div>";
                }
                content += "<div class=\"product_info\">" +
                        "<h6 class=\"product_name\"><a href=\"single.html\">" + object[i].productName + "(" + object[i].description + ")</a></h6>" +
                        "<div class=\"product_price\">$" + object[i].price + "<span>$590.00</span></div>" +
                        "</div>" +
                        "</div>" +
                        "<div class=\"red_button add_to_cart_button\" onclick=\"addProduct(" + object[i].productId + ")\">add to cart</div>" +
                        "</div>";
              /*  if (i % 4 == 0) {
                    height += 400;
                }*/
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
function getProducts(page) {

    $.get("ShowProductServlet?page=" + page, callback);


}
function getProductsCount() {

    $.get("ShowProductServlet", ProductsCountcallback);


}
function ProductsCountcallback(response, statusTxt, xhr)
{
    if (statusTxt == "success") {
        pageNum = Math.round(response / 8);
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
            currentPage=num;
            getProducts(num);
        });
    }
}
function addProduct(productId) {

    $.post("AddProductToCart", {productId: productId}, showProductCallback);

}
function showProductCallback(response, statusTxt, xhr)
{
    if (statusTxt == "success") {
        getProductsCount();
        getProducts(currentPage);
        
    }
}
