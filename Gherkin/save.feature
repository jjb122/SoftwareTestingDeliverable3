Feature: Save item for later

#4.1
Scenario: Save the first item in the cart for later 
Given a cart with one item in it and customer is not logged into an Amazon account
When the customer clicks on “Save for later”
Then the selected item will move to the “Save for Later” list
And the selected will be removed from user’s shopping cart
And the user’s subtotal shall be $0.00

#4.1
Scenario: 2 items in cart, save the first item in the cart for later
Given a cart with at least two unique items and customer is not logged into an Amazon account
When the customer clicks on “Save for later”  on an item
Then the selected item will move to the “Save for Later” list
And the selected will be removed from user’s shopping cart
And the user’s subtotal shall decrease by the cost of the item

#4.3
Scenario: Save the first item in the cart for later while logged in
Given a cart with one item in it and customer does have an amazon account
When the customer clicks on “Save for later”  on an item
Then the selected item will move to the “Save for Later” list
And the selected will be removed from user’s shopping cart
And the user’s subtotal shall be $0.00

#4.4
Scenario: 2 items in cart, save the first item in the cart for later
Given a cart with at least two unique items and customer does have an amazon account
When the customer clicks on “Save for later” on an item
Then the selected item will move to the “Save for Later” list
And the selected will be removed from user’s shopping cart
And the user’s subtotal shall decrease by the cost of the item



