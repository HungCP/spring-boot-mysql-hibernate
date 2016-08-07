INSERT INTO user (ma, email, password_hash, role, ho, ten, count)
VALUES ('AD1', 'demo@localhost', '$2a$10$ebyC4Z5WtCXXc.HGDc1Yoe6CLFzcntFmfse6/pTj7CeDY5I05w16C', 'ADMIN', 'Quản trị viên', 'Quản trị viên', 0);
INSERT INTO user (ma, email, password_hash, role, ho, ten, count)
VALUES ('GV1', 'hung@dtu.vn', '$2a$10$ebyC4Z5WtCXXc.HGDc1Yoe6CLFzcntFmfse6/pTj7CeDY5I05w16C', 'GIAO_VIEN', 'Huỳnh Thế', 'Hùng', 0);
INSERT INTO user (ma, email, password_hash, role, ho, ten, count)
VALUES ('SV1', 'hung@gmail.com', '$2a$10$ebyC4Z5WtCXXc.HGDc1Yoe6CLFzcntFmfse6/pTj7CeDY5I05w16C', 'USER', 'Cao Phi', 'Hùng', 0);
INSERT INTO user (ma, email, password_hash, role, ho, ten, count)
VALUES ('SV2', 'viet@dtu.vn', '$2a$10$ebyC4Z5WtCXXc.HGDc1Yoe6CLFzcntFmfse6/pTj7CeDY5I05w16C', 'USER', 'Hoàng Quốc', 'Việt', 0);
INSERT INTO user (ma, email, password_hash, role, ho, ten, count)
VALUES ('SV3', 'hoa@dn.vn', '$2a$10$ebyC4Z5WtCXXc.HGDc1Yoe6CLFzcntFmfse6/pTj7CeDY5I05w16C', 'USER', 'Nguyễn Diệu', 'Hoa', 0);
INSERT INTO user (ma, email, password_hash, role, ho, ten, count)
VALUES ('SV4', 'chinh@dng.vn', '$2a$10$ebyC4Z5WtCXXc.HGDc1Yoe6CLFzcntFmfse6/pTj7CeDY5I05w16C', 'USER', 'Nguyễn Minh', 'Chính', 0);
INSERT INTO user (ma, email, password_hash, role, ho, ten, count)
VALUES ('SV5', 'ky@gmail.com', '$2a$10$ebyC4Z5WtCXXc.HGDc1Yoe6CLFzcntFmfse6/pTj7CeDY5I05w16C', 'USER', 'Nguyễn Quang', 'Kỳ', 0);
INSERT INTO user (ma, email, password_hash, role, ho, ten, count)
VALUES ('SV6', 'chi@gmail.com', '$2a$10$ebyC4Z5WtCXXc.HGDc1Yoe6CLFzcntFmfse6/pTj7CeDY5I05w16C', 'USER', 'Dương Hoàng', 'Chi', 0);
INSERT INTO user (ma, email, password_hash, role, ho, ten, count)
VALUES ('SV7', 'ly@gmail.com', '$2a$10$ebyC4Z5WtCXXc.HGDc1Yoe6CLFzcntFmfse6/pTj7CeDY5I05w16C', 'USER', 'Nguyễn Thị', 'Ly', 0);
INSERT INTO user (ma, email, password_hash, role, ho, ten, count)
VALUES ('SV8', 'chuong@gmail.com', '$2a$10$ebyC4Z5WtCXXc.HGDc1Yoe6CLFzcntFmfse6/pTj7CeDY5I05w16C', 'USER', 'Phạm Minh', 'Chương', 0);
INSERT INTO user (ma, email, password_hash, role, ho, ten, count)
VALUES ('SV9', 'phuong@gmail.com', '$2a$10$ebyC4Z5WtCXXc.HGDc1Yoe6CLFzcntFmfse6/pTj7CeDY5I05w16C', 'USER', 'Phạm Quang', 'Phương', 0);
INSERT INTO user (ma, email, password_hash, role, ho, ten, count)
VALUES ('SV10', 'uyen@gmail.com', '$2a$10$ebyC4Z5WtCXXc.HGDc1Yoe6CLFzcntFmfse6/pTj7CeDY5I05w16C', 'USER', 'Nguyễn Công', 'Uyên', 0);
INSERT INTO user (ma, email, password_hash, role, ho, ten, count)
VALUES ('SV11', 'an@gmail.com', '$2a$10$ebyC4Z5WtCXXc.HGDc1Yoe6CLFzcntFmfse6/pTj7CeDY5I05w16C', 'USER', 'Nguyễn Long', 'Ẩn', 0);
INSERT INTO user (ma, email, password_hash, role, ho, ten, count)
VALUES ('SV12', 'linh@gmail.com', '$2a$10$ebyC4Z5WtCXXc.HGDc1Yoe6CLFzcntFmfse6/pTj7CeDY5I05w16C', 'USER', 'Đỗ Trường', 'Linh', 0);

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
INSERT INTO course (ma, name, course_status)
VALUES ('ĐTK29', 'Điện tử K29', 'MO');

INSERT INTO user_course (user_Id, course_Id)
VALUES (2, 1);
INSERT INTO user_course (user_Id, course_Id)
VALUES (3, 1);
INSERT INTO user_course (user_Id, course_Id)
VALUES (4, 1);
INSERT INTO user_course (user_Id, course_Id)
VALUES (5, 1);
INSERT INTO user_course (user_Id, course_Id)
VALUES (6, 1);
INSERT INTO user_course (user_Id, course_Id)
VALUES (7, 1);
INSERT INTO user_course (user_Id, course_Id)
VALUES (2, 2);
INSERT INTO user_course (user_Id, course_Id)
VALUES (3, 2);
INSERT INTO user_course (user_Id, course_Id)
VALUES (4, 2);
INSERT INTO user_course (user_Id, course_Id)
VALUES (6, 2);
INSERT INTO user_course (user_Id, course_Id)
VALUES (7, 2);
INSERT INTO user_course (user_Id, course_Id)
VALUES (8, 2);
INSERT INTO user_course (user_Id, course_Id)
VALUES (2, 3);
INSERT INTO user_course (user_Id, course_Id)
VALUES (3, 3);
INSERT INTO user_course (user_Id, course_Id)
VALUES (4, 3);
INSERT INTO user_course (user_Id, course_Id)
VALUES (5, 3);
INSERT INTO user_course (user_Id, course_Id)
VALUES (6, 3);
INSERT INTO user_course (user_Id, course_Id)
VALUES (7, 3);
INSERT INTO user_course (user_Id, course_Id)
VALUES (10, 3);
INSERT INTO user_course (user_Id, course_Id)
VALUES (2, 4);
INSERT INTO user_course (user_Id, course_Id)
VALUES (11, 4);
INSERT INTO user_course (user_Id, course_Id)
VALUES (12, 4);
INSERT INTO user_course (user_Id, course_Id)
VALUES (13, 4);
INSERT INTO user_course (user_Id, course_Id)
VALUES (14, 4);