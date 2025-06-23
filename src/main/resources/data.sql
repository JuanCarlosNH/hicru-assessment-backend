INSERT INTO department (name) VALUES ('IT');
INSERT INTO department (name) VALUES ('Finance');
INSERT INTO department (name) VALUES ('Human Resources');

INSERT INTO recruiter (name, lastname) VALUES ('Roberto', 'Gómez');
INSERT INTO recruiter (name, lastname) VALUES ('César', 'Prieto');
INSERT INTO recruiter (name, lastname) VALUES ('Gonzalo', 'Lira');

INSERT INTO position (title, description, location, status, budget, closing_date, recruiter_id, department_id)
VALUES ('Software Engineer I', 'Master in Java and bankend technologies', 'Mexico city', 'open', 90000.00, '2025-07-20', 1, 1);
INSERT INTO position (title, description, location, status, budget, closing_date, recruiter_id, department_id)
VALUES ('Software Engineer II', 'Master in Java and bankend technologies', 'Mexico city', 'open', 100000.00, '2025-07-20', 2, 2);
INSERT INTO position (title, description, location, status, budget, closing_date, recruiter_id, department_id)
VALUES ('Software Engineer III', 'Master in Java and bankend technologies', 'Mexico city', 'open', 110000.00, '2025-07-20', 3, 3);