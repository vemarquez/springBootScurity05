package com.mvit.security.dao;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.mvit.security.model.CntPartida;

@Repository
public interface DaoCntPartida extends JpaRepositoryImplementation<CntPartida, Long> {

}
