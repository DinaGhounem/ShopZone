function loadCategoryData() {
    $.ajax({
        url: 'CategoryServlet',
        type: 'POST',
        contentType: 'application/json',
        dataType: 'json',
        success: function (val) {
            Categories = val;
            $("#categories_section tr").remove();
            for (var i = 0; i < val.length; i++) {
                $('#categories_section').append('<tr><td>' + Categories[i].categoryName + '</td></tr>');
            }
        }
    });
}

function addNewProduct() {
    $('#btns_section').append('<button type="button" id=save_btn class="newsletter_submit_btn trans_300" onclick="saveCategories()">Save</button>');
    $('#new_category_btn').prop('disabled', true);
    $('#categories_section').append('<tr><td> <input type=text class="form_input input_name input_ph" id=cat /></td></tr>');

}
function saveCategories() {

    var categoryName = $('#cat').val();
    if (categoryName === "")
        loadCategoryData();
    else
    {
        var jsonData = {"NewCateegory": categoryName};
        $.ajax({url: 'CategoryServlet',
            type: 'GET', //servlet request type
            contentType: 'application/json', //For input type
            data: jsonData, //input data
            dataType: 'json', //For output type
            success: function (data)
            {

            }
        });
    }
    $('#save_btn').remove();
    $('#new_category_btn').prop('disabled', false);
    loadCategoryData();

}