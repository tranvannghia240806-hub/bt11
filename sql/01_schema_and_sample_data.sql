-- SQL Server. Chạy toàn bộ file này một lần trong SSMS.
IF DB_ID('BookStore') IS NULL CREATE DATABASE BookStore;
GO
USE BookStore;
GO

CREATE TABLE books (
    bookid       INT IDENTITY(1,1) PRIMARY KEY,
    isbn         INT NULL,
    title        VARCHAR(200) NULL,
    publisher    VARCHAR(100) NULL,
    price        DECIMAL(6,2) NULL,
    description  TEXT NULL,
    publish_date DATE NULL,
    cover_image  VARCHAR(100) NULL,
    quantity     INT NULL
);

CREATE TABLE users (
    id          INT IDENTITY(1,1) PRIMARY KEY,
    email       VARCHAR(50) NOT NULL,
    fullname    NVARCHAR(50) NULL,
    phone       INT NULL,
    passwd      VARCHAR(32) NOT NULL,
    signup_date DATETIME NULL,
    last_login  DATETIME NULL,
    is_admin    BIT NULL
);

CREATE TABLE author (
    author_id     INT IDENTITY(1,1) PRIMARY KEY,
    author_name   VARCHAR(100) NULL,
    date_of_birth DATE NULL
);

CREATE TABLE book_author (
    bookid    INT NOT NULL REFERENCES books(bookid),
    author_id INT NOT NULL REFERENCES author(author_id),
    PRIMARY KEY (bookid, author_id)
);

CREATE TABLE rating (
    userid      INT NOT NULL REFERENCES users(id),
    bookid      INT NOT NULL REFERENCES books(bookid),
    rating      TINYINT NULL,
    review_text TEXT NULL,
    PRIMARY KEY (userid, bookid)
);

-- ===== Bảng mới cho giỏ hàng / đơn hàng =====
CREATE TABLE orders (
    order_id       INT IDENTITY(1,1) PRIMARY KEY,
    user_id        INT           NOT NULL REFERENCES users(id),
    receiver_name  NVARCHAR(100) NOT NULL,
    phone          VARCHAR(20)   NOT NULL,
    address        NVARCHAR(255) NOT NULL,
    note           NVARCHAR(255) NULL,
    total_amount   DECIMAL(12,2) NOT NULL,
    payment_method VARCHAR(10)   NOT NULL DEFAULT 'COD',
    status         INT           NOT NULL DEFAULT 0,
    order_date     DATETIME      NOT NULL DEFAULT GETDATE()
);

CREATE TABLE order_items (
    item_id    INT IDENTITY(1,1) PRIMARY KEY,
    order_id   INT           NOT NULL REFERENCES orders(order_id),
    bookid     INT           NOT NULL REFERENCES books(bookid),
    book_title NVARCHAR(200) NULL,
    quantity   INT           NOT NULL CHECK (quantity > 0),
    price      DECIMAL(6,2)  NOT NULL
);
GO

-- ===== Dữ liệu mẫu =====
-- Mật khẩu = 123456 (lưu MD5 vì cột passwd là varchar(32))
INSERT INTO users(email, fullname, phone, passwd, signup_date, is_admin) VALUES
 ('user@test.com',  N'Trần Văn Nghĩa', 912345678, LOWER(CONVERT(VARCHAR(32), HASHBYTES('MD5','123456'), 2)), GETDATE(), 0),
 ('admin@test.com', N'Quản trị viên',  900000000, LOWER(CONVERT(VARCHAR(32), HASHBYTES('MD5','123456'), 2)), GETDATE(), 1);

INSERT INTO books(isbn, title, publisher, price, description, publish_date, cover_image, quantity) VALUES
 (100001, 'Lap Trinh Java Co Ban',   'NXB Tre',         12.50, 'Giao trinh Java co ban',   '2022-01-10', 'book1.svg', 20),
 (100002, 'Lap Trinh Web Voi JSP',   'NXB Giao Duc',    15.00, 'Servlet, JSP va JPA',      '2022-03-15', 'book2.svg', 15),
 (100003, 'Co So Du Lieu SQL Server','NXB Khoa Hoc',    18.75, 'Thiet ke va truy van CSDL','2021-07-01', 'book3.svg', 10),
 (100004, 'Cau Truc Du Lieu',        'NXB Dai Hoc QG',  14.00, 'Cau truc du lieu va GT',   '2020-09-20', 'book4.svg',  8),
 (100005, 'Hibernate Thuc Chien',    'NXB Thong Tin',   22.00, 'JPA va Hibernate',         '2023-02-02', 'book5.svg',  5),
 (100006, 'Spring Boot Tu A Den Z',  'NXB Lao Dong',    25.00, 'Xay dung REST API',        '2023-05-05', 'book6.svg', 12),
 (100007, 'Thiet Ke Giao Dien Web',  'NXB My Thuat',    11.20, 'HTML CSS JavaScript',      '2021-11-11', 'book7.svg', 30),
 (100008, 'Phan Tich Thiet Ke HT',   'NXB Bach Khoa',   16.80, 'UML va quy trinh phan mem','2019-06-06', 'book8.svg',  3);
GO
