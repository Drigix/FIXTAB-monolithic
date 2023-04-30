package com.fixtab.app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.fixtab.app.models.db.activities.RequestModel;
import com.fixtab.app.models.requests.EditRequestRepairRequest;
import com.fixtab.app.models.requests.RequestRepairRequest;
import com.fixtab.app.models.responses.RequestRepairResponse;
import com.fixtab.app.services.interfaces.RequestRepairService;

import static com.fixtab.app.security.AuthoritiesConstants.*;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/requestRepair")
public class RequestRepairController {
    
    private final RequestRepairService requestRepairService;

    @GetMapping("getAllRequestRepairs")
    @PreAuthorize(ADMIN_PREAUTHORIZE)
    public List<RequestModel> getAllRequestRepairs() {
        return requestRepairService.getAllRequestRepairs();
    }

    @GetMapping("getAllNotDeletedRequestRepairs")
    @PreAuthorize(MANAGEMENT_PREAUTHORIZE)
    public List<RequestRepairResponse> getAllNotDeletedRequestRepairs() {
        return requestRepairService.getAllNotDeletedRequestRepairs();
    }

    @PostMapping("createRequestRepair")
    @PreAuthorize(MANAGEMENT_PREAUTHORIZE)
    public ResponseEntity<?> createRequestRepair(@RequestBody RequestRepairRequest requestRepairRequest) {
        requestRepairService.createRequestRepair(requestRepairRequest);
        return new ResponseEntity<>("{\"Success\":\"Request has been created!\"}", HttpStatus.OK);
    }

    @PutMapping("editRequestRepair")
    @PreAuthorize(MANAGEMENT_PREAUTHORIZE)
    public ResponseEntity<?> editRequestRepair(@RequestBody EditRequestRepairRequest editRequestRepairRequest) {
        requestRepairService.editRequestRepair(editRequestRepairRequest);
        return new ResponseEntity<>("{\"Success\":\"Request has been updated!\"}", HttpStatus.OK);
    }

}
