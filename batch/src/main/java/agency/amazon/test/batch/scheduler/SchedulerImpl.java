package agency.amazon.test.batch.scheduler;

import agency.amazon.test.batch.executor.BatchProcessor;
import agency.amazon.test.repository.SalesAndTrafficRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:batch.properties")
public class SchedulerImpl implements Scheduler {
    private final BatchProcessor processor;
    private final SalesAndTrafficRepository repository;

    @Override
    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void run() {
        processor.process();
    }
}
