package com.fixtab.app.controllers;

import com.fixtab.app.models.db.customers.TargetObjectModel;
import com.fixtab.app.models.requests.TargetObjectRequest;
import com.fixtab.app.models.responses.TargetObjectResponse;
import com.fixtab.app.services.interfaces.TargetObjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/targetObject")
public class TargetObjectController {

    private final TargetObjectService targetObjectService;

    @GetMapping("getAllTargetObjects")
    public List<TargetObjectModel> getAllTargetObjects() {
        return targetObjectService.getAllTargetObjects();
    }

    @GetMapping("getAllNotDeletedTargetObjects")
    public List<TargetObjectResponse> getAllNotDeletedTargetObjects() {
        return targetObjectService.getAllNotDeletedTargetObjects();
    }

    @PostMapping("createTargetObject")
    public ResponseEntity<?> createTargetObject(@RequestBody TargetObjectRequest targetObjectRequest) {
        targetObjectService.createTargetObject(targetObjectRequest);
        return new ResponseEntity<>("{\"Success\":\"TargetObject has been created!\"}",HttpStatus.OK);
    }

    @PutMapping("editTargetObject")
    public ResponseEntity<?> editTargetObject(@RequestBody TargetObjectRequest editTargetObjectRequest) {
        targetObjectService.editTargetObject(editTargetObjectRequest);
        return new ResponseEntity<>("{\"Success\":\"TargetObject has been updated!\"}",HttpStatus.OK);
    }


    @DeleteMapping("deleteTargetObject/{targetObjectId}")
    public ResponseEntity<HttpStatus> deleteTargetObject(@PathVariable Integer targetObjectId) {
        targetObjectService.deleteTargetObject(targetObjectId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
