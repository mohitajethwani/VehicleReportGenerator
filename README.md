# VehicleReportGenerator
Application that generates vehicle information for a car dealer

### Problem Statement
Pretend the economy is terrible for software folks and you work for a shady car dealership. Let's say you have a static collection of vehicles in our inventory. You have trucks, cars, motorcycles and bicycles in the collection of vehicles. Each vehicle has a VIN (Vehicle Identifier Number), brand, color, engine type (Gas, Electric, Hybrid, None), price, year (and any other attributes you care to add.)
 
1. On Friday afternoon your boss, knowing that you have big plans of watching the X-Files that night, saunters up and asked you to generate some reports for him. He wants you to print out the list of vehicles by price.

2. Just as you think you think you're done for the afternoon, your boss says he wants another report of vehicles. This time he wants a report generated that shows average cost per car type sorted by price. Knowing your boss, you know he's going to ask for average cost per brand, per engine type and per color next, so build that out.

3. Build a REST web service in Spring Boot (spring.io) that returns that information above.

4. Summarize what happens in season 1 of the X-Files. (Just kidding)
 
Vehicles
Type, VIN, brand, color, engine type, price, year
Truck, 1234, GMC, Red, Gas, 24000, 2016
Car, 1235, Toyota, Green, Hybrid, 27000, 2015
Car, 1236, Toyota, Red, Gas, 19000, 2015
Car, 1237, Toyota, Blue, Gas, 21000, 2014
Motorcycle, 1238, Honda, Blue, Hybrid, 17000, 2016
Truck, 1239, Honda, Blue, Hybrid, 17000, 2016
Car, 1240, Hyundai, Red, Gas, 17000, 2014
Car, 1241, Tesla, Blue, Electric, 85000, 2016
Bicycle, 1242, Cervelo, White, None, 2000, 2015
Bicycle, 1243, Huffy, White, None, 150, 2014
Bicycle, 1244, Trek, Orange, None, 1200, 2016
Car, 1245, Hyundai, White, Electric, 25000, 2009
Car, 1246,Ford Pinto,Light Blue,Gas,1000,1984
Car, 1247,Chevy Vega,Dark Blue,Gas,500,1981
Car, 1248,AMC Hornet,Tan,Gas,100,1977
Car, 1249,Yugo,Gray,10, 1985
Car, 1250, Bugatti Veyron, Black/Red, 2500000,2016
Car, 1251,Tesla model-S,Red,84000, 2016
Car, 1252, Honda, Blue, Gas, 5000, 2002
Car, 1253, Honda, Silver, Gas, 13000, 2015
Car, 1254, Hyundai, Gold, Gas, 13000, 2014
Car, 1255, Subaru, Blue, Gas, 13000, 2014
