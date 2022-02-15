CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE company
(
    id      BIGINT       NOT NULL,
    address VARCHAR(255) NOT NULL,
    tva     VARCHAR(255) NOT NULL,
    CONSTRAINT pk_company PRIMARY KEY (id)
);

CREATE TABLE contact
(
    id         BIGINT       NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    address    VARCHAR(255) NOT NULL,
    tva        VARCHAR(255),
    type       VARCHAR(255),
    CONSTRAINT pk_contact PRIMARY KEY (id)
);

CREATE TABLE contact_companies
(
    companies_id BIGINT NOT NULL,
    contact_id   BIGINT NOT NULL,
    CONSTRAINT pk_contact_companies PRIMARY KEY (companies_id, contact_id)
);

ALTER TABLE contact_companies
    ADD CONSTRAINT fk_concom_on_company FOREIGN KEY (companies_id) REFERENCES company (id);

ALTER TABLE contact_companies
    ADD CONSTRAINT fk_concom_on_contact FOREIGN KEY (contact_id) REFERENCES contact (id);