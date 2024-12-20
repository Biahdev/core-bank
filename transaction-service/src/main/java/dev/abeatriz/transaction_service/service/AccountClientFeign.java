package dev.abeatriz.transaction_service.service;

import dev.abeatriz.transaction_service.dto.AccountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "account-service", url = "${gateway.server.url}/account-service")
public interface AccountClientFeign {

    @GetMapping("/account/{id}")
    AccountDTO getById(@PathVariable("id") Long contaId);

}
