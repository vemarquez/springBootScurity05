package com.mvit.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mvit.security.model.CntPartida;

@Repository
public interface DaoCntPartida extends JpaRepository<CntPartida, Long> {

}
