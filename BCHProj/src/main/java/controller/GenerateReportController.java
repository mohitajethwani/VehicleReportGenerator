package controller;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Vehicle;
import service.GenerateReportService;

/**
 * This is the controller class. It relays requests to the service class
 * 
 */

@RestController
@RequestMapping("/vehicles")
public class GenerateReportController {

	@Autowired
	GenerateReportService grs;

	//Request URL: http://localhost:8082/vehicles/byPrice
	
	@RequestMapping("/byPrice")
	public JSONArray getVehiclesByPrice() {
		return grs.getVehiclesByPrice();
	}

	
	//Request URL: http://localhost:8082/vehicles/perType
	
	@RequestMapping("/perType")
	public JSONArray getAverageCostPerType() {
		return grs.getAverageCostPerType();
	}

	//Request URL: http://localhost:8082/vehicles/perBrand
	
	@RequestMapping("/perBrand")
	public JSONArray getAverageCostPerBrand() {
		return grs.getAverageCostPerBrand();
	}

	//Request URL: http://localhost:8082/vehicles/perColor
	
	@RequestMapping("/perColor")
	public JSONArray getAverageCostPerColor() {
		return grs.getAverageCostPerColor();
	}

	//Request URL: http://localhost:8082/vehicles/perEngType
	
	@RequestMapping("/perEngType")
	public JSONArray getAverageCostPerEngineType() {
		return grs.getAverageCostPerEngineType();
	}
}
