package com.smuut.payment.controller;

import com.smuut.payment.dto.MerchantCreateDTO;
import com.smuut.payment.dto.MerchantGetDTO;
import com.smuut.payment.dto.MerchantUpdateDTO;
import com.smuut.payment.service.MerchantService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/merchant")
@RequiredArgsConstructor
@RolesAllowed({"merchant", "admin"})
public class MerchantController {

  private final MerchantService merchantService;

  @GetMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Page<MerchantGetDTO>> getMerchants(
      @ParameterObject @PageableDefault(size = 100) Pageable pageable) {
    return ResponseEntity.ok(merchantService.getMerchants(pageable));
  }

  @GetMapping(
      path = "/{merchantId}",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<MerchantGetDTO> getMerchant(@PathVariable("merchantId") UUID merchantId) {
    return ResponseEntity.of(merchantService.getMerchant(merchantId));
  }

  @RolesAllowed("admin")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<MerchantGetDTO> createMerchant(
      @Valid @RequestBody MerchantCreateDTO merchantCreateDTO) {
    return ResponseEntity.of(merchantService.createMerchant(merchantCreateDTO));
  }

  @PutMapping(
      path = "/{merchantId}",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<MerchantGetDTO> updateMerchant(
      @PathVariable("merchantId") UUID merchantId,
      @Valid @RequestBody MerchantUpdateDTO merchantUpdateDTO) {
    return ResponseEntity.of(merchantService.updateMerchant(merchantId, merchantUpdateDTO));
  }

  @RolesAllowed("admin")
  @DeleteMapping(
      path = "/{merchantId}",
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Void> deleteMerchant(@PathVariable("merchantId") UUID merchantId) {
    return merchantService.deleteMerchant(merchantId)
        ? ResponseEntity.ok().build()
        : ResponseEntity.badRequest().build();
  }
}
