package jtech.shopzone.controller.dal.bean;
// Generated Mar 16, 2018 2:19:44 PM by Hibernate Tools 4.3.1



/**
 * UserProductsId generated by hbm2java
 */
public class UserProductsId  implements java.io.Serializable {


     private int userId;
     private int productId;

    public UserProductsId() {
    }

    public UserProductsId(int userId, int productId) {
       this.userId = userId;
       this.productId = productId;
    }
   
    public int getUserId() {
        return this.userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getProductId() {
        return this.productId;
    }
    
    public void setProductId(int productId) {
        this.productId = productId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof UserProductsId) ) return false;
		 UserProductsId castOther = ( UserProductsId ) other; 
         
		 return (this.getUserId()==castOther.getUserId())
 && (this.getProductId()==castOther.getProductId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getUserId();
         result = 37 * result + this.getProductId();
         return result;
   }   


}

