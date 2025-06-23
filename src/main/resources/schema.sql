CREATE TABLE IF NOT EXISTS recruiter (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    lastname VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS department (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS position (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(1000) NOT NULL,
    location VARCHAR(100) NOT NULL,
    status VARCHAR(20) NOT NULL,
    budget NUMERIC(10,2) NOT NULL,
    closing_date DATE,
    recruiter_id INT NOT NULL,
    department_id INT NULL NULL
);

ALTER TABLE position
    ADD FOREIGN KEY (recruiter_id)
    REFERENCES recruiter(id);

ALTER TABLE position
    ADD FOREIGN KEY (department_id)
    REFERENCES department(id);