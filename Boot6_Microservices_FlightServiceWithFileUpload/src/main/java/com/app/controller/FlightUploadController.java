package com.app.controller;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.app.dto.FileInfo;
import com.app.dto.InventoryRequest;
import com.app.dto.ResponseMessage;
import com.app.repo.FlightRepo;
import com.app.service.FlightService;
import com.app.storage.FilesStorageService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@RestController
@CrossOrigin("*")
public class FlightUploadController {

    @Autowired
    FilesStorageService storageService;

    @Autowired
    FlightRepo flightRepo;
    
    
    @Autowired
    FlightService flightService;
    @PostMapping("/uploadFlights")
    public ResponseEntity<ResponseMessage> uploadFlights(@RequestParam("file") MultipartFile file) {
        String message = "";

        try {
            // store file in uploads
            storageService.save(file);

            // Read JSON into InventoryRequest list
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            InputStream is = file.getInputStream();

            List<InventoryRequest> list =
                    mapper.readValue(is, new TypeReference<List<InventoryRequest>>() {});

            // Insert into DB using your existing logic
            for (InventoryRequest req : list) {
                flightService.addFlightInventory(req);
            }

            message = "Successfully uploaded: " + list.size() + " flights";
            return ResponseEntity.ok(new ResponseMessage(message));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseMessage("Could not process file: " + e.getMessage()));
        }
    }


    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> listFiles() {
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FlightUploadController.class, "getFile", filename)
                    .build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
