package com.smuut.payment.config.mapping;

import com.smuut.payment.dto.RefundTransactionGetDTO;
import com.smuut.payment.entity.ChargeTransaction;
import com.smuut.payment.entity.RefundTransaction;
import java.util.UUID;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RefundTransactionGetDTOMapping implements MappingConfiguration {

  @Override
  public void configure(ModelMapper modelMapper) {
    final var typeMap = modelMapper.typeMap(RefundTransaction.class, RefundTransactionGetDTO.class);
    typeMap.addMappings(
        mapping ->
            mapping
                .using((Converter<ChargeTransaction, UUID>) context -> context.getSource().getId())
                .map(
                    RefundTransaction::getChargeTransaction,
                    RefundTransactionGetDTO::setChargeTransactionId));
  }
}
