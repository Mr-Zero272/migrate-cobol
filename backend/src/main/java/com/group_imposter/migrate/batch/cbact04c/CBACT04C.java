package com.group_imposter.migrate.batch.cbact04c;

import com.group_imposter.migrate.model.TranCatBalRecord;
import com.group_imposter.migrate.model.TranRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CBACT04C {

    private final PlatformTransactionManager platformTransactionManager;
    private final JobRepository jobRepository;

    @Bean
    public FlatFileItemReader<TranCatBalRecord> tranCatBalRecordFlatFileItemReader() {
        return new FlatFileItemReaderBuilder<TranCatBalRecord>()
                .name("tranCatBalRecordFlatFileItemReader")
                .resource(new FileSystemResource("src/main/resources/data/ASCII/tcatbal.txt"))
                .lineMapper(lineMapper())
                .build();
    }

    @Bean
    public CBACT04CProcessor cbact04CProcessor() {
        return new CBACT04CProcessor();
    }

    @Bean
    public CBACT04Writer cbact04CWriter() {
        return new CBACT04Writer();
    }

    @Bean
    public Step handleTranCatBalRecordJobStep1() {
        return new StepBuilder("CBACT04Cstep1", jobRepository)
                .<TranCatBalRecord, TranRecord>chunk(100, platformTransactionManager)
                .reader(tranCatBalRecordFlatFileItemReader())
                .processor(cbact04CProcessor())
                .writer(cbact04CWriter())
                .build();
    }
    @Bean
    public Job handleTranCatBalRecordJob() {
        return new JobBuilder("handleTranCatBalRecordJob", jobRepository)
                .start(handleTranCatBalRecordJobStep1())
                .build();
    }

    private LineMapper<TranCatBalRecord> lineMapper() {
        DefaultLineMapper<TranCatBalRecord> lineMapper = new DefaultLineMapper<>();

        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
        tokenizer.setStrict(false);
        tokenizer.setNames(
                "trancatAcctId",
                "trancatTypeCd",
                "trancatCd",
                "tranCatBal",
                "filler1"
        );
        tokenizer.setColumns(
                new Range(1, 11),
                new Range(12, 13),
                new Range(14, 17),
                new Range(18, 27),
                new Range(29, 50)
        );

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(new TranCatBalRecordFieldSetMapper());
        return lineMapper;
    }
}
