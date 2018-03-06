/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
showUsers();

function showUsers(){
    $.get("ShowUserToAdmin",showUserCallBack)
}
function showUserCallBack(response, statusTxt, xhr)
{
    if (statusTxt == "success") {
        object=JSON.parse(response);
        content="";
        for(i=0;i<Object.length;i++){
            content+=" <tr id=\"\" onclick='showUserProfile("+object[0].userId+")'><td class=\"cart_product\">"+object[0].firstName+" "+object[0].lastName;
                                    
                content+= "</td>  <td class=\"cart_description\">"+object[0].email;
                     content+= "</td><td class=\"cart_price\"><p></p> </td></tr>";

        }
        $(user_table).html(content);
    }}
function showUserProfile(userId){
     $.get("ShowUserToAdmin?userId="+userId,showUserProfileCallBack)
    
}
function showUserProfileCallBack(response, statusTxt, xhr)
{
    if (statusTxt == "success") {
         var content = "";
        object = JSON.parse(response);
        if( object!=null) {
            
                content += "<div class=\"product-item men\" >" +
                        "<div class=\"product discount product_filter\">" +
                        "<div class=\"product_image\">" +
                        "<img src=\"img/" + object.userImg + "\" alt=\"\">" +
                        "</div>" +
                        "<div class=\"favorite favorite_left\" onclick=\"clearFun()\"></div>";
               
                content += "<div class=\"product_info\">" +
                        "<h6 class=\"product_price\">" + object.firstName + " "+object.lastName+"<br/>(" + object.email + ")</h6>" +
                        //"<div class=\"product_name\"><span class=\"product_price\">Job : </span>" + object.job + "</div>"  +
                        "<div class=\"product_name\"><span class=\"product_price\">Address : </span>" + object.address + "</div>" +
                        "<div class=\"product_name\"><span class=\"product_price\">Birth date : </span>" + object.birthdate + "</div>" +
                        "</div>" +
                        "</div>" +
                        "<div class=\"red_button add_to_cart_button\" >show history</div>" +
                        "</div>";
                /*  if (i % 4 == 0) {
                 height += 400;
                 }*/
            
        }
        $(user_profile_section).css("float","right");
        $(user_profile_section).css("width","100%");
        $(user_profile_section).html(content);
    }}
function clearFun(){
    $(user_profile_section).html("<div></div>");
}