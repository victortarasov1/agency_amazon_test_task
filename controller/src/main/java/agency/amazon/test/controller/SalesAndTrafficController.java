package agency.amazon.test.controller;


import agency.amazon.test.dto.ReportQuery;
import agency.amazon.test.model.SalesAndTraffic;
import agency.amazon.test.service.SalesAndTrafficQueryHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
@PreAuthorize("hasRole('ROLE_USER')")
public class SalesAndTrafficController {
    private final SalesAndTrafficQueryHandler handler;

    @PostMapping
    public List<SalesAndTraffic> findReport(@RequestBody @Valid ReportQuery query) {
        return handler.executeQuery(query);
    }
}
