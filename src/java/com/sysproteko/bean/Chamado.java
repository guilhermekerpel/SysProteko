/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysproteko.bean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "chamado")
@NamedQueries({
    @NamedQuery(name = "Chamado.findAll", query = "SELECT c FROM Chamado c"),
    @NamedQuery(name = "Chamado.findById", query = "SELECT c FROM Chamado c WHERE c.nChamado = :nChamado"),
})
public class Chamado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "nChamado")
    private Integer nChamado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "dtAbertura")
    private String dtAbertura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "assunto")
    private String assunto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "departamento")
    private String departamento;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "problemaRelatado")
    private String problemaRelatado;
    @Lob
    @Size(max = 65535)
    @Column(name = "solucao")
    private String solucao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "emailSolicitante")
    private String emailSolicitante;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "contato")
    private String contato;
    @Size(max = 50)
    @Column(name = "dtConclusao")
    private String dtConclusao;
    @JoinColumn(name = "id_unidade", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Unidade unidade;
    @JoinColumn(name = "id_tecnico", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tecnico tecnico;

    public Chamado() {
    }

    public Chamado(Integer nChamado) {
        this.nChamado = nChamado;
    }

    public Chamado(Integer nChamado, String dtAbertura, String assunto, String status, String departamento, String problemaRelatado, String emailSolicitante, String contato) {
        this.nChamado = nChamado;
        this.dtAbertura = dtAbertura;
        this.assunto = assunto;
        this.status = status;
        this.departamento = departamento;
        this.problemaRelatado = problemaRelatado;
        this.emailSolicitante = emailSolicitante;
        this.contato = contato;
    }

    public Integer getNChamado() {
        return nChamado;
    }

    public void setNChamado(Integer nChamado) {
        this.nChamado = nChamado;
    }

    public String getDtAbertura() {
        return dtAbertura;
    }

    public void setDtAbertura(String dtAbertura) {
        this.dtAbertura = dtAbertura;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProblemaRelatado() {
        return problemaRelatado;
    }

    public void setProblemaRelatado(String problemaRelatado) {
        this.problemaRelatado = problemaRelatado;
    }

    public String getSolucao() {
        return solucao;
    }

    public void setSolucao(String solucao) {
        this.solucao = solucao;
    }

    public String getEmailSolicitante() {
        return emailSolicitante;
    }

    public void setEmailSolicitante(String emailSolicitante) {
        this.emailSolicitante = emailSolicitante;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getDtConclusao() {
        return dtConclusao;
    }

    public void setDtConclusao(String dtConclusao) {
        this.dtConclusao = dtConclusao;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nChamado != null ? nChamado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chamado)) {
            return false;
        }
        Chamado other = (Chamado) object;
        if ((this.nChamado == null && other.nChamado != null) || (this.nChamado != null && !this.nChamado.equals(other.nChamado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sysproteko.bean.Chamado[ nChamado=" + nChamado + " ]";
    }
    
}
