package agency.amazon.test.batch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Data
@PropertySource("classpath:batch.properties")
@AllArgsConstructor
@NoArgsConstructor
public class ReaderConfigHolder {
    @Value("${reports.file.path}")
    private String filePath;
    @Value("${reports.file.type}")
    private String fileType;
}
