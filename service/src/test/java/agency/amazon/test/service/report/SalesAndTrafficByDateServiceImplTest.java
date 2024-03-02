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

class SalesAndTrafficByDateServiceImplTest {

    private SalesAndTrafficRepository repository;

    private SalesAndTrafficService service;
    @BeforeEach()
    public void setUp(){
        repository = mock(SalesAndTrafficRepository.class);
        service = new SalesAndTrafficByDateServiceImpl(repository);
    }
    @Test
    void findById() {
        var date = "2024-02-14";
        var expected = new SalesAndTraffic();
        expected.setId(date);
        when(repository.findById(eq(date))).thenReturn(Optional.of(expected));
        var result = service.findById(date);
        verify(repository, times(1)).findById(eq(date));
        assertEquals(expected, result);
    }

    @Test
    void findById_shouldReturnEmptySalesAndTraffic() {
        var date = "2024-02-14";
        var expected = new SalesAndTraffic();
        when(repository.findById(eq(date))).thenReturn(Optional.empty());
        var result = service.findById(date);
        verify(repository, times(1)).findById(eq(date));
        assertEquals(expected, result);
    }

    @Test
    void findAllById() {
        var startDate = "2024-02-14";
        var endDate = "2024-02-16";
        var range = List.of(startDate, "2024-02-15", endDate);
        when(repository.findAllById(eq(range))).thenReturn(List.of());
        service.findAllById(List.of(startDate, endDate));
        verify(repository, times(1)).findAllById(eq(range));
    }

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(List.of());
        service.findAll();
        verify(repository, times(1)).findAll();
    }

}