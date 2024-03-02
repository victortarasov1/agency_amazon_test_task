package agency.amazon.test.controller;

import agency.amazon.test.dto.AccountDto;
import agency.amazon.test.dto.AccountWithDetailsDto;
import agency.amazon.test.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService service;

    @PostMapping("/add")
    public void add(@RequestBody @Valid AccountWithDetailsDto dto) {
        service.addAccount(dto);
    }

    @GetMapping("/get")
    @PreAuthorize("hasRole('ROLE_USER')")
    public AccountDto get(Principal principal){
        return service.getAccount(principal.getName());
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void delete(Principal principal) {
        service.remove(principal.getName());
    }

    @PatchMapping("/update")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void update(Principal principal, @RequestBody @Valid AccountWithDetailsDto dto) {
        service.update(principal.getName(), dto);
    }

}
