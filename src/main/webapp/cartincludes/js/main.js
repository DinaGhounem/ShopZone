$(document).ready(function () {
    updateTotal();
});

function removeProduct(productId) {
    $.post("RemoveProductFromCart", {productId: productId, removeProduct: ""}, function () {
        $("#" + productId).remove();
    });
}

function setProductQuantity(e, productId) {

    var priceStr = $("#" + productId).find(".cart_price")[0].innerText;
    priceStr = priceStr.substr(4, priceStr.length);
    var price = parseFloat(priceStr);
    var total = $("#" + productId).find(".cart_total_price")[0];
    var validated = validateQuantity(productId);
    if (validated) {
        if (event.keyCode === 13) {
            var productQuantity = $("#" + productId).find(".cart_quantity_input")[0].value;
            $.post("SetProductQuantityIntoCart", {productId: productId, quantity: productQuantity}, function (data) {
                if (data == "OUT_OF_STOCK") {
                    $("#" + productId).find(".stockStatus").removeClass("out");
                    $("#" + productId).find(".stockStatus").addClass("out");
                    $("#" + productId).find(".stockStatus")[0].innerText = "OUT OF STOCK";

                }
                else if (data == "IN_STOCK") {
                    $("#" + productId).find(".stockStatus").removeClass("out");
                    $("#" + productId).find(".stockStatus")[0].innerText = "IN STOCK";
                }


                var value = parseInt(productQuantity);
                $("#" + productId).find(".cart_quantity_input")[0].value = value;
                total.innerText = "$" + (price * value).toFixed(1);
                updateTotal();
            });
        }
    }
    else {
        var totalVal = parseFloat(total.innerText.substr(1, total.innerText.length));
        var quantity = totalVal / price;
        $("#" + productId).find(".cart_quantity_input")[0].value = quantity;

    }

}

function validateQuantity(productId) {
    var validated = false;
    var quantity = $("#" + productId).find(".cart_quantity_input")[0].value;
    if (isNumeric(quantity)) {
        var value = parseInt(quantity);
        if (value > 0 && value < 20) {
            validated = true;
        }
    }
    else {
        validated = false;
    }

    return validated;
}

function isNumeric(num) {
    return !isNaN(num)
}

function updateTotal() {
    var total = 0.0;
    $(".cart_total_price").each(function () {
        var priceStr = this.innerText;
        priceStr = priceStr.substr(1, priceStr.length);
        var price = parseFloat(priceStr);
        total += price;
    });

    var totalSpan = $(".value")[0];
    totalSpan.innerText = "$" + total.toFixed(1);


}

function checkOut(userId) {
    $.post("CheckOut", {userId: userId}, function (data) {
        console.log(data);
    });
}