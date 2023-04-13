package com.fixtab.app.controllers;

import com.fixtab.app.models.db.customers.TargetObjectModel;
import com.fixtab.app.models.requests.EditTargetObjectRequest;
import com.fixtab.app.models.requests.TargetObjectRequest;
import com.fixtab.app.models.responses.TargetObjectResponse;
import com.fixtab.app.security.AuthoritiesConstants;
import com.fixtab.app.services.interfaces.TargetObjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/targetObject")
public class TargetObjectController {

    private final TargetObjectService targetObjectService;

    @GetMapping("getAllTargetObjects")
    @PreAuthorize("hasAnyAuthority(\"" + AuthoritiesConstants.ADMIN + "\", \"" + AuthoritiesConstants.MANAGER + "\")")
    public List<TargetObjectModel> getAllTargetObjects() {
        return targetObjectService.getAllTargetObjects();
    }

    @GetMapping("getAllNotDeletedTargetObjects")
    @PreAuthorize("hasAnyAuthority(\"" + AuthoritiesConstants.ADMIN + "\", \"" + AuthoritiesConstants.MANAGER + "\")")
    public List<TargetObjectResponse> getAllNotDeletedTargetObjects() {
        return targetObjectService.getAllNotDeletedTargetObjects();
    }

    @PostMapping("createTargetObject")
    @PreAuthorize("hasAnyAuthority(\"" + AuthoritiesConstants.ADMIN + "\", \"" + AuthoritiesConstants.MANAGER + "\")")
    public ResponseEntity<?> createTargetObject(@RequestBody TargetObjectRequest targetObjectRequest) {
        targetObjectService.createTargetObject(targetObjectRequest);
        return new ResponseEntity<>("{\"Success\":\"TargetObject has been created!\"}",HttpStatus.OK);
    }

    @PutMapping("editTargetObject")
    @PreAuthorize("hasAnyAuthority(\"" + AuthoritiesConstants.ADMIN + "\", \"" + AuthoritiesConstants.MANAGER + "\")")
    public ResponseEntity<?> editTargetObject(@RequestBody EditTargetObjectRequest editTargetObjectRequest) {
        targetObjectService.editTargetObject(editTargetObjectRequest);
        return new ResponseEntity<>("{\"Success\":\"TargetObject has been updated!\"}",HttpStatus.OK);
    }

    @DeleteMapping("deleteTargetObject/{targetObjectId}")
    @PreAuthorize("hasAnyAuthority(\"" + AuthoritiesConstants.ADMIN + "\", \"" + AuthoritiesConstants.MANAGER + "\")")
    public ResponseEntity<HttpStatus> deleteTargetObject(@PathVariable Integer targetObjectId) {
        targetObjectService.deleteTargetObject(targetObjectId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
