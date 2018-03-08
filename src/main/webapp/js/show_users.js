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
        for(i=0;i<object.length;i++){
            content+=" <tr id=\"\" onclick='showUserProfile("+object[i].userId+")'><td class=\"cart_product\">"+object[i].firstName+" "+object[i].lastName;
                                    
                content+= "</td>  <td class=\"cart_description\">"+object[i].email;
                     content+= "</td><td class=\"cart_price\"><p></p> </td></tr>";

        }
        $(user_table).html(content);
    }}
function showUserProfile(userId){
     $.get("ShowUserToAdmin?userId="+userId,showUserProfileCallBack);
    
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
                        "<div class=\"product_name\"><span class=\"product_price\">Birth date : </span>"+object.birthdate + "</div>" ;
                        if(object.interests.length>0){
                        content+="<div class=\"product_name\"><span class=\"product_price\">Interests :</div>" ;
                        for(j=0;j<object.interests.length;j++){
                         content+="<span class=\"product_name\">"+object.interests[j].interestName+"</span>";
                         if(j!=object.interests.length-1){
                             content+=", ";
                         }
                        }
                    }
                        content+="</div>" +
                        "</div>" +
                        "<div class=\"red_button add_to_cart_button\" onclick='showHistory("+object.userId+")'><a href=\"#ex1\" rel=\"modal:open\">show history</a></div>" +
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

function showHistory(userId){
    
     $.get("UserHistory?userId="+userId,showUserHistoryCallBack)
}
function showUserHistoryCallBack(response, statusTxt, xhr)
{
    if (statusTxt == "success") {
        object = JSON.parse(response);
          content="";
        if(object.length>0){
           
        content="<div class=\"table-responsive cart_info\" style=\"width:100%\">"+
                                   " <table class=\"table table-condensed\" >"+
                                        "<thead> <tr class=\"cart_menu\">"+
                                               " <td class=\"image\">Product</td>"+
                                               " <td class=\"price\">Quantity</td>"+
                                                "<td class=\"total\">Date</td>"+
                                                "<td>Price</td></tr> </thead><tbody id=\"history_table\">";
                                            
                                        



        for(i=0;i<object.length;i++){
            content+=" <tr id=\"\" ><td class=\"cart_product\">"+object[i].productId;
                                    
                content+= "</td>  <td class=\"cart_description\">"+object[i].quantity;
                 content+= "</td>  <td class=\"cart_description\">"+object[i].date;
                     content+= "</td><td class=\"cart_price\"><p></p>"+ object[i].price+"</td></tr>";

        }
        content+="</tbody></table></div>";
                                       
                                    

                                
        
    }
    else{
        content="<span id=\"data2\">No Data to show</span>";
    }
    $(data).html(content);
}

    }
    