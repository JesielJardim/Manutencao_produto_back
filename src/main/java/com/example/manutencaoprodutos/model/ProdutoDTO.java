package com.example.manutencaoprodutos.model;

import java.io.Serializable;
import javax.persistence.Entity;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@EnableJpaRepositories
public class ProdutoDTO implements Serializable{

	private static final long serialVersionUID = 5403569274077200104L;

	private long id;	
	
    private String nome;
    
    private String categoria;

    private String valor;
}
