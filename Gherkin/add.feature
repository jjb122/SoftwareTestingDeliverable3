Feature: Add item to cart
	
#1.1
Scenario: Add 1 item to cart
Given an empty cart and customer is not logged into an Amazon account
When the customer clicks “Add to Cart”
Then the item shall be added to the user’s cart
And the user’s subtotal shall increase by the cost of the item

#1.2
Scenario: Add 2 items to cart
Given a cart with one item in it and customer is not logged into an Amazon account
When the customer clicks “Add to Cart”
Then the item shall be added to the user’s cart
And the user’s subtotal shall increase by the cost of the item

#1.3
Scenario: Add 3 items to cart
Given a cart with at least two unique items and customer is not logged into an Amazon account
When the customer clicks “Add to Cart”
Then the item shall be added to the user’s cart
And the user’s subtotal shall increase by the cost of the item

#1.4
Scenario: Add 1 item to cart while logged in
Given an empty cart and customer has an amazon account
When the customer clicks “Add to Cart”
Then the item shall be added to the user’s cart
And the user’s subtotal shall increase by the cost of the item

#1.5
Scenario: Add 2 items to cart while logged in
Given a cart with one item in it and customer has an amazon account
When the customer clicks “Add to Cart”
Then the item shall be added to the user’s cart
And the user’s subtotal shall increase by the cost of the item

#1.6
Scenario: Add 3 items to cart while logged in
Given a cart with at least two unique items and customer has an amazon account
When the customer clicks “Add to Cart”
Then the item shall be added to the user’s cart
And the user’s subtotal shall increase by the cost of the item

