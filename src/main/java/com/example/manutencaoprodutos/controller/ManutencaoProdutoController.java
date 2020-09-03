package com.example.manutencaoprodutos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.manutencaoprodutos.model.Produto;
import com.example.manutencaoprodutos.service.ManutencaoProdutoService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class ManutencaoProdutoController {

	private static final Logger log = LoggerFactory.getLogger(ManutencaoProdutoController.class);
	
	@Autowired
	private ManutencaoProdutoService service;
	
    @GetMapping("/produtos")
    public ResponseEntity<Object> getProdutos() {
    	try {
    		log.info("BEGIN getProdutos");
			return service.getProdutos();
		} catch (Exception e) {
			log.error("Couldn't serialize response for content type application/json", e);
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    @GetMapping("/produto/{id}")
    public ResponseEntity<Object> getProdutoById(@PathVariable(value="id") Long id) {
    	try {
    		log.info("BEGIN getProdutoById");
			return service.getProdutoById(id);
		} catch (Exception e) {
			log.error("Couldn't serialize response for content type application/json", e);
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    @GetMapping("/produtos/{param}/{value}")
    public ResponseEntity<Object> getProduto(@PathVariable(value="param") String param, @PathVariable(value="value") String value) {
    	try {
    		log.info("BEGIN getProduto param: {}, value: {}", param, value);
			return service.getProduto(param, value);
		} catch (Exception e) {
			log.error("Couldn't serialize response for content type application/json", e);
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    @PostMapping("/produto")
    public ResponseEntity<Object> creatProdutos(@Validated @RequestBody Produto produto) {
    	try {
    		log.info("BEGIN creatProdutos Produto:}", produto);
			return service.creatProdutos(produto);
		} catch (Exception e) {
			log.error("Couldn't serialize response for content type application/json", e);
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    @PutMapping("/produto/{id}")
    public ResponseEntity<Object> updateProdutos(@PathVariable(value="id") Long id, @Validated @RequestBody Produto produto) {
    	try {
    		log.info("BEGIN creatProdutos ID:{}, Produto: {}", id, produto);
			return service.updateProdutos(id, produto);
		} catch (Exception e) {
			log.error("Couldn't serialize response for content type application/json", e);
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    @DeleteMapping("/produto/{id}")
    public ResponseEntity<Object> deleteProdutos(@PathVariable(value="id") Long id) {
    	try {
    		log.info("BEGIN creatProdutos ID:{}, Produto: {}", id);
			return service.deleteProdutos(id);
		} catch (Exception e) {
			log.error("Couldn't serialize response for content type application/json", e);
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}
