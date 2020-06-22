package com.mvit.security.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "CNT_PARTIDAS")
//To increase speed and save sql statement execution time.
@DynamicInsert
@DynamicUpdate
public class CntPartida {
	
	@Id
	//@GeneratedValue(strategy= GenerationType.IDENTITY)
	@GeneratedValue(generator="increment") //soportado por oracle
	@GenericGenerator(name="increment", strategy = "increment") //soportado por oracle
	private Long id;
	private String origen;
	private String partida;
	private String estatusReg;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getOrigen() {
		return origen;
	}
	
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	
	public String getPartida() {
		return partida;
	}
	
	public void setPartida(String partida) {
		this.partida = partida;
	}
	
	public String getEstatusReg() {
		return estatusReg;
	}
	
	public void setEstatusReg(String estatusReg) {
		this.estatusReg = estatusReg;
	}
	
	@Override
	public String toString() {
		return "CntPartidas [id=" + id + ", origen=" + origen + ", partida=" + partida + ", estatusReg=" + estatusReg
				+ "]";
	}

	
	

}
