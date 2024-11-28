package dev.abeatriz.account_service.controller;

import dev.abeatriz.account_service.dto.AccountCreate;
import dev.abeatriz.account_service.dto.AccountDetail;
import dev.abeatriz.account_service.dto.AccountUpdate;
import dev.abeatriz.account_service.exception.ErrorMessage;
import dev.abeatriz.account_service.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Conta", description = "Operações relacionadas entidade Conta")
@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Operation(
            summary = "Cadastrar",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Cadastro concluída com sucesso",
                            content = {@Content(schema = @Schema(implementation = AccountDetail.class), mediaType = "application/json")}
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Valores inválidos ou mal formatados",
                            content = {@Content(schema = @Schema(implementation = ErrorMessage.class), mediaType = "application/json")}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Recurso ou Entidade não foi encontrado",
                            content = {@Content(schema = @Schema(implementation = ErrorMessage.class), mediaType = "application/json")}
                    ),
            }
    )
    @PostMapping
    public AccountDetail create(@RequestBody @Valid AccountCreate json) {
        return accountService.create(json);
    }

    @Operation(
            summary = "Listagem",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Listagem com sucesso",
                            content = {@Content(schema = @Schema(implementation = AccountDetail.class), mediaType = "application/json")}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Recurso ou Entidade não foi encontrado",
                            content = {@Content(schema = @Schema(implementation = ErrorMessage.class), mediaType = "application/json")}
                    ),
            }
    )
    @GetMapping
    public List<AccountDetail> listAll() {
        return accountService.listAll();
    }

    @Operation(
            summary = "Detalhe",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Detalhe com sucesso",
                            content = {@Content(schema = @Schema(implementation = AccountDetail.class), mediaType = "application/json")}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Recurso ou Entidade não foi encontrado",
                            content = {@Content(schema = @Schema(implementation = ErrorMessage.class), mediaType = "application/json")}
                    ),
            }
    )
    @GetMapping("/{id}")
    public AccountDetail listById(@PathVariable("id") Long id) {
        return accountService.listById(id);
    }


    @Operation(
            summary = "Atualizar",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Atualizado com sucesso",
                            content = {@Content(schema = @Schema(implementation = AccountDetail.class), mediaType = "application/json")}
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Valores inválidos",
                            content = {@Content(schema = @Schema(implementation = ErrorMessage.class), mediaType = "application/json")}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Recurso ou Entidade não foi encontrado",
                            content = {@Content(schema = @Schema(implementation = ErrorMessage.class), mediaType = "application/json")}
                    ),
            }
    )
    @PutMapping("{id}")
    public AccountDetail update(@PathVariable("id") Long id, @RequestBody @Valid AccountUpdate json) {
        return accountService.update(id, json);
    }

    @Operation(
            summary = "Desativar",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Desativado com sucesso",
                            content = {@Content(schema = @Schema(implementation = AccountDetail.class), mediaType = "application/json")}
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Valores inválidos",
                            content = {@Content(schema = @Schema(implementation = ErrorMessage.class), mediaType = "application/json")}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Recurso ou Entidade não foi encontrado",
                            content = {@Content(schema = @Schema(implementation = ErrorMessage.class), mediaType = "application/json")}
                    ),
            }
    )
    @PutMapping("{id}/disable")
    public AccountDetail disable(@PathVariable("id") Long id) {
        return accountService.disable(id);
    }
}
