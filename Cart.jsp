<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>


<%@include file="header.jsp" %>
<c:if test="${empty user}">
    <c:redirect url="/Login.jsp" />
</c:if>
<%
float sum=0;
%>
<div class="container">
  <h2>Your <c:if test="${empty cart}">WishList</c:if><c:if test="${not empty cart}">Cart</c:if></h2>
  <div class="panel panel-default">
    <div class="panel-heading">Your product has been added to <c:if test="${not empty cart}">Cart</c:if>
    <c:if test="${not empty cart.getCartItems()}">
    	<a href="deleteCart/${cart.id}/">
    		<button type="button" class="btn btn-danger" style="float: right;">
    			<i class="fa fa-trash"> Clear All </i>
   			</button>
		</a>
    </c:if>
    <c:if test="${not empty wishes.getListItems()}">
    	<a href="delete/${wishes.id}/">
    		<button type="button" class="btn btn-danger" style="float: right;">
    			<i class="fa fa-trash"> Clear All </i>
   			</button>
		</a>
    </c:if>
    </div>
    <div class="panel-body">
    
    	<c:if test="${empty cart.getCartItems() and empty wishes.getListItems()}">
				<i>Your <c:if test="${empty cart}">WishList</c:if><c:if test="${not empty cart}">Cart</c:if> is Empty</i>
    	</c:if>
    	
    	<c:forEach var="c" items="${cart.getCartItems()}">
    	<div class="row">
    		<div class="col-lg-4">
    		<img src="images/product/${c.product.id}.jpg" style="float: left;max-width: 100px;max-height: 100px;"/>
			${c.product.name}</div>
			<div class="col-lg-4">
				<form action="updateCart/${c.id}/${c.product.id}">
					<input type="number" value="${c.quantity}" name="q" min="1" max="5"/>
					<button class="fa fa-refresh">Update</button>
				</form>
			</div>
			<div class="col-sm-2">$ ${c.product.price}</div>
			<div class="col-sm-2">
				<a href="delete/${c.id}/${c.product.id}"><i class="fa fa-trash fa-stack-2x"></i></a>
			</div>
    	</div>
    	<p style="visibility: hidden;">${sum=sum+c.product.price}</p>
    	</c:forEach>	
    	<c:forEach var="c" items="${wishes.getListItems()}">
    	<div class="row">
    		<div class="col-lg-4">
    		<img src="images/product/${c.wishGroup.product.productId}.jpg" style="float: left;max-width: 100px;max-height: 100px;"/>
			${c.wishGroup.product.name}</div>
			<div class="col-lg-4">
			</div>
			<div class="col-sm-2">
				<a href="delete/${c.wishGroup.wishList.id}/${c.wishGroup.product.productId}"><i class="fa fa-trash fa-stack-2x"></i></a>
			</div>
    	</div>
    	</c:forEach>
    </div>
    <div class="panel-footer">
    <c:if test="${not empty cart.getCartItems()}">
    	<div class="row">
			<div class="col-lg-8" style="font-size: 30px;">Total</div>
			<div class="col-sm-4" style="font-size: 30px;">$ ${sum}</div>
    	</div>
    	<div class="row">
			<div class="col-lg-8"></div>
			<div class="col-sm-4">
				<a href="ship.jsp?c=${cart.id}" class="btn btn-info" role="button">Proceed to Shipment</a>
			</div>
    	</div>
   	</c:if>
    </div>
    </div>
</div>
  <c:if test="">
      <sql:setDataSource var = "cartDs" driver = "sun.java.jdbc.odbc.JdbcOdbcDriver" url = "jdbc:odbc:Eshp"/>
      <sql:update var="cartUpdate" dataSource="cartDs">
          UPDATE CartItem
          SET quantity = (select quantity from cartitem where id=1)+1
          WHERE cartitem where id=1
      </sql:update>  
  </c:if>     
  <c:if test="">
      <sql:update var="cartItemDelete" dataSource="DataConnector">
          DELETE FROM cartitem
          WHERE cartitem where id=2
      </sql:update>
  </c:if>
<%@include file="footer.jsp" %>    
</body>
</html>
