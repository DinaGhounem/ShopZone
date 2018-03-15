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
pageMin=0;
deleteFlag=false;

function getProducts(page, categoryId) {

    $.get("AdminProductServlet?page=" + page + "&categoryId=0", callback);



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
                    "<h6 class=\"product_name\"><a href=\"\">" + object[i].productName + "(" + object[i].description + ")</a></h6>" +
                    "<div class=\"product_price\">$" + object[i].price + "</div>" +
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

    $.get("AdminProductServlet?categoryId=0", ProductsCountcallback);


}



function ProductsCountcallback(response, statusTxt, xhr) {
    if (statusTxt == "success") {
        
        if (response % 8 == 0 || response % 8 >= 4 || response < 8) {
            pageNum = Math.round(response / 8);
        } else {
            pageNum = Math.round(response / 8) + 1;
        }
       
        if(response%8==0&&currentPage-1==(pageNum)){
             
            getProducts(currentPage-1, 0);
             currentPage--;
        }
       if(response==1){
           pageMin=1;
           currentPage--;
       }
       
        $('#pagination-here').bootpag({
            total: pageNum,
            page: currentPage,
            maxVisible: 3,
            leaps: true,
            href: "#result-page-{{number}}",
        })
        

//page click action
    if(pageMin!=1){
        $('#pagination-here').on("page", function (event, num) {
            //show / hide content or pull via ajax etc
           
            currentPage = num;
            
        
            if (searchFlag) {
                getProducts(currentPage, categoryId);
            } else {
                searchByPrice(num, searchFrom, searchTo);
            }
        });
    }
    }
}




function onclickEdit(PID) {
    $.get("RemoveProduct?productId=" + PID);
        getProductsCount(categoryId);
        getProducts(currentPage-pageMin, 0);
       
       
       pageMin=0;
       
       

}

function setPID(PID) {

    //put id in session
}
