package service;

import java.io.*;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import model.Vehicle;

/**
 * This is the REST Service class. It returns the user responses as per the
 * request. The request is a URL
 * 
 */

@Service
public class GenerateReportService {

	static TreeMap<Double, ArrayList<String>> priceVinMapping = new TreeMap<Double, ArrayList<String>>();			//stores <price,VIN> as <key,value>
	static HashMap<String, ArrayList<Double>> carTypePriceMapping = new HashMap<String, ArrayList<Double>>();		//stores <type, arrayList of vehicle price> as <key,value>
	static HashMap<String, ArrayList<Double>> brandPriceMapping = new HashMap<String, ArrayList<Double>>();			//stores <brand, arrayList of vehicle price> as <key,value>
	static HashMap<String, ArrayList<Double>> colorPriceMapping = new HashMap<String, ArrayList<Double>>();			//stores <color, arrayList of vehicle price> as <key,value>
	static HashMap<String, ArrayList<Double>> engineTypePriceMapping = new HashMap<String, ArrayList<Double>>();	//stores <engineType, arrayList of vehicle price> as <key,value>
	static ArrayList<String> values = new ArrayList<String>();
	static ArrayList<Double> prices = new ArrayList<Double>();
	static TreeMap<Double, ArrayList<String>> tm = new TreeMap<Double, ArrayList<String>>();						//stores <avgCost, type/brand/color/engType> as <key,value>
	static double averageCost = 0;

	
	/**
	 * The constructor reads the vehicle.txt line by line and calls the parser
	 */
	public GenerateReportService() throws IOException {

		File listOfVehicles = new File("vehicles.txt");
		BufferedReader br = new BufferedReader(new FileReader(listOfVehicles));
		String line = br.readLine();
		line = br.readLine();
		while (line != null) {
			parse(line);
			line = br.readLine();
		}
	}
	
	
	/**
	 * The method parses the string to form tokens by splitting the line by ","
	 * Creates objects and sets their value
	 */

	public static void parse(String line) {
		String tokens[] = line.split(",");
		Vehicle v = new Vehicle();
		v.setType(tokens[0].trim());
		v.setVIN(tokens[1].trim());
		v.setBrand(tokens[2].trim());
		v.setColor(tokens[3].trim());
		v.setEngineType(tokens[4].trim());
		v.setPrice(Double.parseDouble(tokens[5].trim()));
		v.setYear(Integer.parseInt(tokens[6].trim()));

		addToTreeMapToListSortedByPrice(v.getPrice(), v.getVIN());
		addToHashMapForPricePerCarType(v.getType(), v.getPrice());
		addToHashMapForPricePerBrand(v.getBrand(), v.getPrice());
		addToHashMapForPricePerColor(v.getColor(), v.getPrice());
		addToHashMapForPricePerEngineType(v.getEngineType(), v.getPrice());
	}

	/**
	 * The method stores the vehicle identification number and the price in a
	 * treeMap in increasing order of price. There may be multiple vehicles
	 * with the same price.
	 */
	
	public static void addToTreeMapToListSortedByPrice(double price, String VIN) {
		values = new ArrayList<String>();
		if (priceVinMapping.containsKey(price)) {
			values = priceVinMapping.get(price);
			values.add(VIN);
			priceVinMapping.put(price, values);
		}

		//multiple vehicles with same price
		else if (!priceVinMapping.containsKey(price)) {
			values.add(VIN);
			priceVinMapping.put(price, values);
		}
	}

	
	/**
	 * The method stores the vehicle type and the price in a
	 * HashMap. There may be multiple vehicles
	 * with the same type.
	 */
	
	public static void addToHashMapForPricePerCarType(String carType, double price) {
		prices = new ArrayList<Double>();
		if (carTypePriceMapping.containsKey(carType)) {
			prices = carTypePriceMapping.get(carType);
			prices.add(price);
			carTypePriceMapping.put(carType, prices);
		}

		else if (!carTypePriceMapping.containsKey(carType)) {
			prices.add(price);
			carTypePriceMapping.put(carType, prices);
		}
	}

	
	/**
	 * The method stores the vehicle brand and the price in a
	 * HashMap. There may be multiple vehicles
	 * with the same brand.
	 */
	
	public static void addToHashMapForPricePerBrand(String brand, double price) {

		prices = new ArrayList<Double>();
		if (brandPriceMapping.containsKey(brand)) {
			prices = brandPriceMapping.get(brand);
			prices.add(price);
			brandPriceMapping.put(brand, prices);
		}

		else if (!brandPriceMapping.containsKey(brand)) {
			prices.add(price);
			brandPriceMapping.put(brand, prices);
		}
	}

	
	/**
	 * The method stores the vehicle color and the price in a
	 * HashMap. There may be multiple vehicles
	 * with the same color.
	 */
	
	public static void addToHashMapForPricePerColor(String color, double price) {

		prices = new ArrayList<Double>();
		if (colorPriceMapping.containsKey(color)) {
			prices = colorPriceMapping.get(color);
			prices.add(price);
			colorPriceMapping.put(color, prices);
		}

		else if (!colorPriceMapping.containsKey(color)) {
			prices.add(price);
			colorPriceMapping.put(color, prices);
		}
	}

	
	/**
	 * The method stores the vehicle engine type and the price in a
	 * HashMap. There may be multiple vehicles
	 * with the same engine type.
	 */
	
	public static void addToHashMapForPricePerEngineType(String engineType, double price) {

		prices = new ArrayList<Double>();
		if (engineTypePriceMapping.containsKey(engineType)) {
			prices = engineTypePriceMapping.get(engineType);
			prices.add(price);
			engineTypePriceMapping.put(engineType, prices);
		}

		else if (!engineTypePriceMapping.containsKey(engineType)) {
			prices.add(price);
			engineTypePriceMapping.put(engineType, prices);
		}
	}

	
	/**
	 * The method returns a json array with vehicles sorted by price
	 */
	
	public static JSONArray getVehiclesByPrice() {
		JSONArray vehiclesByPrice = new JSONArray();
		Iterator<Map.Entry<Double, ArrayList<String>>> it = priceVinMapping.entrySet().iterator();
		Map.Entry<Double, ArrayList<String>> entry;
		while (it.hasNext()) {
			entry = it.next();
			values = entry.getValue();
			for (int i = 0; i < values.size(); i++) {
				JSONObject objectEntry = new JSONObject();
				objectEntry.put("VIN", values.get(i));
				objectEntry.put("cost", entry.getKey());
				vehiclesByPrice.add(objectEntry);
			}
		}
		return vehiclesByPrice;
	}

	
	/**
	 * The method returns a json array with the average cost of vehicles 
	 * per type sorted by price
	 */
	
	public static JSONArray getAverageCostPerType() {
		JSONArray avgCostPerType = new JSONArray();
		tm = new TreeMap<Double, ArrayList<String>>();
		prices = new ArrayList<Double>();
		Iterator<Map.Entry<String, ArrayList<Double>>> it = carTypePriceMapping.entrySet().iterator();
		Map.Entry<String, ArrayList<Double>> entry;

		while (it.hasNext()) {
			averageCost = 0;
			values = new ArrayList<String>();
			entry = it.next();
			prices = entry.getValue();
			for (int i = 0; i < prices.size(); i++) {
				averageCost += prices.get(i);
			}
			averageCost = averageCost / prices.size();

			if (tm.containsKey(averageCost)) {
				values = tm.get(averageCost);
				values.add(entry.getKey());
				tm.put(averageCost, values);
			}

			else {
				values.add(entry.getKey());
				tm.put(averageCost, values);
			}
		}

		Iterator<Map.Entry<Double, ArrayList<String>>> it1 = tm.entrySet().iterator();
		Map.Entry<Double, ArrayList<String>> entry1;
		while (it1.hasNext()) {
			entry1 = it1.next();
			values = entry1.getValue();
			for (int i = 0; i < values.size(); i++) {
				JSONObject objectEntry = new JSONObject();
				objectEntry.put("type", values.get(i));
				objectEntry.put("cost", entry1.getKey());
				avgCostPerType.add(objectEntry);
			}
		}
		return avgCostPerType;
	}

	
	/**
	 * The method returns a json array with the average cost of vehicles 
	 * per brand sorted by price
	 */
	
	public static JSONArray getAverageCostPerBrand() {
		JSONArray avgCostPerBrand = new JSONArray();
		tm = new TreeMap<Double, ArrayList<String>>();
		prices = new ArrayList<Double>();
		Iterator<Map.Entry<String, ArrayList<Double>>> it = brandPriceMapping.entrySet().iterator();
		Map.Entry<String, ArrayList<Double>> entry;

		while (it.hasNext()) {
			averageCost = 0;
			values = new ArrayList<String>();
			entry = it.next();
			prices = entry.getValue();
			for (int i = 0; i < prices.size(); i++) {
				averageCost += prices.get(i);
			}
			averageCost = averageCost / prices.size();

			if (tm.containsKey(averageCost)) {
				values = tm.get(averageCost);
				values.add(entry.getKey());
				tm.put(averageCost, values);
			}

			else {
				values.add(entry.getKey());
				tm.put(averageCost, values);
			}
		}

		Iterator<Map.Entry<Double, ArrayList<String>>> it1 = tm.entrySet().iterator();
		Map.Entry<Double, ArrayList<String>> entry1;
		while (it1.hasNext()) {
			entry1 = it1.next();
			values = entry1.getValue();
			for (int i = 0; i < values.size(); i++) {
				JSONObject objectEntry = new JSONObject();
				objectEntry.put("brand", values.get(i));
				objectEntry.put("cost", entry1.getKey());
				avgCostPerBrand.add(objectEntry);
			}
		}
		return avgCostPerBrand;
	}

	
	/**
	 * The method returns a json array with the average cost of vehicles 
	 * per color sorted by price
	 */
	
	public static JSONArray getAverageCostPerColor() {
		JSONArray avgCostPerColor = new JSONArray();
		tm = new TreeMap<Double, ArrayList<String>>();
		prices = new ArrayList<Double>();
		Iterator<Map.Entry<String, ArrayList<Double>>> it = colorPriceMapping.entrySet().iterator();
		Map.Entry<String, ArrayList<Double>> entry;

		while (it.hasNext()) {
			averageCost = 0;
			values = new ArrayList<String>();
			entry = it.next();
			prices = entry.getValue();
			for (int i = 0; i < prices.size(); i++) {
				averageCost += prices.get(i);
			}
			averageCost = averageCost / prices.size();

			if (tm.containsKey(averageCost)) {
				values = tm.get(averageCost);
				values.add(entry.getKey());
				tm.put(averageCost, values);
			}

			else {
				values.add(entry.getKey());
				tm.put(averageCost, values);
			}
		}

		Iterator<Map.Entry<Double, ArrayList<String>>> it1 = tm.entrySet().iterator();
		Map.Entry<Double, ArrayList<String>> entry1;
		while (it1.hasNext()) {
			entry1 = it1.next();
			values = entry1.getValue();
			for (int i = 0; i < values.size(); i++) {
				JSONObject objectEntry = new JSONObject();
				objectEntry.put("color", values.get(i));
				objectEntry.put("cost", entry1.getKey());
				avgCostPerColor.add(objectEntry);
			}
		}
		return avgCostPerColor;
	}

	
	/**
	 * The method returns a json array with the average cost of vehicles 
	 * per engine type sorted by price
	 */
	
	public static JSONArray getAverageCostPerEngineType() {
		JSONArray avgCostPerEngineType = new JSONArray();
		tm = new TreeMap<Double, ArrayList<String>>();
		prices = new ArrayList<Double>();
		Iterator<Map.Entry<String, ArrayList<Double>>> it = engineTypePriceMapping.entrySet().iterator();
		Map.Entry<String, ArrayList<Double>> entry;

		while (it.hasNext()) {
			averageCost = 0;
			values = new ArrayList<String>();
			entry = it.next();
			prices = entry.getValue();
			for (int i = 0; i < prices.size(); i++) {
				averageCost += prices.get(i);
			}
			averageCost = averageCost / prices.size();

			if (tm.containsKey(averageCost)) {
				values = tm.get(averageCost);
				values.add(entry.getKey());
				tm.put(averageCost, values);
			}

			else {
				values.add(entry.getKey());
				tm.put(averageCost, values);
			}
		}

		Iterator<Map.Entry<Double, ArrayList<String>>> it1 = tm.entrySet().iterator();
		Map.Entry<Double, ArrayList<String>> entry1;
		while (it1.hasNext()) {
			entry1 = it1.next();
			values = entry1.getValue();
			for (int i = 0; i < values.size(); i++) {
				JSONObject objectEntry = new JSONObject();
				objectEntry.put("engType", values.get(i));
				objectEntry.put("cost", entry1.getKey());
				avgCostPerEngineType.add(objectEntry);
			}
		}
		return avgCostPerEngineType;
	}

}
