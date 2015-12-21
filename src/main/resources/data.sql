INSERT INTO user (email, password_hash, role, ho, ten)
VALUES ('demo@localhost', '$2a$10$ebyC4Z5WtCXXc.HGDc1Yoe6CLFzcntFmfse6/pTj7CeDY5I05w16C', 'ADMIN', 'Quản trị viên', 'Quản trị viên');
INSERT INTO user (email, password_hash, role, ho, ten)
VALUES ('anh@dtu.vn', '$2a$10$ebyC4Z5WtCXXc.HGDc1Yoe6CLFzcntFmfse6/pTj7CeDY5I05w16C', 'GIAO_VIEN', 'Huỳnh Thế', 'Hùng');
INSERT INTO user (email, password_hash, role, ho, ten)
VALUES ('linh@dtu', '$2a$10$ebyC4Z5WtCXXc.HGDc1Yoe6CLFzcntFmfse6/pTj7CeDY5I05w16C', 'USER', 'Trương', 'Linh');
INSERT INTO user (email, password_hash, role, ho, ten)
VALUES ('dung@dtu', '$2a$10$ebyC4Z5WtCXXc.HGDc1Yoe6CLFzcntFmfse6/pTj7CeDY5I05w16C', 'USER', 'Cao Minh', 'Vương');
INSERT INTO user (email, password_hash, role, ho, ten)
VALUES ('phu@dng', '$2a$10$ebyC4Z5WtCXXc.HGDc1Yoe6CLFzcntFmfse6/pTj7CeDY5I05w16C', 'USER', 'Trần', 'Tấn');

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

INSERT INTO user_course (user_Id, course_Id)
VALUES (2, 1);
INSERT INTO user_course (user_Id, course_Id)
VALUES (3, 1);
INSERT INTO user_course (user_Id, course_Id)
VALUES (4, 1);
INSERT INTO user_course (user_Id, course_Id)
VALUES (2, 2);
INSERT INTO user_course (user_Id, course_Id)
VALUES (3, 2);
INSERT INTO user_course (user_Id, course_Id)
VALUES (2, 3);
INSERT INTO user_course (user_Id, course_Id)
VALUES (5, 3);
