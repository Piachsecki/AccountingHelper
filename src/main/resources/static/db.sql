CREATE TABLE address (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         city VARCHAR(255) NULL,
                         postal_code VARCHAR(255) NULL,
                         street VARCHAR(255) NULL
);

CREATE TABLE company (
                         address_id BIGINT NULL,
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         company_name VARCHAR(255) NULL,
                         nip VARCHAR(255) NULL,
                         CONSTRAINT UKh2rewspdf9bnwpbt1nauwiaww UNIQUE (address_id),
                         CONSTRAINT FKgfifm4874ce6mecwj54wdb3ma
                             FOREIGN KEY (address_id)
                                 REFERENCES address (id)
                                 ON DELETE CASCADE
                                 ON UPDATE CASCADE  -- To umożliwia automatyczne aktualizowanie adresu w tabeli 'company', gdy zmieni się adres w tabeli 'address'.
);

CREATE TABLE user (
                      address_id BIGINT NULL,
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      nip VARCHAR(255) NULL,
                      username VARCHAR(255) NULL,
                      CONSTRAINT UKdhlcfg8h1drrgu0irs1ro3ohb UNIQUE (address_id),
                      CONSTRAINT FKddefmvbrws3hvl5t0hnnsv8ox
                          FOREIGN KEY (address_id)
                              REFERENCES address (id)
                              ON DELETE CASCADE
                              ON UPDATE CASCADE
);

CREATE TABLE invoice (
                         amount DECIMAL(38, 2) NULL,
                         due_date DATE NULL,
                         issue_date DATE NULL,
                         tax_rate DECIMAL(38, 2) NULL,
                         company_id BIGINT NULL,
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         user_id BIGINT NOT NULL,
                         currency VARCHAR(255) NULL,
                         invoice_number VARCHAR(255) NULL,
                         invoice_type ENUM ('INCOME_INVOICE', 'OUTGOING_INVOICE') NULL,
                         CONSTRAINT UKq6djtjgynydbbllhdycrqegk8 UNIQUE (company_id),
                         CONSTRAINT FKaslug8pfl346tbeuslh98n7k5
                             FOREIGN KEY (company_id)
                                 REFERENCES company (id)
                                 ON DELETE CASCADE
                                 ON UPDATE CASCADE,
                         CONSTRAINT FKjunvl5maki3unqdvljk31kns3
                             FOREIGN KEY (user_id)
                                 REFERENCES user (id)
                                 ON DELETE CASCADE
                                 ON UPDATE CASCADE
);

CREATE TABLE receipt (
                         amount DECIMAL(38, 2) NULL,
                         date DATE NULL,
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         user_id BIGINT NOT NULL,
                         currency VARCHAR(255) NULL,
                         CONSTRAINT FKrs0n6mlmm5xuxoq2h7dafuw88
                             FOREIGN KEY (user_id)
                                 REFERENCES user (id)
                                 ON DELETE CASCADE
                                 ON UPDATE CASCADE
);

CREATE TABLE worker (
                        salary DECIMAL(38, 2) NULL,
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        bank_account_number VARCHAR(255) NULL,
                        name VARCHAR(255) NULL,
                        surname VARCHAR(255) NULL
);

CREATE TABLE worker_invoice (
                                invoice_id BIGINT NOT NULL,
                                worker_id BIGINT NOT NULL,
                                CONSTRAINT FKskbulo5aef9xnx5w5h0hq3lhe
                                    FOREIGN KEY (worker_id)
                                        REFERENCES worker (id)
                                        ON DELETE CASCADE
                                        ON UPDATE CASCADE,
                                CONSTRAINT FKt7g20tg722h9artra1gukk7r5
                                    FOREIGN KEY (invoice_id)
                                        REFERENCES invoice (id)
                                        ON DELETE CASCADE
                                        ON UPDATE CASCADE
);
