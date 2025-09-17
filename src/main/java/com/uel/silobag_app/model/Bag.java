package com.uel.silobag_app.model;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.uel.silobag_app.model.dto.BagAddDTO;
import com.uel.silobag_app.model.enums.TipoProduto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_BAG")
public class Bag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "VOLUME")
	private Double volume;
	
	@Column(name = "CAPACIDADE")
	private Double capacidade;
	
	@Column(name = "CODIGO")
	private Integer codigo;
	
	@Column(name = "PRODUTO")
	@Enumerated(EnumType.STRING)
	private TipoProduto produto;
	
	
	@ManyToOne
	@JoinColumn(name = "OPERACAO_ID")
	private Operacao operacao;
	
	@Column(name = "DATA_CADASTRO")
	@CreationTimestamp
	private Date dataCadastro;
	
	public Bag() {
		
	}
	
	public Bag(BagAddDTO dadosCadastro) {
		this.capacidade = dadosCadastro.capacidade();
		this.volume = dadosCadastro.volume();
		this.codigo = dadosCadastro.codigo();
		this.produto = dadosCadastro.produto();
	}

	public Bag(Long id, Double volume, Double capacidade, Integer codigo, TipoProduto produto, Date dataCadastro) {
		super();
		this.id = id;
		this.volume = volume;
		this.capacidade = capacidade;
		this.codigo = codigo;
		this.produto = produto;
		this.dataCadastro = dataCadastro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public Double getCapacidade() {
		return capacidade;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public TipoProduto getProduto() {
		return produto;
	}

	public void setProduto(TipoProduto produto) {
		this.produto = produto;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}
	
	public Operacao getOperacao() {
		return this.operacao;
	}
	
	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
	}
	
	
}
