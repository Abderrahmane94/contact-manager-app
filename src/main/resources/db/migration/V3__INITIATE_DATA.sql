INSERT INTO company (id, address, tva)
VALUES (1, 'Alger', 'TVA BE 123 456 789');
INSERT INTO company (id, address, tva)
VALUES (2, 'Bouzareha', 'TVA BE 741 852 963');
INSERT INTO contact (id, first_name, last_name, address, tva, type)
VALUES (1, 'Amin', 'Belkadi', 'Bouzareha', '', 'Employe');
INSERT INTO contact (id, first_name, last_name, address, tva, type)
VALUES (2, 'Nadir', 'Benhoucine', 'Alger', 'TVA BE 965 753 264', 'freelance');
INSERT INTO company_contacts (company_id, contacts_id)
VALUES (1, 1);

INSERT INTO role (id, description, name)
VALUES (1, 'Admin role', 'ADMIN');
INSERT INTO role (id, description, name)
VALUES (2, 'User role', 'USER');
