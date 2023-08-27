package com.smuut.payment.config.mapping;

import com.smuut.payment.dto.TransactionGetDTO;
import com.smuut.payment.entity.BaseTransaction;
import com.smuut.payment.entity.Merchant;
import java.util.UUID;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BaseTransactionGetDTOMapping implements MappingConfiguration {
  @Override
  public void configure(ModelMapper modelMapper) {
    final var typemap = modelMapper.typeMap(BaseTransaction.class, TransactionGetDTO.class);

    typemap.addMappings(
        mapping ->
            mapping
                .using((Converter<Merchant, UUID>) context -> context.getSource().getId())
                .map(BaseTransaction::getMerchant, TransactionGetDTO::setMerchantId));
  }
}
