use db_zelmi

INSERT INTO employee(username, password, is_admin, created_at)
VALUES 
('Sadia Qazi', 'password', 0, '2022-01-01'),
('Laura Dovidaviciene', 'password', 1, '2022-01-01'),
('Emil Johansson', 'password', 0, '2022-01-01'),
('Gabriel Nilsson', 'password', 0, '2022-01-01'),
('Emmelie Viklund', 'password', 1, '2022-01-01')

INSERT INTO task(prioritization_type, case_notes, created_at, expires_at, employee_id)
VALUES 
('Urgent', 'Note', '2023-01-01' ,'2022-01-01' ,1),
('No Rush', 'Note', '2023-01-01' ,'2022-01-01' ,1),
('No Rush', 'Note', '2023-01-01' ,'2022-01-01' ,1),
('Urgent', 'Note', '2023-01-01' ,'2022-01-01' ,1),
('No Rush', 'Note', '2023-01-01' ,'2022-01-01' ,2),
('Urgent', 'Note', '2023-01-01' ,'2022-01-01' ,2),
('No Rush', 'Note', '2023-01-01' ,'2022-01-01' ,2),
('Urgent', 'Note', '2023-01-01' ,'2022-01-01' ,2),
('No Rush', 'Note', '2023-01-01' ,'2022-01-01' ,3),
('Urgent', 'Note', '2023-01-01' ,'2022-01-01' ,3),
('Urgent', 'Note', '2023-01-01' ,'2022-01-01' ,3),
('No Rush', 'Note', '2023-01-01' ,'2022-01-01' ,3),
('Urgent', 'Note', '2023-01-01' ,'2022-01-01' ,3),
('No Rush', 'Note', '2023-01-01' ,'2022-01-01' ,4),
('Urgent', 'Note', '2023-01-01' ,'2022-01-01' ,4),
('No Rush', 'Note', '2023-01-01' ,'2022-01-01' ,4),
('Urgent', 'Note', '2023-01-01' ,'2022-01-01' ,4),
('No Rush', 'Note', '2023-01-01' ,'2022-01-01' ,5),
('Urgent', 'Note', '2023-01-01' ,'2022-01-01' ,5),
('No Rush', 'Note', '2023-01-01' ,'2022-01-01' ,5),
('Urgent', 'Note', '2023-01-01' ,'2022-01-01' ,5)

