package com.mvit.security.service;

import java.util.List;
import java.util.Optional;

import com.mvit.security.model.CntPartida;

public interface CntPartidaService {
	public List<CntPartida> getCntPartidas();
	public Optional<CntPartida> getCntPartidaById(Long id);
	public CntPartida addCntPartida(CntPartida cntPartida);
	public CntPartida updateCntPartida(CntPartida cntPartida);
	public void deleteCntPartidaById(Long id);
	public void deleteAllCntPartida();
}
