# 24_summer_oop_final_project
Title: Foodiego delivery system
Team member details: Haotian Lei (hl4385), Liam Tang (ct2960), Yukying Chong (yc5099)
Description: Foodiego is a Java GUI-based food delivery system. It is designed to provide efficient food ordering and delivery experiences. The application will connect customers with restaurants, 
allowing them to browse menus, place orders, and receive notifications about their order status. It will cater to users seeking convenience and variety in food choices, along with reliable delivery 
services.
Proposed features: 
There will be three actors: customers, business, drivers.
For customers:
Restaurant and menu browsing: the system will list all available restaurants, and the customers are able to browse menus with detailed descriptions and pricing.
Restaurant Selection Data Types:
String for location and cuisine type.
List<Restaurant> for the list of restaurants (where Restaurant is a custom class with
String fields for name, address, cuisine type, and ratings).
Binary Storage: 
Serialized objects of Restaurant lists based on customer location or
preferences.
Menu Browsing Data Types:
            List<MenuItem> for menu items (where MenuItem is a custom class with String for
 name, double for price, String for description, and String for image file path).
            List<OrderItem> for items in the cart (where OrderItem is a custom class with
references to MenuItem and quantity as int).
Binary Storage: 
Serialized objects of Order containing List<OrderItem> linked to customer and restaurant details.

Order placement: customers can select items from restaurants’ menu and add them to a virtual cart. Payment will be processed after placing orders.
Order Review Data Types:
List<OrderItem> for items being reviewed.
String for promo codes.
String for payment method.
Binary Storage:
Serialized objects of Order containing all reviewed details and payment status.

Order status notifications: customers will receive notifications for significant events, such as order acceptance, out-for-delivery status, and delivery completion
             Order Status Data Types:
                           String for status updates (e.g., “Preparing,” “Out for Delivery”).
                           Date for timestamps of each status change.
             Binary Storage: 
                           Serialized objects of Order updated in real-time.

For business:
Menu management: businesses can update and revise their menu
Menu Management Data Types:
List<MenuItem> for the menu (as described above).
Binary Storage: 
Serialized objects of MenuItem lists linked to Restaurant objects.

Order management: businesses are able to confirm or cancel orders from customers and update the order status from placed to confirmed and from preparing to ready for pickup
Order Management Data Types:
List<Order> for incoming orders.
String for order status (e.g., “Accepted,” “Rejected”).
Binary Storage: 
Serialized Order objects linked to the Restaurant object.

For drivers:
Delivery tasks management: drivers can browse the real-time delivery tasks posted with details on dashboard and decide if they want to accept or not.
Order Acceptance Data Types:
List<Order> for available orders.
String for acceptance status.
Binary Storage: 
Serialized Order objects linked to Driver objects.



