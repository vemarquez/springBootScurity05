package com.mvit.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvit.security.dao.DaoCntPartida;
import com.mvit.security.model.CntPartida;


@Service
public class CntPartidaServiceImpl implements CntPartidaService{
	
	@Autowired
	DaoCntPartida dao;

	@Override
	public List<CntPartida> getCntPartidas() {		
		return dao.findAll();
	}

	@Override
	public Optional<CntPartida> getCntPartidaById(Long id) {
		return dao.findById(id);
	}

	@Override
	public CntPartida addCntPartida(CntPartida cntPartida) {
		return dao.save(cntPartida);
	}

	@Override
	public CntPartida updateCntPartida(CntPartida cntPartida) {
		 return dao.save(cntPartida);
	}

	@Override
	public void deleteCntPartidaById(Long id) {
		dao.deleteById(id);		
	}

	@Override
	public void deleteAllCntPartida() {
		dao.deleteAll();		
	}



}
