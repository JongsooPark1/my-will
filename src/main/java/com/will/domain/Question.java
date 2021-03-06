package com.will.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Question {
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne		// Question과 User의 상관 관계
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))		//데이터 베이스 테이블에 포인키 이름을 지정해준다
	private User writer;		//User 객체의 primary key값이 들어가도록 설정됨
	private String title;
	
	@Lob		// String은 Column의 길이가 255인데 Lob 애노테이션을 이용하면 많은 길이를 담을 수 있다
	private String contents;
	
	private LocalDateTime createDate;
	
	@OneToMany(mappedBy = "question")
	@OrderBy("id ASC")		// 답변 id에 오름차순으로 data 정렬
	private List<Answer> answers;
	
	public Question() { }			// JPA 에서는 mapping을 할 때 default 생성자를 필요로한다
	
	public Question(User writer, String title, String contents) {
		super();		// 왜 있는거야?
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.createDate = LocalDateTime.now();
	}

	public User getWriter() {
		return writer;
	}
	
	public void setWriter(User writer) {
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

	public String getFormattedCreateDate() {
		if (createDate == null) {
			return "";
		}
		return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd.hh:mm:ss"));
	}

	public void update(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}

	public boolean isSameWriter(User loginUser) {
		return this.writer.equals(loginUser);
	}
	
	@Override
	public String toString() {
		return "Question [writer=" + writer + ", title=" + title + ", contents=" + contents + "]";
	}
	
	
}
