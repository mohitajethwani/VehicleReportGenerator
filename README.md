# VehicleReportGenerator
Application that generates vehicle information for a car dealer

### Problem Statement
Pretend the economy is terrible for software folks and you work for a shady car dealership. Let's say you have a static collection of vehicles in our inventory. You have trucks, cars, motorcycles and bicycles in the collection of vehicles. Each vehicle has a VIN (Vehicle Identifier Number), brand, color, engine type (Gas, Electric, Hybrid, None), price, year (and any other attributes you care to add.)
 
1. On Friday afternoon your boss, knowing that you have big plans of watching the X-Files that night, saunters up and asked you to generate some reports for him. He wants you to print out the list of vehicles by price.

2. Just as you think you think you're done for the afternoon, your boss says he wants another report of vehicles. This time he wants a report generated that shows average cost per car type sorted by price. Knowing your boss, you know he's going to ask for average cost per brand, per engine type and per color next, so build that out.

3. Build a REST web service in Spring Boot (spring.io) that returns that information above.

4. Summarize what happens in season 1 of the X-Files. (Just kidding)
 
#### Vehicles
Type, VIN, brand, color, engine type, price, year<br />
Truck, 1234, GMC, Red, Gas, 24000, 2016<br />
Car, 1235, Toyota, Green, Hybrid, 27000, 2015<br />
Car, 1236, Toyota, Red, Gas, 19000, 2015<br />
Car, 1237, Toyota, Blue, Gas, 21000, 2014<br />
Motorcycle, 1238, Honda, Blue, Hybrid, 17000, 2016<br />
Truck, 1239, Honda, Blue, Hybrid, 17000, 2016<br />
Car, 1240, Hyundai, Red, Gas, 17000, 2014<br />
Car, 1241, Tesla, Blue, Electric, 85000, 2016<br />
Bicycle, 1242, Cervelo, White, None, 2000, 2015<br />
Bicycle, 1243, Huffy, White, None, 150, 2014<br />
Bicycle, 1244, Trek, Orange, None, 1200, 2016<br />
Car, 1245, Hyundai, White, Electric, 25000, 2009<br />
Car, 1246,Ford Pinto,Light Blue,Gas,1000,1984<br />
Car, 1247,Chevy Vega,Dark Blue,Gas,500,1981<br />
Car, 1248,AMC Hornet,Tan,Gas,100,1977<br />
Car, 1249,Yugo,Gray,10, 1985<br />
Car, 1250, Bugatti Veyron, Black/Red, 2500000,2016<br />
Car, 1251,Tesla model-S,Red,84000, 2016<br />
Car, 1252, Honda, Blue, Gas, 5000, 2002<br />
Car, 1253, Honda, Silver, Gas, 13000, 2015<br />
Car, 1254, Hyundai, Gold, Gas, 13000, 2014<br />
Car, 1255, Subaru, Blue, Gas, 13000, 2014<br />
<br />
### Overview of the code

The dataset provided had missing values for some attributes. The data was preprocessed by adding values "None" in place of the missing values.<br />
The file vehicle.txt is read by the program which generates reports with the following information:
1. List of vehicles sorted by price
2. List of average cost per type sorted by price
3. List of average cost per brand sorted by price
4. List of average cost per color sorted by price
5. List of average cost per engine type sorted by price<br />

The above information was obtained by creating a REST service in Spring Boot for which JDK 1.8 was used. The service was deployed on Tomcat 8 on HTTP port 8082.<br /><br />
The project BCHProj needs to be run as a spring boot application.<br /><br />
The service returns a JSON response.<br />

#### Implementation details
The file vehicles.txt is read line by line and tokens are obtained by splitting each line based on ",". Vehicles is a class with the getters and setters of the vehicle attributes- VIN, type, brand, color, engine type, price and year.<br /><br />
To list vehicles by price, a TreeMap was used which stores the key value pair sorted by the natural ordering of the keys. The vehicle price is the key and the value is an ArrayList of VIN (since there can be multiple vehicles with the same price). To print the list an iterator iterates over the TreeMap and prints every key and value pair <price, VIN>.<br /><br />
To list the average cost of vehicles by type sorted by price, a HashMap is used to store the type of the vehicle as the key and an ArrayList with the price of all vehicles having the same type.<br /><br />
The average cost per type is then calculated by iterating over the HashMap and then a TreeMap is used to store the average cost and the type of the vehicle. Average cost is the key and the type is the value. The TreeMap is then read to print the output.<br /><br />
The average cost of vehicles per brand, color and engine type sorted by price is listed in the similar way.<br /><br />

### Screenshots of the output

