create table address
(
    id          bigint auto_increment
        primary key,
    city        varchar(255) null,
    postal_code varchar(255) null,
    street      varchar(255) null
);

create table company
(
    address_id   bigint       null,
    id           bigint auto_increment
        primary key,
    company_name varchar(255) null,
    nip          varchar(255) null,
    constraint UKh2rewspdf9bnwpbt1nauwiaww
        unique (address_id),
    constraint FKgfifm4874ce6mecwj54wdb3ma
        foreign key (address_id) references address (id)
);

create table notification
(
    id             bigint auto_increment
        primary key,
    user_id        bigint                            not null,
    invoice_number varchar(255)                      not null,
    send_status    enum ('ERROR', 'PENDING', 'SENT') not null
);

create table user
(
    business_activity tinyint      null,
    taxation_method   tinyint      null,
    vat               bit          null,
    address_id        bigint       null,
    id                bigint auto_increment
        primary key,
    email             varchar(255) not null,
    nip               varchar(255) not null,
    password          varchar(255) not null,
    role              varchar(255) null,
    username          varchar(255) not null,
    constraint UK86qo9wgo7dttnc5okf9t5ocpu
        unique (nip),
    constraint UKdhlcfg8h1drrgu0irs1ro3ohb
        unique (address_id),
    constraint FKddefmvbrws3hvl5t0hnnsv8ox
        foreign key (address_id) references address (id),
    check (`business_activity` between 0 and 6),
    check (`taxation_method` between 0 and 2)
);

create table invoice
(
    amount         decimal(38, 2)                              null,
    due_date       date                                        null,
    issue_date     date                                        null,
    tax_rate       decimal(38, 2)                              null,
    company_id     bigint                                      null,
    id             bigint auto_increment
        primary key,
    user_id        bigint                                      not null,
    currency       varchar(255)                                null,
    invoice_number varchar(255)                                not null,
    invoice_type   enum ('INCOME_INVOICE', 'OUTGOING_INVOICE') not null,
    constraint UKq6djtjgynydbbllhdycrqegk8
        unique (company_id),
    constraint FKaslug8pfl346tbeuslh98n7k5
        foreign key (company_id) references company (id),
    constraint FKjunvl5maki3unqdvljk31kns3
        foreign key (user_id) references user (id)
);

create table receipt
(
    amount   decimal(38, 2) null,
    date     date           null,
    is_nip   bit            not null,
    tax_rate decimal(38, 2) null,
    id       bigint auto_increment
        primary key,
    user_id  bigint         not null,
    currency varchar(255)   null,
    constraint FKrs0n6mlmm5xuxoq2h7dafuw88
        foreign key (user_id) references user (id)
);

create table worker
(
    salary              decimal(38, 2) not null,
    id                  bigint auto_increment
        primary key,
    user_id             bigint         null,
    bank_account_number varchar(255)   not null,
    name                varchar(255)   null,
    surname             varchar(255)   null,
    constraint UKbtsvrqwe8df3gde1fjl30hcqf
        unique (bank_account_number),
    constraint FKpv1q4f7cyw16v1gicvxk74skh
        foreign key (user_id) references user (id)
);

create table worker_invoice
(
    invoice_id bigint not null,
    worker_id  bigint not null,
    constraint FKskbulo5aef9xnx5w5h0hq3lhe
        foreign key (worker_id) references worker (id),
    constraint FKt7g20tg722h9artra1gukk7r5
        foreign key (invoice_id) references invoice (id)
);








INSERT INTO address (id, city, postal_code, street) VALUES
                                                        (1, 'Warsaw', '00-001', 'Main Street'),
                                                        (2, 'Krakow', '31-001', 'High Street'),
                                                        (3, 'Gdansk', '80-001', 'Sea Road');

-- Dodawanie danych do tabeli Company
INSERT INTO company (id, address_id, company_name, nip) VALUES
                                                            (1, 1, 'TechCorp', '1234567890'),
                                                            (2, 2, 'BizSolutions', '0987654321');

-- Dodawanie danych do tabeli User
INSERT INTO user (id, email, nip, password, username, business_activity, taxation_method, vat, address_id) VALUES
                                                                                                               (1, 'john.doe@example.com', '1234567890', 'securepassword', 'john_doe', 1, 0, 1, 1),
                                                                                                               (2, 'jane.smith@example.com', '0987654321', 'anotherpassword', 'jane_smith', 2, 1, 0, 2);

-- Dodawanie danych do tabeli Invoice
INSERT INTO invoice (id, amount, due_date, issue_date, tax_rate, company_id, user_id, currency, invoice_number, invoice_type) VALUES
                                                                                                                                  (1, 1000.00, '2025-02-01', '2025-01-15', 23.00, 1, 1, 'PLN', 'INV2025001', 'INCOME_INVOICE'),
                                                                                                                                  (2, 2000.00, '2025-03-01', '2025-01-20', 23.00, 2, 2, 'PLN', 'INV2025002', 'OUTGOING_INVOICE');

-- Dodawanie danych do tabeli Receipt
INSERT INTO receipt (id, amount, date, is_nip, tax_rate, user_id, currency) VALUES
                                                                                (1, 500.00, '2025-01-10', 1, 23.00, 1, 'PLN'),
                                                                                (2, 800.00, '2025-01-12', 0, 23.00, 2, 'PLN');

-- Dodawanie danych do tabeli Notification
INSERT INTO notification (id, user_id, invoice_number, send_status) VALUES
                                                                        (1, 1, 'INV2025001', 'PENDING'),
                                                                        (2, 2, 'INV2025002', 'SENT');

-- Dodawanie danych do tabeli Worker
INSERT INTO worker (id, salary, user_id, bank_account_number, name, surname) VALUES
                                                                                 (1, 5000.00, 1, 'PL76109010140000071219812874', 'John', 'Doe'),
                                                                                 (2, 4500.00, 2, 'PL76109010140000071219812875', 'Jane', 'Smith');

-- Dodawanie danych do tabeli Worker_Invoice
INSERT INTO worker_invoice (invoice_id, worker_id) VALUES
                                                       (1, 1),
                                                       (2, 2);






SELECT i.invoice_number, u.username, c.company_name
FROM invoice i
         JOIN user u ON i.user_id = u.id
         JOIN company c ON i.company_id = c.id;


DELETE FROM user WHERE id = 1;
