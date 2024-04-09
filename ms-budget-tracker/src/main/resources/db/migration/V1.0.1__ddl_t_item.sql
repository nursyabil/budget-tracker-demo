CREATE TABLE IF NOT EXISTS t_item (
    id uuid PRIMARY KEY,
    description varchar(500),
    price NUMERIC(10, 2),
    purchase_date date
);