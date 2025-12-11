package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.models.FlightUploadResult;
import com.app.repo.FlightService;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping("/upload")
    public ResponseEntity<FlightUploadResult> uploadFlights(@RequestParam("file") MultipartFile file) {
    	 try {
    	        FlightUploadResult result = flightService.uploadFlights(file);
    	        return ResponseEntity.ok(result);
    	    } catch (Exception e) {
    	        FlightUploadResult fail = new FlightUploadResult();
    	        fail.addError("Unexpected error: " + e.getMessage());
    	        return ResponseEntity.status(500).body(fail);
    	    }
    }
    
    @PostMapping(value="/upload/batch",consumes = MediaType.MULTIPART_FORM_DATA_VALUE
)
    public ResponseEntity<FlightUploadResult> uploadBatch(@RequestParam("file") MultipartFile file) {
        try {
            FlightUploadResult result = flightService.uploadFlightsBatch(file);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            FlightUploadResult errorResult = new FlightUploadResult();
            errorResult.addError("Unexpected error: " + e.getMessage());
            return ResponseEntity.status(500).body(errorResult);
        }
    }

}
