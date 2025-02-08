package com.group_imposter.migrate.controller;

import com.group_imposter.migrate.dto.response.ResponseObject;
import com.group_imposter.migrate.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/batch")
public class BatchController {

    @Autowired
    private BatchService batchService;

    @PostMapping
    public ResponseEntity<ResponseObject> runBatch(@RequestBody Map<String, String> request) {
        return ResponseEntity.ok(batchService.execute(request.get("batchName")));
    }
}
