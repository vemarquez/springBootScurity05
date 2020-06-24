package com.mvit.security.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mvit.security.model.CntPartida;
import com.mvit.security.service.CntPartidaService;

@Controller
public class AppController {

	public static final Logger logger = LoggerFactory.getLogger(AppController.class);

	@Autowired
	private CntPartidaService service;

	// (1) todos los registros
	@RequestMapping("/")
	public String viewHomePage(Model model) {		
	    
		List<CntPartida> listCntPartidas = service.getCntPartidas();
		logger.info("Todos los registros::" + listCntPartidas.toString());
		model.addAttribute("listCntPartidas", listCntPartidas);		
		return "index";
	}

	//(2) formulario nuevo
	@RequestMapping("/nuevo")
	public String showNewCntPartidaForm(Model model) {
		CntPartida cntPartida = new CntPartida();
		model.addAttribute("cntPartida", cntPartida);
		logger.info("Nuevo registro :: formulario ::");
		return "nueva_partida";
	}

	//(3) salvar
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("cntPartida") CntPartida cntPartida) {
		logger.info("Nuevo registro :: " + cntPartida);
		service.addCntPartida(cntPartida);		
		return "redirect:/";
	}
	

	//(4) editar
	@RequestMapping("/editar/{id}")
	public ModelAndView showEditProductForm(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("edita_partida");

		Optional<CntPartida> cntPartida = service.getCntPartidaById(id);
		if (cntPartida.isPresent()){
			logger.info("Editar :: " + cntPartida);
			mav.addObject("cntPartida", cntPartida);
		}
		else {
			logger.info("Registro id :: " + id + " no encontrado");
		}
		return mav;
	}	

	//(5) borrar
	@RequestMapping("/borrar/{id}")
	public String deleteProduct(@PathVariable(name = "id") Long id) {
		logger.info("Se borra el registro id :: " + id );
		service.deleteCntPartidaById(id);		
		return "redirect:/";
	}
	
	@GetMapping("/403")
	public String error403() {
		return "403";
	}
}
