package agency.amazon.test.batch.writer;

import agency.amazon.test.model.SalesAndTraffic;
import agency.amazon.test.repository.SalesAndTrafficRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SalesAndTrafficWriter implements ItemWriter<List<SalesAndTraffic>> {
    private final SalesAndTrafficRepository repository;
    @Override
    public void write(Chunk<? extends List<SalesAndTraffic>> chunk) throws Exception {
        var items = chunk.getItems().stream().flatMap(Collection::stream).toList();
        items.forEach(System.out::println);
        repository.saveAll(items);
    }
}
