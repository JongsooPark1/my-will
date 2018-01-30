INSERT INTO USER (id, user_id, password, name, email) VALUES (1, 'will', 'test', 'pjs', 'will@naver.com');
INSERT INTO USER (id, user_id, password, name, email) VALUES (2, 'will11', 'test', 'pjs11', 'will11@naver.com');

INSERT INTO QUESTION (id, writer_id, title, contents, create_date) VALUES (1, 1, 'test_title1111', 'test_contents1111', CURRENT_TIMESTAMP());
INSERT INTO QUESTION (id, writer_id, title, contents, create_date) VALUES (2, 2, 'test_title2222', 'test_contents2222', CURRENT_TIMESTAMP());
// 두번째 2는 2번 사용자와 맺는다는 의미