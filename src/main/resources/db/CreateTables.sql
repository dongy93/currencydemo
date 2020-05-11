CREATE TABLE IF NOT EXISTS payment (
    payment_id character varying(255) NOT NULL,
    total_amount_sent integer,
    total_amount_received integer,
    currency_sent character varying(255),
    currency_received character varying(255),
    status character varying(255) NOT NULL,
    error_description text
);