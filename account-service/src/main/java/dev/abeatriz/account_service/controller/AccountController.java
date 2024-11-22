package dev.abeatriz.account_service.controller;

import dev.abeatriz.account_service.dto.AccountCreate;
import dev.abeatriz.account_service.dto.AccountDetail;
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
    public AccountDetail create(@RequestBody @Valid AccountCreate json) {
        return accountService.create(json);
    }

    @GetMapping("/{id}")
    public AccountDetail getById(@PathVariable("id") Long id) {
       return accountService.getById(id);
    }


}
