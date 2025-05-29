package com.dataexport.consumer.repository;

import com.dataexport.consumer.model.AtmWithdrawal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtmWithdrawalRepository extends JpaRepository<AtmWithdrawal, Long> {
}
