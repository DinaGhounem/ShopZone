/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function callback(response, statusTxt, xhr)
{
    if (statusTxt == "success") {
        //object contain array of products
        var content="";
        var height=0;
        object = JSON.parse(response);
        for( i=0;i<object.length;i++){
            content+="<div class=\"product-item men\" style='float:left;margin:2%'>"+
							"<div class=\"product discount product_filter\">"+
								"<div class=\"product_image\">"+
									"<img src=\"img/"+object[i].img+"\" alt=\"\">"+
								"</div>"+
								"<div class=\"favorite favorite_left\"></div>"+
								"<div class=\"product_bubble product_bubble_right product_bubble_red d-flex flex-column align-items-center\"><span>-$20</span></div>"+
								"<div class=\"product_info\">"+
									"<h6 class=\"product_name\"><a href=\"single.html\">"+object[i].productName+"("+object[i].description+")</a></h6>"+
									"<div class=\"product_price\">$"+object[i].price+"<span>$590.00</span></div>"+
								"</div>"+
							"</div>"+
							"<div class=\"red_button add_to_cart_button\"><a href=\"#\">add to cart</a></div>"+
                                                    "</div>";
                                            if(i%4==0){
                                                height+=400;
                                            }
        }
        
        $(products).html(content);
        $(products).css("height",height+"px");
        
        if(object[0]!=null)
        console.log(object[0].productName,object[0].productId,object[0].price,object[0].description,object[0].img,object[0].quantity,object[0].categoryId);

    }}
    function getProducts(){
      
        $.get("ShowProductServlet", callback);

      
    }
