function loadData() {
    $.ajax({
        url: 'CategoryServlet',
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        success: function (val) {
            
        }
    });
}