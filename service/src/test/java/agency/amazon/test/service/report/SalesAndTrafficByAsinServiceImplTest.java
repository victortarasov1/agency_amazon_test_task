package agency.amazon.test.service.report;

import agency.amazon.test.model.SalesAndTraffic;
import agency.amazon.test.repository.SalesAndTrafficRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class SalesAndTrafficByAsinServiceImplTest {
    private SalesAndTrafficRepository repository;

    private SalesAndTrafficService service;
    @BeforeEach()
    public void setUp(){
        repository = mock(SalesAndTrafficRepository.class);
        service = new SalesAndTrafficByAsinServiceImpl(repository);
    }
    @Test
    void findById() {
        var asin = "asin";
        var expected = new SalesAndTraffic();
        expected.setId(asin);
        when(repository.findById(eq(asin))).thenReturn(Optional.of(expected));
        var result = service.findById(asin);
        verify(repository, times(1)).findById(eq(asin));
        assertEquals(expected, result);
    }

    @Test
    void findById_shouldReturnEmptySalesAndTraffic() {
        var asin = "asin";
        var expected = new SalesAndTraffic();
        when(repository.findById(eq(asin))).thenReturn(Optional.empty());
        var result = service.findById(asin);
        verify(repository, times(1)).findById(eq(asin));
        assertEquals(expected, result);
    }

    @Test
    void findAllById() {
        var asinList = List.of("asin 1", "asin 2", "asin 3");
        when(repository.findAllById(eq(asinList))).thenReturn(List.of());
        service.findAllById(asinList);
        verify(repository, times(1)).findAllById(eq(asinList));
    }

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(List.of());
        service.findAll();
        verify(repository, times(1)).findAll();
    }
}