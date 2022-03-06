INSERT INTO company (id, uuid, address, tva)
VALUES (1, 'ba3d99c9-8f1a-4d4f-bdd9-600020f37401', 'Alger', 'TVA BE 123 456 789');
INSERT INTO company (id, uuid, address, tva)
VALUES (2, '5943fee8-7fd6-4c9d-8653-cdc756ad3a95', 'Bouzareha', 'TVA BE 741 852 963');
INSERT INTO contact (id, uuid, first_name, last_name, address, tva, type)
VALUES (1, '7a970d94-df2a-4aab-923c-8a3a27d38f2b', 'Abderrahmane', 'Sardaoui', 'Bab Ezzouar', '', 'Employe');
INSERT INTO contact (id, uuid, first_name, last_name, address, tva, type)
VALUES (2, 'f969a2c4-e294-4648-8040-630f8ad40765', 'Nadir', 'Benhoucine', 'Alger', 'TVA BE 965 753 264', 'freelance');
INSERT INTO company_contacts (company_id, contacts_id)
VALUES (1, 1);

INSERT INTO role (id, description, name)
VALUES (1, 'Admin role', 'ADMIN');
INSERT INTO role (id, description, name)
VALUES (2, 'User role', 'USER');
