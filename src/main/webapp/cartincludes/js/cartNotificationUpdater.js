$(document).ready(function () {
    setInterval(function () {
        $.post("CartQuantityServlet", function (data) {
            $("#checkout_items")[0].innerText = data;
        })
    }, 500);
});