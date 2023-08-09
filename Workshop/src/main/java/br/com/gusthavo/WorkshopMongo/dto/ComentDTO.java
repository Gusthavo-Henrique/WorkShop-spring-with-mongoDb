package br.com.gusthavo.WorkshopMongo.dto;

import java.io.Serializable;
import java.time.Instant;

public class ComentDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String text;
	private Instant date;
	private AuthorDTO author;

	public ComentDTO() {

	}

	public ComentDTO(String text, Instant date, AuthorDTO author) {
		super();
		this.text = text;
		this.date = date;
		this.author = author;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}

}
