/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function callback(response, statusTxt, xhr)
{
    if (statusTxt == "success") {
        //object contain array of products
        object = JSON.parse(response);
        console.log(object[0].productName);

    }}
    function getProducts(){
      
        $.get("ShowProductServlet", callback);

      
    }
