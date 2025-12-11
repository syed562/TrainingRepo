package com.app.repo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.models.Flight;
import com.app.models.FlightUploadResult;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Service
public class FlightService {

	@Autowired
private FlightRepo fr;
	private static final int BATCH_SIZE = 50;

	private String validateFlight(Flight flight) {

	    List<String> errors = new ArrayList<>();

	    if (flight.getFlightNumber() == null || flight.getFlightNumber().isBlank()) {
	        errors.add("Flight number is missing");
	    }

	    if (flight.getFromPlace() == null || flight.getFromPlace().isBlank()) {
	        errors.add("From place is missing");
	    }

	    if (flight.getToPlace() == null || flight.getToPlace().isBlank()) {
	        errors.add("To place is missing");
	    }

	    if (flight.getDepartureTime() == null) {
	        errors.add("Departure time is missing");
	    }

	    if (flight.getArrivalTime() == null) {
	        errors.add("Arrival time is missing");
	    }

	    if (flight.getPrice() == null || flight.getPrice() <= 0) {
	        errors.add("Invalid or missing price");
	    }

	    if (flight.getAvailableSeats() <= 0) {
	        errors.add("Available seats must be > 0");
	    }

	    
	    if (errors.isEmpty()) return null;

	    
	    return String.join(", ", errors);
	}

	public FlightUploadResult  uploadFlights(MultipartFile file) throws Exception, Exception {
		
		
		ObjectMapper mapper=new ObjectMapper();
		List<Flight>flights=mapper.readValue(file.getInputStream(),new TypeReference<List<Flight>>() {
		});
		FlightUploadResult res=new FlightUploadResult();
		for(Flight f:flights) {
			try {
				if(fr.existsByFlightNumber(f.getFlightNumber())) {
				res.addError("Duplicate: "+f.getFlightNumber());
				continue;
			}
			   if (f.getPrice() <= 0) {
                   res.addError("Invalid Price for " + f.getFlightNumber());
                   continue;
               }
			   fr.saveAll(flights);
			   res.addSuccess("Uploaded: " + f.getFlightNumber());
		}catch(Exception e) {
			res.addError("Error saving " + f.getFlightNumber() + ": " + e.getMessage());
		}
		
	 
	}
		return res;
}
	
	public FlightUploadResult uploadFlightsBatch(MultipartFile file) throws IOException {

	    ObjectMapper mapper = new ObjectMapper();

	    List<Flight> flights = mapper.readValue(
	            file.getInputStream(),
	            new TypeReference<List<Flight>>() {}
	    );

	    FlightUploadResult result = new FlightUploadResult();
	    List<Flight> validBatch = new ArrayList<>();

	    int index = 0;

	    for (Flight flight : flights) {
	        index++;

	        // Validate
	        String error = validateFlight(flight);
	        if (error != null) {
	            result.addError("Row " + index + " (" + flight.getFlightNumber() + "): " + error);
	            continue;
	        }

	      
	        if (fr.existsByFlightNumber(flight.getFlightNumber())) {
	            result.addError("Row " + index + ": Duplicate flight number " + flight.getFlightNumber());
	            continue;
	        }

	        validBatch.add(flight);

	      
	        if (validBatch.size() == BATCH_SIZE) {
	            fr.saveAll(validBatch);
	            validBatch.forEach(f -> result.addSuccess("Saved: " + f.getFlightNumber()));
	            validBatch.clear();
	        }
	    }

	   
	    if (!validBatch.isEmpty()) {
	        fr.saveAll(validBatch);
	        validBatch.forEach(f -> result.addSuccess("Saved: " + f.getFlightNumber()));
	    }

	    return result;
	}

	
	
	
	
}