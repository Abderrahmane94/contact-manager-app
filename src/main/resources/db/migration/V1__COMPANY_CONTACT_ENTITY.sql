CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE contact
(
    id         BIGINT       NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    address    VARCHAR(255) NOT NULL,
    tva        VARCHAR(255) NOT NULL,
    type       VARCHAR(255),
    company_id BIGINT,
    CONSTRAINT pk_contact PRIMARY KEY (id)
);

CREATE TABLE company
(
    id         BIGINT       NOT NULL,
    address    VARCHAR(255) NOT NULL,
    tva        VARCHAR(255) NOT NULL,
    contact_id BIGINT,
    CONSTRAINT pk_company PRIMARY KEY (id)
);

ALTER TABLE company
    ADD CONSTRAINT FK_COMPANY_ON_CONTACT FOREIGN KEY (contact_id) REFERENCES contact (id);

ALTER TABLE contact
    ADD CONSTRAINT FK_CONTACT_ON_COMPANY FOREIGN KEY (company_id) REFERENCES company (id);