/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
