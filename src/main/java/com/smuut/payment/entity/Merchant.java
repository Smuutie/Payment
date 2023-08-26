package com.smuut.payment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

@Data
@Entity
@Table(name = "merchant")
@ToString(exclude = "transactions")
public class Merchant {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @UuidGenerator
  @JdbcTypeCode(SqlTypes.CHAR)
  private UUID id;

  @Column(updatable = false)
  @CreationTimestamp
  private OffsetDateTime createdAt;

  private boolean admin;

  @NotEmpty private String name;

  private String description;

  @Email private String email;

  @Formula(
      "(SELECT SUM(ct.amount) FROM charge_transaction ct WHERE ct.status='APPROVED' AND ct.merchant_id=id )")
  private Double totalTransactionSum;

  @OneToMany(mappedBy = "merchant", fetch = FetchType.LAZY)
  private Set<BaseTransaction> transactions;
}
