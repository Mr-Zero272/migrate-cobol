package com.group_imposter.migrate.service.impl;

import com.group_imposter.migrate.dto.response.ResponseObject;
import com.group_imposter.migrate.repository.TranCatBalRecordRepository;
import com.group_imposter.migrate.service.BatchService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BatchServiceImpl implements BatchService {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job handleDalytranRecordJob;

    @Autowired
    private Job handleTranCatBalRecordJob;

    @Autowired
    private Job handlePrepareDataJob;

    @Autowired
    private TranCatBalRecordRepository tranCatBalRecordRepository;

    @Override
    public ResponseObject execute(String batchName) {
        ResponseObject responseObject = new ResponseObject();
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        try {
            switch (batchName.toLowerCase()) {
                case "cbtrn02c":
                    jobLauncher.run(handleDalytranRecordJob, jobParameters);
                    break;
                case "cbact04c":
                    jobLauncher.run(handleTranCatBalRecordJob, jobParameters);
                    break;
                case "prepdata":
                    jobLauncher.run(handlePrepareDataJob, jobParameters);
                    break;
                default:
                    responseObject.setStatus("Fail");
                    responseObject.setHttpStatus(HttpStatus.BAD_REQUEST);
                    responseObject.setMessage("Job execution failed");
                    responseObject.setError("Invalid batch name");
                    break;
            }
            responseObject.setStatus("Success");
            responseObject.setHttpStatus(HttpStatus.OK);
            responseObject.setMessage("Job executed successfully");
        } catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException | JobRestartException |
                 JobParametersInvalidException e) {
            responseObject.setStatus("Fail");
            responseObject.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseObject.setMessage("Job execution failed");
            responseObject.setError(e.getMessage());
            e.printStackTrace();
        }
        return responseObject;
    }
}
