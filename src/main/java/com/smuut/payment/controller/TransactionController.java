package com.smuut.payment.controller;

import com.smuut.payment.dto.TransactionCreateDTO;
import com.smuut.payment.dto.TransactionGetDTO;
import com.smuut.payment.service.impl.BaseTransactionService;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

  private final BaseTransactionService transactionService;

  @GetMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Page<TransactionGetDTO>> getTransactions(
      @PageableDefault(size = 100) Pageable pageable) {
    return ResponseEntity.ok(transactionService.getTransactions(pageable));
  }

  @GetMapping(
      path = "/{transactionId}",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<TransactionGetDTO> getTransaction(
      @PathVariable("transactionId") UUID merchantId) {
    return ResponseEntity.of(transactionService.getTransaction(merchantId));
  }

  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<TransactionGetDTO> createTransaction(
      @Valid @RequestBody TransactionCreateDTO transactionCreateDTO) {
    return ResponseEntity.of(transactionService.createTransaction(transactionCreateDTO));
  }

  @DeleteMapping(
      path = "/{transactionId}",
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Void> deleteTransaction(@PathVariable("transactionId") UUID transactionId) {
    return transactionService.deleteTransaction(transactionId)
        ? ResponseEntity.ok().build()
        : ResponseEntity.badRequest().build();
  }
}
