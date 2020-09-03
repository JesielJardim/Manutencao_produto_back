package com.example.manutencaoprodutos.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table ;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table (name = "produtos")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto implements Serializable {

	private static final long serialVersionUID = 8621858893196804955L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
    private long id;	
	
    private String nome;
    
    private String categoria;
    
    @Column(name="valor", precision = 19, scale = 2) 
    private BigDecimal valor;
	
}
