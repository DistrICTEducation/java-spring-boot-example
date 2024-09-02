-- Create the library database
CREATE DATABASE library;
GO

-- Switch to the library database
USE library;
GO

-- Create the member table
CREATE TABLE member (
    id UNIQUEIDENTIFIER PRIMARY KEY,
    first_name NVARCHAR(100) NOT NULL,
    last_name NVARCHAR(100) NOT NULL,
    email NVARCHAR(255) NOT NULL,
    phone_number NVARCHAR(20),
    password NVARCHAR(255) NOT NULL,
    username NVARCHAR(100) NOT NULL
);
GO

-- Create the book table
CREATE TABLE book (
    id INT IDENTITY(1,1) PRIMARY KEY,
    isbn NVARCHAR(13) NOT NULL,
    isbn13 NVARCHAR(17) NOT NULL,
    title NVARCHAR(255) NOT NULL,
    author NVARCHAR(255) NOT NULL,
    publisher NVARCHAR(255) NOT NULL,
    publication_year INT,
    publication_language NVARCHAR(50),
    genre NVARCHAR(100),
    number_of_pages INT,
    number_of_copies INT NOT NULL
);
GO

-- Create the book_loan table
CREATE TABLE book_loan (
    id UNIQUEIDENTIFIER PRIMARY KEY,
    member_id UNIQUEIDENTIFIER NOT NULL,
    book_id INT NOT NULL,
    checkout_date DATE NOT NULL,
    due_date DATE NOT NULL,
    return_date DATE,
    fine_amount DECIMAL(10, 2),
    fine_status NVARCHAR(50),
    fine_status_date DATE,
    CONSTRAINT FK_book_loan_member FOREIGN KEY (member_id) REFERENCES member(id),
    CONSTRAINT FK_book_loan_book FOREIGN KEY (book_id) REFERENCES book(id)
);
GO

-- Create the book_reservation table
CREATE TABLE book_reservation (
    id UNIQUEIDENTIFIER PRIMARY KEY,
    member_id UNIQUEIDENTIFIER NOT NULL,
    book_id INT NOT NULL,
    reservation_date DATE NOT NULL,
    status NVARCHAR(50) NOT NULL,
    pickup_date DATE,
    CONSTRAINT FK_book_reservation_member FOREIGN KEY (member_id) REFERENCES member(id),
    CONSTRAINT FK_book_reservation_book FOREIGN KEY (book_id) REFERENCES book(id)
);
GO

-- Create the book_review table
CREATE TABLE book_review (
    id UNIQUEIDENTIFIER PRIMARY KEY,
    book_id INT NOT NULL,
    member_id UNIQUEIDENTIFIER NOT NULL,
    rating INT CHECK (rating >= 1 AND rating <= 5),
    review_date DATE NOT NULL,
    CONSTRAINT FK_book_review_book FOREIGN KEY (book_id) REFERENCES book(id),
    CONSTRAINT FK_book_review_member FOREIGN KEY (member_id) REFERENCES member(id)
);
GO
