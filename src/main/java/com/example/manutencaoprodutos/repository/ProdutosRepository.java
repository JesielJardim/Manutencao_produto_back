package com.example.manutencaoprodutos.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.manutencaoprodutos.model.Produto;

public interface ProdutosRepository extends JpaRepository<Produto, Long>{

	List<Produto> findByNomeContainingIgnoreCase(String nomeProduto);
	
	List<Produto> findByCategoria(String categoria);

}
