package com.group_imposter.migrate.batch.prepare_data;


import com.group_imposter.migrate.batch.cbact04c.CBACT04C;
import com.group_imposter.migrate.batch.cbact04c.CBACT04CProcessor;
import com.group_imposter.migrate.batch.cbact04c.CBACT04Writer;
import com.group_imposter.migrate.batch.cbact04c.TranCatBalRecordFieldSetMapper;
import com.group_imposter.migrate.batch.cbtrn02c.CBTRN02C;
import com.group_imposter.migrate.model.*;
import com.group_imposter.migrate.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Configuration
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PrepareJobConfig {

    private final PlatformTransactionManager platformTransactionManager;
    private final JobRepository jobRepository;
    private final AccountRecordRepository accountRecordRepository;
    private final DisGroupRecordRepository disGroupRecordRepository;
    private final TranCatBalRecordRepository tranCatBalRecordRepository;
    private final CardXrefRecordRepository cardXrefRecordRepository;
    private final TranRecordRepository tranRecordRepository;

    @Bean
    public FlatFileItemReader<AccountRecord> fileAccountItemReader() {
        FlatFileItemReader<AccountRecord> reader = new FlatFileItemReader<>();
        reader.setName("fileAccountItemReader");
        reader.setResource(new FileSystemResource("src/main/resources/data/ASCII/acctdata.txt"));
        reader.setLineMapper(lineMapperAccountRecord());
        return reader;
    }

    @Bean
    public FlatFileItemReader<DisGroupRecord> fileDisGroupItemReader() {
        FlatFileItemReader<DisGroupRecord> reader = new FlatFileItemReader<>();
        reader.setName("fileDisGroupItemReader");
        reader.setResource(new FileSystemResource("src/main/resources/data/ASCII/discgrp.txt"));
        reader.setLineMapper(lineMapperDisGroupRecord());
        return reader;
    }

    @Bean
    public FlatFileItemReader<TranCatBalRecord> fileTranCatBalItemReader() {
        FlatFileItemReader<TranCatBalRecord> reader = new FlatFileItemReader<>();
        reader.setName("fileTranCatBalItemReader");
        reader.setResource(new FileSystemResource("src/main/resources/data/ASCII/tcatbal.txt"));
        reader.setLineMapper(lineMapperTranCatBalRecord());
        return reader;
    }

    @Bean
    public FlatFileItemReader<CardXrefRecord> fileCardXrefItemReader() {
        FlatFileItemReader<CardXrefRecord> reader = new FlatFileItemReader<>();
        reader.setName("fileCardXrefItemReader");
        reader.setResource(new FileSystemResource("src/main/resources/data/ASCII/cardxref.txt"));
        reader.setLineMapper(lineMapperCardXrefRecord());
        return reader;
    }

    @Bean
    public ItemWriter<AccountRecord> accountRecordItemWriter() {
        return accountRecordRepository::saveAll;
    }

    @Bean
    public ItemWriter<DisGroupRecord> disGroupRecordItemWriter() {
        return disGroupRecordRepository::saveAll;
    }

    @Bean
    public ItemWriter<TranCatBalRecord> tranCatBalRecordItemWriter() {
        return tranCatBalRecordRepository::saveAll;
    }

    @Bean
    public ItemWriter<CardXrefRecord> cardXrefRecordItemWriter() {
        return cardXrefRecordRepository::saveAll;
    }

    @Bean
    public Step importAccountRecordStep() {
        return new StepBuilder("importAccountRecordStep", jobRepository)
                .<AccountRecord, AccountRecord>chunk(100, platformTransactionManager)
                .reader(fileAccountItemReader())
                .writer(accountRecordItemWriter())
                .build();
    }

    @Bean
    public Step importDisGroupRecordStep() {
        return new StepBuilder("importDisGroupRecordStep", jobRepository)
                .<DisGroupRecord, DisGroupRecord>chunk(100, platformTransactionManager)
                .reader(fileDisGroupItemReader())
                .writer(disGroupRecordItemWriter())
                .build();
    }

    @Bean
    public Step importTranCatBalRecordStep() {
        return new StepBuilder("importTranCatBalRecordStep", jobRepository)
                .<TranCatBalRecord, TranCatBalRecord>chunk(100, platformTransactionManager)
                .reader(fileTranCatBalItemReader())
                .writer(tranCatBalRecordItemWriter())
                .build();
    }

    @Bean
    public Step importCardXrefRecordStep() {
        return new StepBuilder("importCardXrefRecordStep", jobRepository)
                .<CardXrefRecord, CardXrefRecord>chunk(100, platformTransactionManager)
                .reader(fileCardXrefItemReader())
                .writer(cardXrefRecordItemWriter())
                .build();
    }

    @Bean
    public Step deleteDataStep() {
        return new StepBuilder("deleteDateStep", jobRepository)
                .tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
//                    System.out.println("Delete data");
                    accountRecordRepository.deleteAll();
                    disGroupRecordRepository.deleteAll();
                    tranCatBalRecordRepository.deleteAll();
                    cardXrefRecordRepository.deleteAll();
                    tranRecordRepository.deleteAll();
                    try (BufferedWriter bw = Files.newBufferedWriter(Path.of("src/main/resources/data/ASCII/dalyrejs.txt"))) {
                        // File content will be deleted when the writer is created
                    } catch (IOException e) {
                        System.out.println("Cannot delete the contents of the file");
                        e.printStackTrace();
                    }
                    return RepeatStatus.FINISHED;
                }, platformTransactionManager)
                .build();
    }

    @Bean
    public Job handlePrepareDataJob() {
        return new JobBuilder("handlePrepareDataJob", jobRepository)
                .start(deleteDataStep())
                .next(importAccountRecordStep())
                .next(importDisGroupRecordStep())
                .next(importTranCatBalRecordStep())
                .next(importCardXrefRecordStep())
                .build();
    }

    private LineMapper<TranCatBalRecord> lineMapperTranCatBalRecord() {
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

    private LineMapper<CardXrefRecord> lineMapperCardXrefRecord() {
        DefaultLineMapper<CardXrefRecord> lineMapper = new DefaultLineMapper<>();

        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
        tokenizer.setStrict(false);
        tokenizer.setNames(
                "xrefCardNum",
                "xrefCustId",
                "xrefAcctId",
                "filler1"
        );
        tokenizer.setColumns(
                new Range(1, 16),
                new Range(17, 25),
                new Range(26, 36),
                new Range(37, 50)
        );

        BeanWrapperFieldSetMapper<CardXrefRecord> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(CardXrefRecord.class);

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    private LineMapper<AccountRecord> lineMapperAccountRecord() {
        DefaultLineMapper<AccountRecord> lineMapper = new DefaultLineMapper<>();

        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
        tokenizer.setStrict(false);
        tokenizer.setNames(
                "acctId",
                "acctActiveStatus",
                "acctCurrBal",
                "acctCreditLimit",
                "acctCashCreditLimit",
                "acctOpenDate",
                "acctExpiraionDate",
                "acctReissueDate",
                "acctCurrCycCredit",
                "acctCurrCycDebit",
                "acctGroupId",
                "acctAddrZip",
                "filler1"
        );
        tokenizer.setColumns(
                new Range(1, 11),
                new Range(12, 12),
                new Range(13, 23),
                new Range(25, 35),
                new Range(37, 47),
                new Range(49, 58),
                new Range(59, 68),
                new Range(69, 78),
                new Range(79, 89),
                new Range(91, 101),
                new Range(103, 112),
                new Range(113, 122),
                new Range(123, 300)
        );

        BeanWrapperFieldSetMapper<AccountRecord> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(AccountRecord.class);

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    private LineMapper<DisGroupRecord> lineMapperDisGroupRecord() {
        DefaultLineMapper<DisGroupRecord> lineMapper = new DefaultLineMapper<>();

        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
        tokenizer.setStrict(false);
        tokenizer.setNames(
                "disAcctGroupId",
                "disTranTypeCd",
                "disTranCatCd",
                "disIntRate",
                "filler"
        );
        tokenizer.setColumns(
                new Range(1, 10),
                new Range(11, 12),
                new Range(13, 16),
                new Range(17, 21),
                new Range(23, 50)
        );

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(new DisGroupRecordFieldSetMapper());
        return lineMapper;
    }
}
