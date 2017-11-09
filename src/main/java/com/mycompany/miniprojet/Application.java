package com.mycompany.miniprojet;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Application class.
 *
 * @author Nafaa Friaa (nafaa.friaa@isetjb.rnu.tn)
 */
public class Application {

    /**
     * Attribute declaration for factory to share between methods.
     */
    private static SessionFactory factory;

    public static void main(String[] args) {
        System.out.println("JavaSE + Maven + Hibernate + MySQL : Many to One Association");

        // Open connection  pool
        factory = HibernateUtil.getSessionFactory();

        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            
            
            //one to many entre Famille et produit

            // new Famille
            Famille famille_a = new Famille();
            famille_a.setNom("famille a");
            famille_a.setDescription("fa a");
            session.save(famille_a);

            // new Famille
            Famille famille_b = new Famille();
            famille_b.setNom("famille b");
            famille_b.setDescription("fa b");
            session.save(famille_b);

            // new Produit
            Produit produit_x = new Produit();
            produit_x.setReference("Produit x");
            produit_x.setDescription("gfdfdf x");
            produit_x.setPrixUnitaire(1234);
            produit_x.setFamille(famille_a);
            session.save(produit_x);

            // new Produit
            Produit produit_y = new Produit();
            produit_y.setReference("Produit y");
            produit_y.setDescription("gfdfdf y");
            produit_y.setPrixUnitaire(1234);
            produit_y.setFamille(famille_a);
            session.save(produit_y);

            // new Produit
            Produit produit_z = new Produit();
            produit_z.setReference("Produit z");
            produit_z.setDescription("gfdfdf");
            produit_z.setPrixUnitaire(1234);
            produit_z.setFamille(famille_a);
            session.save(produit_z);

            
            
            
            
            //many to many entre Etiquette et produit
            //new Etiquette
            Etiquette etiquette_a = new Etiquette();
            etiquette_a.setNom("Cat a");
            etiquette_a.setCouleur("Cat a");
            session.save(etiquette_a);
            // new produit
            Produit produit_h = new Produit();
            produit_h.setReference("Produit z");
            produit_h.setDescription("gfdfdf");
            produit_h.setPrixUnitaire(1234);
            produit_h.setFamille(famille_a);
            produit_h.getEtiquettes().add(etiquette_a);
            session.save(produit_h);

            // new product
            Produit produit_w = new Produit();
            produit_w.setReference("Produit z");
            produit_w.setDescription("gfdfdf");
            produit_w.setPrixUnitaire(1234);
            produit_w.setFamille(famille_a);
            session.save(produit_w);

            // new Etiquette
            Etiquette etiquette_g = new Etiquette();
            etiquette_g.setNom("Cat c");
            etiquette_a.setCouleur("Cat c");
            etiquette_g.getProduits().add(produit_w);
            session.save(etiquette_g);

            
            
            //many to many entre produit et Commande
            //new Commande
            Commande commande_a = new Commande();
            commande_a.setDateCommande(java.sql.Date.valueOf("2013-09-04"));
            session.save(commande_a);
            // new produit
            Produit produit_aa = new Produit();
            produit_aa.setReference("Produit aa");
            produit_aa.setDescription("gfdfdf");
            produit_aa.setPrixUnitaire(1234);
            produit_aa.setFamille(famille_a);
            produit_aa.getEtiquettes().add(etiquette_a);
            produit_aa.getCommandes().add(commande_a);
            session.save(produit_aa);

            // new product
            Produit produit_ww = new Produit();
            produit_ww.setReference("Produit zz");
            produit_ww.setDescription("gfdfdf");
            produit_ww.setPrixUnitaire(1234);
            produit_ww.setFamille(famille_a);
            session.save(produit_ww);

            // new Commande
            Commande commande_g = new Commande();
            commande_g.setDateCommande(java.sql.Date.valueOf("2013-09-04"));
            commande_g.getProduits().add(produit_ww);
            session.save(commande_g);
            
            
            
            //one to many entre Client et Commande

            // new Client
            Client client_a = new Client();
             client_a.setAdresse("Client a");
            client_a.setNom("Client a");
            session.save(client_a);

            // new Client
            Client client_b = new Client();
             client_b.setAdresse("Client b");
            client_b.setNom("Client b");
            session.save(client_b);

            // new Commande
            Commande commande_x = new Commande();
            commande_x.setDateCommande(java.sql.Date.valueOf("2017-11-07"));
            commande_x.setClient(client_a);
            session.save(commande_x);

            // new Commande
            Commande commande_y = new Commande();
            commande_y.setDateCommande(java.sql.Date.valueOf("2017-01-22"));
            commande_y.setClient(client_a);
            session.save(commande_y);
            // new Commande
            Commande commande_z = new Commande();
            commande_z.setDateCommande(java.sql.Date.valueOf("2017-04-10"));
            commande_z.setClient(client_b);
            session.save(commande_z);

            // produit list by executing HQL Query
            List produits = session.createQuery("FROM Produit").list();

            for (Iterator iterator = produits.iterator(); iterator.hasNext();) {
                Produit produit = (Produit) iterator.next();
                System.out.print("ID: " + produit.getId());
                System.out.print(" ===> Reference: " + produit.getReference());
                System.out.print(" ===> Description: " + produit.getDescription());
                System.out.print(" ===> PrixUnitaire: " + produit.getPrixUnitaire());
                System.out.println(" ===> Famille: " + produit.getFamille().getNom());
            }
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            //e.printStackTrace();
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            session.close();
        }

        // Cleaning up connection pool
        factory.close();
    }
}
