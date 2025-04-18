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
DROP TABLE users;

-- 3. Tạo bảng Chat Rooms
CREATE TABLE chat_rooms (
    room_id INT IDENTITY(1,1) PRIMARY KEY,
    room_name NVARCHAR(100) NOT NULL,
    created_at DATETIME DEFAULT GETDATE()
);
GO

-- 4. Tạo bảng Files
CREATE TABLE files (
    file_id INT IDENTITY(1,1) PRIMARY KEY,
    file_name NVARCHAR(255) NOT NULL,
    file_path NVARCHAR(500) NOT NULL,
    uploaded_by INT NOT NULL,
    uploaded_at DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (uploaded_by) REFERENCES users(user_id)
);
GO

-- 5. Tạo bảng Messages (sau khi chat_rooms đã tồn tại)
CREATE TABLE messages (
    message_id INT IDENTITY(1,1) PRIMARY KEY,
    sender_id INT NOT NULL,
    receiver_id INT NULL,  -- NULL nếu tin nhắn thuộc về nhóm
    room_id INT NULL,  -- NULL nếu tin nhắn riêng tư
    content NVARCHAR(MAX) NOT NULL,
    file_id INT NULL,  -- Nếu có file đính kèm
    sent_at DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (sender_id) REFERENCES users(user_id),
    FOREIGN KEY (receiver_id) REFERENCES users(user_id),
    FOREIGN KEY (room_id) REFERENCES chat_rooms(room_id),
    FOREIGN KEY (file_id) REFERENCES files(file_id)
);
GO

-- 6. Tạo bảng Room Members
CREATE TABLE room_members (
    member_id INT IDENTITY(1,1) PRIMARY KEY,
    user_id INT NOT NULL,
    room_id INT NOT NULL,
    joined_at DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (room_id) REFERENCES chat_rooms(room_id)
);
GO
<<<<<<< HEAD
-- 7. Tạo bảng Notes
CREATE TABLE notes (
    id INT IDENTITY(1,1) PRIMARY KEY,
    user_id INT NOT NULL,
    content NVARCHAR(MAX) NOT NULL,
    reminder_time DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
GO
-- 8. Thêm người dùng vào bảng users
=======
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

-- 7. Thêm người dùng vào bảng users
INSERT INTO users (username, fullname, nickname, phone, address, gender, password, email, status)
VALUES 
('XuanNhat', 'Nguyen Xuan Nhat', 'Nhat', '097987653', 'Hanoi, Vietnam', 1, '123', 'xuannhat@gmail.com', 1),
('bob', 'An Hoang Anh', 'Hoang', '098765432', 'Ho Chi Minh City, Vietnam', 1, '456', 'bob@gmail.com', 0),
('PhucLe', 'Le The Phuc', 'Phuc', '034567891', 'Da Nang, Vietnam', 1, '789', 'phucle@gmail.com', 0),
('DinhTruong', 'Nguyen Dinh Truong', 'Truong', '097889901', 'Hai Phong, Vietnam', 1, '987', 'dinhtruong@gmail.com', 1),
('MaiLe', 'Le Mai', 'Mai', '090112233', 'Can Tho, Vietnam', 0, '563', 'maile@gmail.com', 1);
GO
INSERT INTO users (username, fullname, nickname, phone, address, gender, password, email, status)
VALUES
('Truong1124', 'Ngô Thượng Nhung', 'Kiếm Ma', '0362361299', 'Hải Phòng', 0, 'Truong123', 'truong@gmail.com', 0);
GO
SELECT * FROM users;
SELECT*FROM notes;
DELETE FROM notes;

DROP TABLE notes;
DROP TABLE room_members;
DROP TABLE messages;
DROP TABLE files;

DROP TABLE users;

