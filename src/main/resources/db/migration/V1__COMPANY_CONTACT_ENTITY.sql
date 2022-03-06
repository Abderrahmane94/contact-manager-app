CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE company
(
    id      BIGINT auto_increment NOT NULL,
    uuid    UUID DEFAULT RANDOM_UUID(),
    address VARCHAR(255)          NOT NULL,
    tva     VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_company PRIMARY KEY (id)
);

CREATE TABLE company_contacts
(
    company_id  BIGINT NOT NULL,
    contacts_id BIGINT NOT NULL,
    CONSTRAINT pk_company_contacts PRIMARY KEY (company_id, contacts_id)
);

CREATE TABLE contact
(
    id         BIGINT auto_increment NOT NULL,
    uuid       UUID DEFAULT RANDOM_UUID(),
    first_name VARCHAR(255)          NOT NULL,
    last_name  VARCHAR(255)          NOT NULL,
    address    VARCHAR(255)          NOT NULL,
    tva        VARCHAR(255),
    type       VARCHAR(255),
    CONSTRAINT pk_contact PRIMARY KEY (id)
);

ALTER TABLE company_contacts
    ADD CONSTRAINT fk_comcon_on_company FOREIGN KEY (company_id) REFERENCES company (id);

ALTER TABLE company_contacts
    ADD CONSTRAINT fk_comcon_on_contact FOREIGN KEY (contacts_id) REFERENCES contact (id);