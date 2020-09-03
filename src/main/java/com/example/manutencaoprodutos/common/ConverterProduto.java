package com.example.manutencaoprodutos.common;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.manutencaoprodutos.model.Produto;
import com.example.manutencaoprodutos.model.ProdutoDTO;

@Service
public class ConverterProduto {
	
	public List<ProdutoDTO> converterProduto(List<Produto> ListaProduto) {
		
		List<ProdutoDTO> ListaprodutoDTO = new ArrayList<>();
		
		for (Produto item : ListaProduto) {
			ProdutoDTO produtoDTO = new ProdutoDTO();
			
			produtoDTO.setId(item.getId());
			produtoDTO.setNome(item.getNome());
			produtoDTO.setCategoria(item.getCategoria());
			DecimalFormat decFormat = new DecimalFormat("0.00");
			produtoDTO.setValor(decFormat.format(item.getValor()));
			
			ListaprodutoDTO.add(produtoDTO);
			
		}	
			
		return ListaprodutoDTO;
	}
	
	public ProdutoDTO converterProdutoById(Optional<Produto> produto) {

			ProdutoDTO produtoDTO = new ProdutoDTO();
			
			produtoDTO.setId(produto.get().getId());
			produtoDTO.setNome(produto.get().getNome());
			produtoDTO.setCategoria(produto.get().getCategoria());
			DecimalFormat decFormat = new DecimalFormat("0.00");
			produtoDTO.setValor(decFormat.format(produto.get().getValor()));
			
		return produtoDTO;
	}
	
	public Produto converterToEntity(ProdutoDTO produtoDto) {

		Produto produto = new Produto();
		
		produto.setId(produtoDto.getId());
		produto.setNome(produtoDto.getNome());
		produto.setCategoria(produtoDto.getCategoria());
		BigDecimal valor = new BigDecimal(produtoDto.getValor().replace('.', ','));
		produto.setValor(valor);
		
	return produto;
}
}
