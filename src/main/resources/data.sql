INSERT INTO user (email, password_hash, role)
VALUES ('demo@localhost', '$2a$10$ebyC4Z5WtCXXc.HGDc1Yoe6CLFzcntFmfse6/pTj7CeDY5I05w16C', 'ADMIN');
INSERT INTO user (email, password_hash, role)
VALUES ('anh@dtu.vn', '$2a$10$ebyC4Z5WtCXXc.HGDc1Yoe6CLFzcntFmfse6/pTj7CeDY5I05w16C', 'GIAO_VIEN');
INSERT INTO user (email, password_hash, role)
VALUES ('linh@dtu', '$2a$10$86pT2qBuFnlmVT2Ezv57puH2XI2aKkD02KH4nRZaZBt5DCcQjYWkC', 'USER');
INSERT INTO user (email, password_hash, role)
VALUES ('dung@dtu', '$2a$10$86pT2qBuFnlmVT2Ezv57puH2XI2aKkD02KH4nRZaZBt5DCcQjYWkC', 'USER');
INSERT INTO user (email, password_hash, role)
VALUES ('phu@dng', '$2a$10$86pT2qBuFnlmVT2Ezv57puH2XI2aKkD02KH4nRZaZBt5DCcQjYWkC', 'USER');

INSERT INTO classroom (ma, name)
VALUES ('A141', 'Phòng A141 Khu A');
INSERT INTO classroom (ma, name)
VALUES ('A142', 'Phòng A142 Khu A');
INSERT INTO classroom (ma, name)
VALUES ('F101', 'Phòng F101 Khu F');

INSERT INTO course (ma, name, course_status)
VALUES ('KHMTK28', 'Khoa học máy tính K28', 'MO');
INSERT INTO course (ma, name, course_status)
VALUES ('KHMTK29', 'Khoa học máy tính K29', 'MO');
INSERT INTO course (ma, name, course_status)
VALUES ('XDK29', 'Xây dựng K29', 'MO');

INSERT INTO user_course (user_Id, course_Id, date, attendanceStatus)
VALUES (1, 1,'2015-12-15 13:01:44' ,'THAM_GIA');
