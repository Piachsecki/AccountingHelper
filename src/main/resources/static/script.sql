-- Insert data into address
INSERT INTO address (city, postal_code, street)
VALUES
    ('Warsaw', '00-001', 'Krakowskie Przedmieście 1'),
    ('Kraków', '30-001', 'Rynek Główny 10'),
    ('Gdańsk', '80-001', 'Długa 50'),
    ('Wrocław', '50-001', 'Świdnicka 20'),
    ('Poznań', '60-001', 'Plac Wolności 5');

-- Insert data into company
INSERT INTO company (address_id, company_name, nip)
VALUES
    (1, 'ABC Corp', '1234567890'),
    (2, 'XYZ Ltd', '0987654321'),
    (3, 'Tech Solutions', '1122334455'),
    (4, 'Green Energy', '2233445566'),
    (5, 'Oceanic Ventures', '3344556677');

-- Insert data into user
INSERT INTO user (address_id, nip, username)
VALUES
    (1, '9876543210', 'john_doe'),
    (2, '8765432109', 'jane_smith'),
    (3, '7654321098', 'mike_jones'),
    (4, '6543210987', 'emma_brown'),
    (5, '5432109876', 'lisa_white');

-- Insert data into invoice
INSERT INTO invoice (amount, due_date, issue_date, tax_rate, company_id, user_id, currency, invoice_number, invoice_type)
VALUES
    (1000.00, '2024-12-31', '2024-12-01', 23.00, 1, 1, 'PLN', 'INV-2024-001', 'INCOME_INVOICE'),
    (2000.00, '2024-11-30', '2024-11-01', 23.00, 2, 2, 'PLN', 'INV-2024-002', 'OUTGOING_INVOICE'),
    (1500.00, '2024-10-31', '2024-10-01', 8.00, 3, 3, 'EUR', 'INV-2024-003', 'INCOME_INVOICE'),
    (2500.00, '2024-09-30', '2024-09-01', 8.00, 4, 4, 'USD', 'INV-2024-004', 'OUTGOING_INVOICE'),
    (3000.00, '2024-08-31', '2024-08-01', 0.00, 5, 5, 'GBP', 'INV-2024-005', 'INCOME_INVOICE');

-- Insert data into receipt
INSERT INTO receipt (amount, date, user_id, currency)
VALUES
    (500.00, '2024-12-01', 1, 'PLN'),
    (750.00, '2024-11-15', 2, 'PLN'),
    (300.00, '2024-10-20', 3, 'EUR'),
    (1200.00, '2024-09-25', 4, 'USD'),
    (100.00, '2024-08-10', 5, 'GBP');

-- Insert data into worker
INSERT INTO worker (salary, bank_account_number, name, surname)
VALUES
    (5000.00, 'PL12345678901234567890123456', 'Adam', 'Nowak'),
    (4500.00, 'PL98765432109876543210987654', 'Ewa', 'Kowalska'),
    (4000.00, 'PL65432109876543210987654321', 'Michał', 'Wiśniewski'),
    (6000.00, 'PL32109876543210987654321098', 'Anna', 'Zielińska'),
    (5500.00, 'PL21098765432109876543210987', 'Krzysztof', 'Lewandowski');

-- Insert data into worker_invoice
INSERT INTO worker_invoice (invoice_id, worker_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (1, 2), -- Jeden pracownik może współpracować przy kilku fakturach
    (2, 3),
    (3, 4);
