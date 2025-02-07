package com.group_imposter.migrate.batch.cbtrn02c;

import com.group_imposter.migrate.model.DalytranRecord;
import com.group_imposter.migrate.model.TranRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
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
public class CBTRN02C {

    private final PlatformTransactionManager platformTransactionManager;
    private final JobRepository jobRepository;

    @Bean
    public FlatFileItemReader<DalytranRecord> dalytranRecordFlatFileItemReader() {
        return new FlatFileItemReaderBuilder<DalytranRecord>()
                .name("dalytranRecordFlatFileItemReader")
                .resource(new FileSystemResource("src/main/resources/data/ASCII/dailytran.txt"))
                .lineMapper(lineMapper())
                .build();
    }

    @Bean
    public CBTRN02CProcessor cbtrn02CProcessor() {
        return new CBTRN02CProcessor();
    }

    @Bean
    public FlatFileItemWriter<DalytranRecord> dalytranRecordFlatFileItemWriter() {
        return new FlatFileItemWriterBuilder<DalytranRecord>()
                .name("dalytranRecordFlatFileItemWriter")
                .resource(new FileSystemResource("src/main/resources/data/ASCII/output.txt"))
                .delimited()
                .delimiter(",")
                .names("dalytranId", "dalytranTypeCd")
                .build();
    }

    @Bean
    public Step handleDalytranRecordJobStep1() {
        return new StepBuilder("csvImport", jobRepository)
                .<DalytranRecord, TranRecord>chunk(100, platformTransactionManager)
                .reader(dalytranRecordFlatFileItemReader())
                .processor(cbtrn02CProcessor())
                .writer(new CBTRN02CWriter())
                .build();
    }


    @Bean
    public Job handleDalytranRecordJob() {
        return new JobBuilder("handleDalytranRecordJob", jobRepository)
                .start(handleDalytranRecordJobStep1())
                .build();

    }

    private LineMapper<DalytranRecord> lineMapper() {
        DefaultLineMapper<DalytranRecord> lineMapper = new DefaultLineMapper<>();

        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
        tokenizer.setStrict(false);
        tokenizer.setNames(
                "dalytranId",
                "dalytranTypeCd",
                "dalytranCatCd",
                "dalytranSource",
                "dalytranDesc",
                "dalytranAmt",
                "dalytranMerchantId",
                "dalytranMerchantName",
                "dalytranMerchantCity",
                "dalytranMerchantZip",
                "dalytranCardNum",
                "dalytranOrigTs",
                "dalytranProcTs",
                "filler1"
        );
        tokenizer.setColumns(
                new Range(1, 16),
                new Range(17, 18),
                new Range(19, 22),
                new Range(23, 32),
                new Range(33, 132),
                new Range(133, 142),
                new Range(144, 152),
                new Range(153, 202),
                new Range(203, 252),
                new Range(253, 262),
                new Range(263, 278),
                new Range(279, 304),
                new Range(305, 330),
                new Range(331, 350)
        );

        BeanWrapperFieldSetMapper<DalytranRecord> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(DalytranRecord.class);

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }
}
