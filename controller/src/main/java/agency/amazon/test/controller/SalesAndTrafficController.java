package agency.amazon.test.controller;


import agency.amazon.test.dto.ReportQuery;
import agency.amazon.test.model.SalesAndTraffic;
import agency.amazon.test.service.SalesAndTrafficFacade;
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

    private final SalesAndTrafficFacade facade;

    @PostMapping("/id")
    public SalesAndTraffic findOne(@RequestBody @Valid ReportQuery dto) {
        return facade.findById(dto);
    }

    @PostMapping("/range")
    public List<SalesAndTraffic> findByRange(@RequestBody @Valid ReportQuery dto) {
        return facade.findAllById(dto);
    }

    @PostMapping("/all")
    public List<SalesAndTraffic> findAll(@RequestBody @Valid ReportQuery dto) {
        return facade.findAll(dto);
    }

}
