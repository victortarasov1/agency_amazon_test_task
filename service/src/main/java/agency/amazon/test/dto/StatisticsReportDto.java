package agency.amazon.test.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record StatisticsReportDto(
        @NotNull @NotEmpty String type,
        @NotNull List<String> data) {
}
