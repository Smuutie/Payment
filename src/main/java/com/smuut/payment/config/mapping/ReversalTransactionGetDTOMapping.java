package com.smuut.payment.config.mapping;

import com.smuut.payment.config.MappingConfiguration;
import com.smuut.payment.dto.ReversalTransactionGetDTO;
import com.smuut.payment.entity.ReversalTransaction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ReversalTransactionGetDTOMapping implements MappingConfiguration {

  @Override
  public void configure(ModelMapper modelMapper) {
    final var typemap =
        modelMapper.typeMap(ReversalTransaction.class, ReversalTransactionGetDTO.class);
    typemap
        .addMappings(
            mapping ->
                mapping.map(
                    ReversalTransaction::getAuthorizeTransactionId,
                    ReversalTransactionGetDTO::setAuthorizeTransactionId))
        .implicitMappings();
  }
}
