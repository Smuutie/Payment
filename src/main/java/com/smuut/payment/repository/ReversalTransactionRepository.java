package com.smuut.payment.repository;

import com.smuut.payment.entity.ReversalTransaction;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReversalTransactionRepository extends JpaRepository<ReversalTransaction, UUID> {}
