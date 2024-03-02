package agency.amazon.test.batch.executor;

import agency.amazon.test.batch.dto.ReaderConfigHolder;
import agency.amazon.test.batch.dto.SalesAndTrafficReport;
import agency.amazon.test.batch.exception.UnknownFileTypeException;
import agency.amazon.test.batch.reader.Reader;
import agency.amazon.test.batch.writer.ReportWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BatchProcessorImplTest {
    private Reader reader;
    private ReportWriter writer;
    private ReaderConfigHolder holder;

    private BatchProcessor processor;
    @BeforeEach
    public void setUp() {
        reader = mock(Reader.class);
        writer = mock(ReportWriter.class);
        holder = mock(ReaderConfigHolder.class);
    }

    @Test
    public void testConstructor() {
        var type = "json";
        when(holder.getFileType()).thenReturn(type);
        when(reader.getType()).thenReturn(type);
        processor = new BatchProcessorImpl(writer, holder, List.of(reader));
    }

    @Test
    public void testConstructor_shouldThrownUnknownFileTypeException(){
        when(holder.getFileType()).thenReturn("json");
        when(reader.getType()).thenReturn("xml");
        assertThatThrownBy(() -> new BatchProcessorImpl(writer, holder, List.of(reader)))
                .isInstanceOf(UnknownFileTypeException.class);
    }

    @Test
    public void testProcess() {
        var type = "json";
        when(holder.getFileType()).thenReturn(type);
        when(reader.getType()).thenReturn(type);
        processor = new BatchProcessorImpl(writer, holder, List.of(reader));
        var report = new SalesAndTrafficReport();
        when(reader.readData(any(), any())).thenReturn(report);
        processor.process();
        verify(writer, times(1)).saveToDb(eq(report));
    }
}