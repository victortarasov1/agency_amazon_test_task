package agency.amazon.test.batch.reader;

import agency.amazon.test.batch.exception.DataParsingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JsonFileReader implements Reader {
    private final ObjectMapper mapper;
    @Override
    public <T> T readData(String path, Class<T> clazz) {
        try(var stream = new FileInputStream(path)){
            return mapper.readerFor(clazz).readValue(stream);
        } catch (IOException ex) {
            throw new DataParsingException(ex);
        }
    }

    @Override
    public String getType() {
        return "json";
    }
}
