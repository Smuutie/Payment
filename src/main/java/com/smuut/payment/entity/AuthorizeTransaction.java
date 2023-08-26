package com.smuut.payment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("Authorize")
public class AuthorizeTransaction extends BaseTransaction {

    @NotNull
    @Column(name = "authorized_amount")
    private Double amount;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "charge_to_authorize_transaction_id")
    private ChargeTransaction chargeTransaction;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reversal_to_authorize_transaction_id")
    private ReversalTransaction reversalTransaction;
}
