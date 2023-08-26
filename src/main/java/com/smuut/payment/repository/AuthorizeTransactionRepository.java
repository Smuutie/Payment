package com.smuut.payment.repository;

import com.smuut.payment.entity.AuthorizeTransaction;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizeTransactionRepository extends JpaRepository<AuthorizeTransaction, UUID> {}
