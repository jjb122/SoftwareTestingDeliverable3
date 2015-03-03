Feature: Remove item from cart

#2.1
Scenario: 0 items in the cart, check to see if the customer can delete an item
Given an empty cart and customer is not logged into an Amazon account
When the customer views their cart
Then the option to delete an item shall not be present

#2.2
Scenario: 1 item in the cart, removes the first item in the cart
Given a cart with one item in it and customer is not logged into an Amazon account
When the customer clicks “Delete” on an item in their cart
Then the item shall be removed from the user’s cart 
And the user’s cart shall be empty
And the user’s subtotal shall be $0.00

#2.3
Scenario: 2 unique items in the cart, removes 1 item while logged in 
Given a cart with at least two unique items and customer is not logged into an Amazon account
When the customer clicks “Delete” on an item in their cart
Then the selected item shall be removed from the user’s cart
And the user’s subtotal shall decrease by the cost of the item

#2.4
Scenario: 0 items in the cart, check to see if the customer can delete an item while logged in
Given an empty cart and customer has an amazon account
When the customer views their cart
Then the selected option to delete an item shall not be present

#2.5
Scenario: 1 item in the cart, removes 1 item while logged in 
Given a cart with one item in it and customer has an amazon account
When the customer clicks “Delete” on an item in their cart
Then the selected item shall be removed from the user’s cart 
And the user’s cart shall be empty
And the user’s subtotal shall be $0.00

#2.6
Scenario: 2 unique items in the cart, removes 1 item while logged in 
Given a cart with at least two unique items and customer has an amazon account
When the customer clicks “Delete” on an item in their cart
Then the selected item shall be removed from the user’s cart
And the user’s subtotal shall decrease by the cost of the item

