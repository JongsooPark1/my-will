package com.will.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Question {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)			// 할 필요 있나?
	private String writer;
	
	private String title;
	private String contents;
	
	public String getWriter() {
		return writer;
	}
	
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContents() {
		return contents;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "Question [writer=" + writer + ", title=" + title + ", contents=" + contents + "]";
	}
}
