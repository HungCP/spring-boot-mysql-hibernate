INSERT INTO user (email, password_hash, role)
VALUES ('demo@localhost', '$2a$10$ebyC4Z5WtCXXc.HGDc1Yoe6CLFzcntFmfse6/pTj7CeDY5I05w16C', 'ADMIN');
INSERT INTO user (email, password_hash, role)
VALUES ('linh@axon', '$2a$10$86pT2qBuFnlmVT2Ezv57puH2XI2aKkD02KH4nRZaZBt5DCcQjYWkC', 'USER');
INSERT INTO user (email, password_hash, role)
VALUES ('anh@dtu.vn', '$2a$10$ebyC4Z5WtCXXc.HGDc1Yoe6CLFzcntFmfse6/pTj7CeDY5I05w16C', 'GIAO_VIEN');

INSERT INTO classroom (ma, name)
VALUES ('KHMTK28', 'Khoa học máy tính K28');
INSERT INTO classroom (ma, name)
VALUES ('KHMTK29', 'Khoa học máy tính K29');
INSERT INTO classroom (ma, name)
VALUES ('XDK29', 'Xây dựng K29');