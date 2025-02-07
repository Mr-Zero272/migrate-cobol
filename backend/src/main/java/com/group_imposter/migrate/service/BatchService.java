package com.group_imposter.migrate.service;

import com.group_imposter.migrate.dto.response.ResponseObject;

public interface BatchService {
    ResponseObject execute(String batchName);
}
