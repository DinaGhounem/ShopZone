/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
productId = 2;
function getProduct() {

    $.get("AdminEditProductServlet", {productId: productId}, callback);
    $.get("ShowCategory", setCategory);
}

var productPrice = 0;
var productQuantity = 0;
var object = null;

function callback(response, statusTxt, xhr)
{
    if (statusTxt == "success") {
        //fill form with product data
        object = JSON.parse(response);
        Pid=object.productId;
        productName=document.getElementById("name").value = object.productName;
        document.getElementById("price").value = object.price;
        document.getElementById("quantity").value = object.quantity;
        description=document.getElementById("description").value = object.description;
        img=document.getElementById("imglabel").innerHTML = object.img;
        categoryId=document.getElementById("category").value = object.categoryId;
        productPrice = object.price;
        productQuantity = object.quantity;
    }

}
function setCategory(response, statusTxt, xhr) {
    if (statusTxt == "success") {
        object = JSON.parse(response);
        content = "";

        for (i = 0; i < object.length; i++) {
            content += "<option value=\"" + object.categoryName + "\">" + object.categoryName + "</option>";
//fill select with options
//<option value="men">Men</option>
//<option value="women">Women</option>
//<option value="technology">Technology</option>
        }
        document.getElementById("category").innerHTML = content;
    }
}
//function updateProducte() {
//    pPrice = document.getElementById("price").value.toString();
//    pQuantity = document.getElementById("quantity").value;
//    if (pPrice == productPrice && pQuantity == productQuantity) {
//        alert("no change in data");
//    } else {
//        object.price = pPrice;
//        object.quantity = pQuantity;
//        //$.get("UpdateProduct", {Pid: object.productId}, {name: object.productName}, {price: pPrice}, {quantity: pQuantity}, {description: object.description}, {img: object.img}, {categoryId: object.categoryId}, updateCallback);

        $("#updateButton").click(function () {
            alert("onclick");
            pPrice = $("#price").val();
            pQuantity = $("#quantity").val();
            if (pPrice == productPrice && pQuantity == productQuantity) {
                alert("no change in data");
            } else {
                var jsonData = {"Pid":Pid,"name": productName,"price": pPrice,"quantity": pQuantity,"description":description,"img": img,"categoryId":categoryId};
                $.ajax({url: 'UpdateProduct',
                    type: 'GET', //servlet request type
                    contentType: 'application/json', //For input type
                    data: jsonData, //input data
                    dataType: 'json', //For output type
                    success: function (data)
                    {
                        alert("data upadeted");
                       // $("myMessage").append(" ");

                    }
                });
            }
        });

        
    


function updateCallback(response, statusTxt, xhr) {
    if (statusTxt == "success") {
        alert(response);
    }

}

