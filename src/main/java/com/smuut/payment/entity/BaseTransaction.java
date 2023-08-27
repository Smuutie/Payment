package com.smuut.payment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@Entity
@Table(name = "transaction")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transaction_type")
public class BaseTransaction {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @UuidGenerator
  @JdbcTypeCode(SqlTypes.CHAR)
  private UUID id;

  @Column(updatable = false)
  @CreationTimestamp
  private OffsetDateTime createdAt;

  @Email private String customerEmail;

  @NotEmpty
  @Pattern(regexp = "(\\+\\d{3}|0{1})\\d{9}")
  private String customerPhone;

  private String referenceId;

  @Enumerated(EnumType.STRING)
  private TransactionStatus transactionStatus;

  @ManyToOne private Merchant merchant;
}
