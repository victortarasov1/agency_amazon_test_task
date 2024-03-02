package agency.amazon.test.batch.executor;

import agency.amazon.test.batch.dto.ReaderConfigHolder;
import agency.amazon.test.batch.exception.UnknownFileTypeException;
import agency.amazon.test.batch.reader.Reader;
import agency.amazon.test.batch.writer.ReportWriter;
import agency.amazon.test.batch.dto.SalesAndTrafficReport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BatchProcessorImpl implements BatchProcessor {
    private final Reader reader;
    private final ReportWriter writer;
    private final ReaderConfigHolder holder;

    public BatchProcessorImpl(ReportWriter writer, ReaderConfigHolder holder, List<Reader> readers) {
        this.writer = writer;
        this.holder = holder;
        this.reader = readers.stream()
                .filter(v -> v.getType().equals(holder.getFileType())).findFirst()
                .orElseThrow(() -> new UnknownFileTypeException(holder.getFileType()));
    }
    @Override
    public void process() {
        var report = reader.readData(holder.getFilePath(), SalesAndTrafficReport.class);
        writer.saveToDb(report);
    }
}
