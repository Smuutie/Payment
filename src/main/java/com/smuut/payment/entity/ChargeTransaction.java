package com.smuut.payment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("Charge")
public class ChargeTransaction extends BaseTransaction {

    @NotNull
    @Column(name = "charge_amount")
    private Double amount;

    @NotNull
    @OneToOne(mappedBy = "chargeTransaction",fetch = FetchType.EAGER, optional = false)
    private AuthorizeTransaction authorizeTransaction;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "charge_transaction_id")
    private RefundTransaction refundTransaction;
}
