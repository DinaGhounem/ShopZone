<<<<<<< HEAD
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
    $.post("AddProductToCart", {productId: productId}, function () {
        var value = parseInt($("#"+productId).find(".cart_quantity_input")[0].value);
        value++;
        $("#"+productId).find(".cart_quantity_input")[0].value = value;
    });
}

function decrementProductQuantity(productId) {
    var value = parseInt($("#"+productId).find(".cart_quantity_input")[0].value);
    if(value==1)
    {
        return;
    }
    $.post("RemoveProductFromCart", {productId: productId}, function () {
        value--;
        $("#"+productId).find(".cart_quantity_input")[0].value = value;
    });
}

function removeProduct(productId) {
    $.post("RemoveProductFromCart", {productId: productId,removeProduct:""}, function () {
        $("#"+productId).remove();
    });
=======
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
    priceStr = priceStr.substr(1,priceStr.length);
    var price = parseFloat(priceStr);
    var total = $("#" + productId).find(".cart_total_price")[0];
    $.post("AddProductToCart", {productId: productId}, function () {
        var value = parseInt($("#" + productId).find(".cart_quantity_input")[0].value);
        value++;
        $("#" + productId).find(".cart_quantity_input")[0].value = value;
        total.innerText = "$"+(price * value).toFixed(1);
    });
}

function decrementProductQuantity(productId) {
    var priceStr = $("#" + productId).find(".cart_price").find("p")[0].innerText;
    priceStr = priceStr.substr(1,priceStr.length);
    var price = parseInt(priceStr);
    var total = $("#" + productId).find(".cart_total_price")[0];
    var value = parseInt($("#" + productId).find(".cart_quantity_input")[0].value);
    if (value == 1) {
        return;
    }
    $.post("RemoveProductFromCart", {productId: productId}, function () {
        value--;
        $("#" + productId).find(".cart_quantity_input")[0].value = value;
        total.innerText = "$"+(price * value).toFixed(1);
    });
}

function removeProduct(productId) {
    $.post("RemoveProductFromCart", {productId: productId, removeProduct: ""}, function () {
        $("#" + productId).remove();
    });
}

function setProductQuantity(e, productId) {
    console.log(e);
    console.log(productId);
}

function validateQuantity(productId)
{
    var validated = false;
    var value = parseInt($("#" + productId).find(".cart_quantity_input")[0].value);
    if(value>0)
    {
        validated = true;
    }
    return validated;
>>>>>>> 22a3db5a9ee258e526a00290bd72080e46d4c714
}