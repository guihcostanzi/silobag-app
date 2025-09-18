package com.uel.silobag_app.model;

import java.sql.Date;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import com.uel.silobag_app.enums.TipoOperacao;
import com.uel.silobag_app.model.dto.OperacaoAddDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_OPERACAO")
public class Operacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@UuidGenerator
    @Column(name = "UID")
    private UUID uid;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "TIPO")
	@Enumerated(EnumType.STRING)
	private TipoOperacao tipo;
	
	@Column(name = "DATA_CADASTRO")
	@CreationTimestamp
	private Date dataCadastro;
	
	public Operacao() {
		
	}
	
	public Operacao(OperacaoAddDTO operacao) {
		this.nome = operacao.nome();
		this.tipo = operacao.tipo();
	}

	public Operacao(Long id, String nome, TipoOperacao tipo, Date dataCadastro, UUID uuid) {
		super();
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
		this.dataCadastro = dataCadastro;
		this.uid = uuid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoOperacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoOperacao tipo) {
		this.tipo = tipo;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public UUID getUid() {
		return this.uid;
	}
	
	
}
