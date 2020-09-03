package com.example.manutencaoprodutos.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.manutencaoprodutos.common.ConverterProduto;
import com.example.manutencaoprodutos.common.EnumCategoria;
import com.example.manutencaoprodutos.model.Produto;
import com.example.manutencaoprodutos.model.ProdutoDTO;
import com.example.manutencaoprodutos.repository.ProdutosRepository;

@Service
public class ManutencaoProdutoService {
	
	private static final Logger log = LoggerFactory.getLogger(ManutencaoProdutoService.class);

	@Autowired
	private ProdutosRepository produtosRepository;
	
	@Autowired
	private ConverterProduto converter;

	public ResponseEntity<Object> getProdutos() {
		
		List<Produto> produtos = produtosRepository.findAll();
		
		Collections.sort(produtos, Comparator.comparing(Produto::getNome));
		
		if (produtos != null && !produtos.isEmpty()) {
			log.info("END getProdutos Produtos: {}", produtos);
			List<ProdutoDTO> produtoDTO = converter.converterProduto(produtos);
			return new ResponseEntity<>(produtoDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	public ResponseEntity<Object> getProdutoById(Long id) {
		
		Optional<Produto> produto = produtosRepository.findById(id);
		
		if (produto != null) {
			log.info("END getProdutoById Produto: {}", produto);
			return new ResponseEntity<>(produto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
		
	public ResponseEntity<Object> getProduto(String param, String value) {

		List<Produto> produto = new ArrayList<>();
		if(param != null && value != null) {
			
			if(param.equalsIgnoreCase("nomeProduto")) {
				
				produto = produtosRepository.findByNomeContainingIgnoreCase(value);
				
			}else if(param.equalsIgnoreCase("categoria")) {
						
				produto = produtosRepository.findByCategoria(value.equalsIgnoreCase("Perecivel") ? EnumCategoria.PERECIVEL : EnumCategoria.NAO_PERECIVEL);
				
			}else {
				log.error("Parâmetros não permitido param: {}, value: {}", param, value);
				return new ResponseEntity<>("Parâmetros não permitido", HttpStatus.BAD_REQUEST);
			}
			
		}else {
			log.error("Parâmetros em Branco param: {}, value: {}", param, value);
			return new ResponseEntity<>("Parâmetros em branco", HttpStatus.BAD_REQUEST);
		}

		if (produto != null && !produto.isEmpty()) {
			Collections.sort(produto, Comparator.comparing(Produto::getNome));
			List<ProdutoDTO> produtoDTO = converter.converterProduto(produto);
			return new ResponseEntity<>(produtoDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	public ResponseEntity<Object> creatProdutos(Produto produto) {

		if (produto.getNome() != null && produto.getCategoria() != null && produto.getValor() != null) {
			log.info("Created creatProdutos Produtos: {}", produto);
			produtosRepository.save(produto);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<Object> updateProdutos(Long id, Produto updateproduto) {

		Optional<Produto> produto = produtosRepository.findById(id);
		
		if (produto != null) {
			log.info("Update updateProdutos ID: {}, Produtos: {}", id, produto);
			updateproduto.setId(produto.get().getId());
			return new ResponseEntity<>(produtosRepository.save(updateproduto), HttpStatus.OK);
		}else {
			log.error("Erro no método updateProdutos ID: {}, Produtos: {}", id, produto);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<Object> deleteProdutos(Long id) {

		Optional<Produto> produto = produtosRepository.findById(id);
		
		if (produto != null) {
			log.info("Update updateProdutos ID: {}, Produtos: {}", id, produto);
			produtosRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			log.error("Erro no método deleteProdutos ID: {}, Produtos: {}", id, produto);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	} 
}
