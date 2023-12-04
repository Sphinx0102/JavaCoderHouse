CREATE TABLE api_rest.client (
    id SERIAL PRIMARY KEY,
    dni VARCHAR(11),
    forename VARCHAR(255),
    lastName VARCHAR(255),
    birthdate DATE
);

CREATE TABLE api_rest.product (
    id SERIAL PRIMARY KEY,
    code VARCHAR(50),
    description VARCHAR(255),
    purchasePrice DOUBLE PRECISION,
    selsPrice DOUBLE PRECISION,
    amount INT,
    enrollmentDate DATE
);

CREATE TABLE api_rest.invoice (
    id SERIAL PRIMARY KEY,
    enrollmentDate DATE,
    totalPrice DOUBLE PRECISION,
    clientId INT,
    CONSTRAINT fk_clientId FOREIGN KEY (clientId) REFERENCES api_rest.client(id)
);

CREATE TABLE api_rest.details (
    detailId SERIAL PRIMARY KEY,
    voucherId INT,
    productId INT,
    amount INT,
    totalPrice DOUBLE PRECISION,
    CONSTRAINT fk_voucherId FOREIGN KEY (voucherId) REFERENCES api_rest.voucher(id),
    CONSTRAINT fk_productId FOREIGN KEY (productId) REFERENCES api_rest.product(id)
);