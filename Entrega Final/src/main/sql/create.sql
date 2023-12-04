CREATE TABLE api_rest.client (
    id SERIAL PRIMARY KEY,
    dni VARCHAR(11),
    forename VARCHAR(255),
    last_name VARCHAR(255),
    birthdate DATE
);

CREATE TABLE api_rest.product (
    id SERIAL PRIMARY KEY,
    code VARCHAR(50),
    description VARCHAR(255),
    purchase_price DOUBLE PRECISION,
    sels_price DOUBLE PRECISION,
    amount INT,
    enrollment_date DATE
);

CREATE TABLE api_rest.invoice (
    id SERIAL PRIMARY KEY,
    enrollment_date DATE,
    total_price DOUBLE PRECISION,
    client_Id INT,
    CONSTRAINT fk_clientId FOREIGN KEY (client_Id) REFERENCES api_rest.client(id)
);

CREATE TABLE api_rest.details (
    id SERIAL PRIMARY KEY,
    invoice_Id INT,
    product_Id INT,
    amount INT,
    total_price DOUBLE PRECISION,
    CONSTRAINT fk_invoiceId FOREIGN KEY (invoice_Id) REFERENCES api_rest.invoice(id),
    CONSTRAINT fk_productId FOREIGN KEY (product_Id) REFERENCES api_rest.product(id)
);