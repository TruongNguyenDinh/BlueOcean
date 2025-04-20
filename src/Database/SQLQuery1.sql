-- 1. Tạo Database
CREATE DATABASE appchat;
GO
USE appchat;
GO

-- 2. Tạo bảng Users 
CREATE TABLE users (
    user_id INT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR(50) UNIQUE NOT NULL,
    fullname NVARCHAR(50) NOT NULL,  
    nickname NVARCHAR(50) NOT NULL,
    phone NVARCHAR(10) NOT NULL,  
    address NVARCHAR(255),  -- Thêm địa chỉ
    gender BIT,  -- 1: Nam, 0: Nữ
    password NVARCHAR(255) NOT NULL,
    email NVARCHAR(100) UNIQUE NOT NULL,
    status BIT , -- 1: Online, 0: Offline 
    created_at DATETIME DEFAULT GETDATE()
);
GO

CREATE TABLE notes (
    id INT IDENTITY(1,1) PRIMARY KEY,
    user_id INT NOT NULL,
    content NVARCHAR(MAX) NOT NULL,
    reminder_time DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
GO
-- Bảng lưu thông tin trận đấu
CREATE TABLE game_matches (
    match_id INT IDENTITY(1,1) PRIMARY KEY,
    player1_id INT NOT NULL,
    player2_id INT NOT NULL,
    winner_id INT NULL, -- NULL nếu hòa
    match_date DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (player1_id) REFERENCES users(user_id),
    FOREIGN KEY (player2_id) REFERENCES users(user_id),
    FOREIGN KEY (winner_id) REFERENCES users(user_id)
);

-- Bảng lưu điểm xếp hạng của người chơi
CREATE TABLE player_rankings (
    user_id INT PRIMARY KEY,
    points INT DEFAULT 1000, -- Điểm xếp hạng, mặc định 1000
    wins INT DEFAULT 0,
    losses INT DEFAULT 0,
    draws INT DEFAULT 0,
    last_played DATETIME,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);


INSERT INTO users (username, fullname, nickname, phone, address, gender, password, email, status)
VALUES 
('XuanNhat', 'Nguyen Xuan Nhat', 'Nhat', '0979876535', 'Hanoi, Vietnam', 1, '123', 'xuannhat@gmail.com', 1),
('bob', 'An Hoang Anh', 'Hoang', '0987654327', 'Ho Chi Minh City, Vietnam', 1, '456', 'bob@gmail.com', 0),
('PhucLe', 'Le The Phuc', 'Phuc', '0345678917', 'Da Nang, Vietnam', 1, '789', 'phucle@gmail.com', 0),
('DinhTruong', 'Nguyen Dinh Truong', 'Truong', '0978899019', 'Hai Phong, Vietnam', 1, '987', 'dinhtruong@gmail.com', 1),
('MaiLe', 'Le Mai', 'Mai', '0901122336', 'Can Tho, Vietnam', 0, '563', 'maile@gmail.com', 1);
GO
INSERT INTO users (username, fullname, nickname, phone, address, gender, password, email, status)
VALUES 
('Truong1', 'Le Mai', 'Mai', '0901122332', 'Can Tho, Vietnam', 0, 'Truong1', 'truo1ng@gmail.com', 1),
('Minh', 'Le Minh', 'Minh', '0901122331', 'Can Tho, Vietnam', 0, 'Minh', 'minh@gmail.com', 1),

('Vuong', 'Le Vuong', 'Vuong', '0901122533', 'Can Tho, Vietnam', 0, 'Vuong', 'vuong@gmail.com', 1),
('Truong', 'Le Mai', 'Mai', '0901122332', 'Can Tho, Vietnam', 0, 'Truong', 'truong@gmail.com', 1),
('Truong1124', N'Ngô Thượng Nhung', N'Kiếm Ma', '0362361299', N'Hải Phòng', 0, 'Truong123', 'truong220@gmail.com',0);
GO
SELECT*FROM users;
DELETE FROM users;


