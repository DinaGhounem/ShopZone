/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).on("submit", "form", function(e){

    //e.preventDefault();
    //alert('it works!');
    return validateImage(e);
});
function getCategories() {

    $.get("ShowCategory", getCategoriesCallBack);


}
function getCategoriesCallBack(response, statusTxt, xhr)
{
    if (statusTxt == "success") {
        object = JSON.parse(response);
       content="";
        for (i = 0; i < object.length; i++) {
            content += "<option value='"+object[i].categoryId+"'>" + object[i].categoryName + "</option>";

        }
        $("#size").html(content);
     




    }
}
function changeCategory() {
   
    validate();



}  

///////////////////////////////////////////////////////////////////////////////////////
//Validation Section


function validate(){
    checkProductName();
     var fullPath = document.getElementById('productImg').value;
            
            alert(fullPath);
}
function checkProductName(){
    var name=$("#size option:selected").val();
    alert(name);
    
    
    
}



function validateImage(e) {
    var hasImage = false;
    if(imageLoader == undefined ||imageLoader.files == null || imageLoader.files == undefined ||imageLoader.files.length == 0)
    {
        e.preventDefault();
        hasImage = false;
        $("#imageError")[0].innerHTML = "Please select a Product picture!!";
    }
    else
    {
        hasImage = true;
    }
    return hasImage;
}