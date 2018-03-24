/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var selectedId=0;
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
                 content+= "</td><td class=\"cart_price\"><p id='totalPrice"+object[i].userId+"'>0$</p> </td></tr>";
                     showHistory(object[i].userId,showUserHistoryCallBack2);
                    

        }
        $(user_table).html(content);
    }}
function showUserProfile(userId){
    if(userId!=0){
    selectedId=userId;
    
 } 
 $(profile).addClass("active");
 
 if($(historySec).hasClass("active")){
 $(historySec).removeClass("active");
 }
   
     $.get("ShowUserToAdmin?userId="+selectedId,showUserProfileCallBack);  
}
function showUserProfileCallBack(response, statusTxt, xhr)
{
    if (statusTxt == "success") {
         var content = "";
        object = JSON.parse(response);
        if( object!=null) {
            
                content += "<div class=\"product-item men\" style='margin:5% 25% 0% -10%' >" +
                        "<div class=\"product discount product_filter\">" +
                        "<div class=\"product_image\">" +
                        "<img src=\"img/profile.png\" alt=\"\">" +
                        "</div>" ;
               
                content += "<div class=\"product_info\">" +
                        "<h6 class=\" headerColor\">" + object.firstName + " "+object.lastName+"<br/>(" + object.email + ")</h6>" +
                        //"<div class=\"product_name\"><span class=\"product_price\">Job : </span>" + object.job + "</div>"  +
                        "<div class=\"product_name\"><span class=\" headerColor\">Address : </span>" + object.address + "</div>" +
                        "<div class=\"product_name\"><span class=\" headerColor\">Birth date : </span>"+object.birthdate + "</div>" ;
                        if(object.interests !=null && object.interests.length>0){
                        content+="<div class=\"product_name\"><span class=\" headerColor\">Interests :</div>" ;
                        for(j=0;j<object.interests.length;j++){
                         content+="<span class=\"product_name\">"+object.interests[j].interestName+"</span>";
                         if(j!=object.interests.length-1){
                             content+=", ";
                         }
                        }
                    }
                        content+="</div>" +
                        "</div>" +
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
function showHistory2(){
     showHistory(selectedId,showUserHistoryCallBack);
     $(profile).removeClass("active");
    $(historySec).addClass("active");
   
}
function showHistory(userId,showUserHistoryCallBack){
    
     $.get("UserHistory?userId="+userId,showUserHistoryCallBack)
}
function showUserHistoryCallBack(response, statusTxt, xhr)
{
    if (statusTxt == "success") {
        object = JSON.parse(response);
          content="";
        if(object.length>0){
           
        content="<div class=\"table-responsive cart_info table-hover\" id=\"history_table\" style=\"width:100%\">"+
                                   " <table class=\"table table-condensed\" >"+
                                        "<thead> <tr class=\"cart_menu\">"+
                                               " <th class=\"image\">Product</th>"+
                                               " <th class=\"price\">Quantity</th>"+
                                                "<th class=\"total\">Date</th>"+
                                                "<th>Price</th></tr> </thead><tbody id=\"history_table\">";
                                            
                                        



        for(i=0;i<object.length;i++){
            content+=" <tr id=\"\" ><td class=\"cart_product\">"+object[i].productName;
                                    
                content+= "</td>  <td class=\"cart_description\">"+object[i].quantity;
                 content+= "</td>  <td class=\"cart_description\">"+object[i].date;
                     content+= "</td><td class=\"cart_price\"><p></p>"+ object[i].price+"</td></tr>";

        }
        content+="</tbody></table></div>";
                                       
                                    

                                
        
    }
    else{
        content="<span id=\"data2\">No Data to show</span>";
    }
    $(user_profile_section).html(content);
}

    }
    function showUserHistoryCallBack2(response, statusTxt, xhr)
{
    if (statusTxt == "success") {
        object = JSON.parse(response);
          content=0;
        if(object.length>0){
            for(i=0;i<object.length;i++){
            content+=object[i].price;
                                    
                
        }
     $("#totalPrice"+object[0].userId).html(content+"$");   
           
       
}


    }
}