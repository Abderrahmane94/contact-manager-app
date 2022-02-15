INSERT INTO company (id, address, tva)
VALUES (1, 'Alger', 'TVA BE 123 456 789');
INSERT INTO company (id, address, tva)
VALUES (2, 'Bouzareha', 'TVA BE 741 852 963');
INSERT INTO contact (id, first_name, last_name, address, tva, type)
VALUES (1, 'Amin', 'Belkadi', 'Bouzareha', '', 'Employe');
INSERT INTO contact (id, first_name, last_name, address, tva, type)
VALUES (2, 'Nadir', 'Benhoucine', 'Alger', 'TVA BE 965 753 264', 'freelance');
INSERT INTO contact_companies (companies_id, contact_id)
VALUES (1, 1);