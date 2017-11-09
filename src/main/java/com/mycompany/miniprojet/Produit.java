
package com.mycompany.miniprojet;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "produit")
public class Produit
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "reference", length = 255, nullable = true)
    private String reference;
    @Column(name = "description", length = 255, nullable = true)
    private String description;
    

    @Column(name = "prixUnitaire", nullable = true)
    private float prixUnitaire;

    @ManyToOne
    @JoinColumn(name = "famille_id", foreignKey = @ForeignKey(name = "Famille_ID_FK"))
    private Famille famille;
     @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "produit_etiquette",
            joinColumns = {
                @JoinColumn(name = "produit_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "etiquette_id")})
    private List<Etiquette> etiquettes = new ArrayList<>();
     
   
     @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "produit_commande",
            joinColumns = {
                @JoinColumn(name = "produit_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "commande_id")})
    private List<Commande> commandes = new ArrayList<>();


    // Getters and Setters here...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(float prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Famille getFamille() {
        return famille;
    }

    public void setFamille(Famille famille) {
        this.famille = famille;
    }

    public List<Etiquette> getEtiquettes() {
        return etiquettes;
    }

    public void setEtiquettes(List<Etiquette> etiquettes) {
        this.etiquettes = etiquettes;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }

    


    

}

