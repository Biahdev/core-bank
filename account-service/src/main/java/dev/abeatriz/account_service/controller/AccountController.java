package dev.abeatriz.account_service.controller;

import dev.abeatriz.account_service.dto.AccountCreateDTO;
import dev.abeatriz.account_service.dto.AccountDetailDTO;
import dev.abeatriz.account_service.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public AccountDetailDTO newAccount(@RequestBody @Valid AccountCreateDTO json) {
        return accountService.create(json);
    }

    @GetMapping("/{id}")
    public AccountDetailDTO getById(@PathVariable("id") Long id) {
       return accountService.getById(id);
    }


}
