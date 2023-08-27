package com.smuut.payment.config.mapping;

import com.smuut.payment.config.MappingConfiguration;
import com.smuut.payment.dto.ReversalTransactionGetDTO;
import com.smuut.payment.entity.AuthorizeTransaction;
import com.smuut.payment.entity.ReversalTransaction;
import java.util.UUID;
import org.modelmapper.Converter;
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
                mapping
                    .using(
                        (Converter<AuthorizeTransaction, UUID>)
                            context -> context.getSource().getId())
                    .map(
                        ReversalTransaction::getAuthorizeTransaction,
                        ReversalTransactionGetDTO::setAuthorizeTransactionId))
        .implicitMappings();
  }
}
