package com.smuut.payment.config;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ModelMapperConfig {

  private final List<MappingConfiguration> configurations;

  @Bean
  public ModelMapper createModelMapper() {
    final var modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setImplicitMappingEnabled(false);
    configurations.forEach(config -> config.configure(modelMapper));
    return modelMapper;
  }
}
