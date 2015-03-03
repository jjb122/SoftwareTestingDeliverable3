Feature: Change the quantity of item that is in the cart

#3.1
Scenario: Change quantity of an item from 1 to 2
Given a cart with one item in it and customer is not logged into an Amazon account
When the customer changes the quantity from 1 to 2
Then the selected item’s quantity shall show 2
And the user’s subtotal shall increase by the cost of the item

#3.2
Scenario: 2 items in cart, change quantity of the first item from 1 to 2
Given a cart with at least two unique items and customer is not logged into an Amazon account
When the customer changes the quantity of an item from 1 to 2
Then the selected item’s quantity shall show 2
And the user’s subtotal shall increase by the cost of the item

#3.3
Scenario: Change quantity of an item from 1 to 2 while logged in
Given a cart with one item in it and customer does have an amazon account
When the customer changes the quantity from 1 to 2
Then the selected item’s quantity shall show 2
And the user’s subtotal shall increase by the cost of the item

#3.4
Scenario: 2 items in cart, change quantity of the first item from 1 to 2 while logged in
Given a cart with at least two unique items and customer does have an amazon account
When the customer changes the quantity of an item from 1 to 2
Then the selected item shall show a quantity of 2
And the user’s subtotal shall increase by the cost of the item