package agency.amazon.test.batch.configuration;

import agency.amazon.test.batch.dto.ReaderConfigHolder;
import agency.amazon.test.batch.dto.SalesAndTrafficReport;
import agency.amazon.test.model.SalesAndTraffic;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

//@Configuration
@RequiredArgsConstructor
//@EnableBatchProcessing
public class BatchConfig {
    private final ReaderConfigHolder holder;
    private final ItemWriter<List<SalesAndTraffic>> writer;
    private final ItemProcessor<SalesAndTrafficReport, List<SalesAndTraffic>> processor;
    private final PlatformTransactionManager manager;
    private final JobRepository repository;
    @Bean
    public JsonItemReader<SalesAndTrafficReport> jsonItemReader(){
        return new JsonItemReaderBuilder<SalesAndTrafficReport>()
                .jsonObjectReader(new JacksonJsonObjectReader<>(SalesAndTrafficReport.class))
                .resource(new ClassPathResource((holder.getFilePath())))
                .name("JsonSalesAndTrafficReportReader")
                .build();
    }

    @Bean
    public Step salesAndTrafficStep() {
        return new StepBuilder("sep", repository)
                .<SalesAndTrafficReport, List<SalesAndTraffic>>chunk(1, manager)
                .reader(jsonItemReader())
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job salesAndTrafficJob() {
        return new JobBuilder("job", repository)
                .start(salesAndTrafficStep())
                .build();
    }
}
