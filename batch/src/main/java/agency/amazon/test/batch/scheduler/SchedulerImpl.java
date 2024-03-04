package agency.amazon.test.batch.scheduler;

import agency.amazon.test.batch.executor.BatchProcessor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

//@Service
@RequiredArgsConstructor
//@PropertySource("classpath:batch.properties")
public class SchedulerImpl implements Scheduler {
    private final BatchProcessor processor;
    private final JobLauncher launcher;
    private final Job job;

    @Override
    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.MINUTES)
    @SneakyThrows
    public void run() {
//        processor.process();
        launcher.run(job, new JobParameters());
    }
}
