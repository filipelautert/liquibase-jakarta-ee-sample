package org.liquibase.jakartaeesample;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.Serializable;
import java.util.List;

@RequestScoped
@Named
public class AuthorBean implements Serializable {
	
	private List<Author> authors;
	@PersistenceContext
	private EntityManager em;
	
	public void loadAuthors() {
		authors = em.createQuery("select a FROM Author a").getResultList();
	}
	
	public List<Author> getAuthors() {
		return authors;
	}

}