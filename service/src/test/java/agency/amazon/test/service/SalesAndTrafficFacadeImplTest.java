package agency.amazon.test.service;

import agency.amazon.test.dto.StatisticsReportDto;
import agency.amazon.test.exception.UnknownIdTypeException;
import agency.amazon.test.model.SalesAndTraffic;
import agency.amazon.test.service.report.SalesAndTrafficService;
import jdk.jfr.DataAmount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.configuration.IMockitoConfiguration;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SalesAndTrafficFacadeImplTest {
    public static final String DATA = "data";
    private SalesAndTrafficService service;

    private SalesAndTrafficFacade facade;

    @BeforeEach
    public void setUp() {
        service = mock(SalesAndTrafficService.class);
        when(service.getIdType()).thenReturn(DATA);
        var services = List.of(service);
        facade = new SalesAndTrafficFacadeImpl(services);
    }

    @Test
    void findById() {
        var id = "2024-02-14";
        var dto = new StatisticsReportDto(DATA, List.of(id));
        facade.findById(dto);
        verify(service, times(1)).findById(id);
    }

    @Test
    void findById_shouldThrowUnknownIdTypeException() {
        var id = "2024-02-14";
        var dto = new StatisticsReportDto("ddf", List.of(id));
        assertThatThrownBy(() -> facade.findById(dto))
                .isInstanceOf(UnknownIdTypeException.class);
    }


    @Test
    void findAllId() {
        var ids = List.of("2024-02-14", "2024-02-16");
        var dto = new StatisticsReportDto(DATA, ids);
        facade.findAllById(dto);
        verify(service, times(1)).findAllById(ids);
    }

    @Test
    void findAll() {
        var dto = new StatisticsReportDto(DATA, List.of());
        facade.findAll(dto);
        verify(service, times(1)).findAll();
    }


}