/*price range*/
var RGBChange = function () {
    $('#RGB').css('background', 'rgb(' + r.getValue() + ',' + g.getValue() + ',' + b.getValue() + ')')
};

/*scroll to top*/

$(document).ready(function () {
    $(function () {
        $.scrollUp({
            scrollName: 'scrollUp', // Element ID
            scrollDistance: 300, // Distance from top/bottom before showing element (px)
            scrollFrom: 'top', // 'top' or 'bottom'
            scrollSpeed: 300, // Speed back to top (ms)
            easingType: 'linear', // Scroll to top easing (see http://easings.net/)
            animation: 'fade', // Fade, slide, none
            animationSpeed: 200, // Animation in speed (ms)
            scrollTrigger: false, // Set a custom triggering element. Can be an HTML string or jQuery object
            //scrollTarget: false, // Set a custom target element for scrolling to the top
            scrollText: '<i class="fa fa-angle-up"></i>', // Text for element, can contain HTML
            scrollTitle: false, // Set a custom <a> title if required.
            scrollImg: false, // Set true to use image
            activeOverlay: false, // Set CSS color to display scrollUp active point, e.g '#00FFFF'
            zIndex: 2147483647 // Z-Index for the overlay
        });
    });
});

function incrementProductQuantity(productId) {
    var priceStr = $("#" + productId).find(".cart_price").find("p")[0].innerText;
    priceStr = priceStr.substr(1, priceStr.length);
    var price = parseFloat(priceStr);
    var total = $("#" + productId).find(".cart_total_price")[0];
    $.post("AddProductToCart", {productId: productId}, function () {
        var value = parseInt($("#" + productId).find(".cart_quantity_input")[0].value);
        value++;
        $("#" + productId).find(".cart_quantity_input")[0].value = value;
        total.innerText = "$" + (price * value).toFixed(1);
    });
}

function decrementProductQuantity(productId) {
    var priceStr = $("#" + productId).find(".cart_price").find("p")[0].innerText;
    priceStr = priceStr.substr(1, priceStr.length);
    var price = parseInt(priceStr);
    var total = $("#" + productId).find(".cart_total_price")[0];
    var value = parseInt($("#" + productId).find(".cart_quantity_input")[0].value);
    if (value == 1) {
        return;
    }
    $.post("RemoveProductFromCart", {productId: productId}, function () {
        value--;
        $("#" + productId).find(".cart_quantity_input")[0].value = value;
        total.innerText = "$" + (price * value).toFixed(1);
    });
}

function removeProduct(productId) {
    $.post("RemoveProductFromCart", {productId: productId, removeProduct: ""}, function () {
        $("#" + productId).remove();
    });
}

function setProductQuantity(e, productId) {

    var priceStr = $("#" + productId).find(".cart_price").find("p")[0].innerText;
    priceStr = priceStr.substr(1, priceStr.length);
    var price = parseFloat(priceStr);
    var total = $("#" + productId).find(".cart_total_price")[0];
    var validated = validateQuantity(productId);
    if (validated) {
        if (event.keyCode === 13) {
            var productQuantity = $("#" + productId).find(".cart_quantity_input")[0].value;
            $.post("SetProductQuantityIntoCart", {productId: productId, quantity: productQuantity}, function () {
                var value = parseInt(productQuantity);
                $("#" + productId).find(".cart_quantity_input")[0].value = value;
                total.innerText = "$" + (price * value).toFixed(1);
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
    if(isNumeric(quantity))
    {
        var value = parseInt(quantity);
        if (value > 0 && value < 20) {
            validated = true;
        }
    }
    else
    {
        validated = false;
    }

    return validated;
}

function isNumeric(num){
    return !isNaN(num)
}