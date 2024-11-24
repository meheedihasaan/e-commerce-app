insert into categories (id, name, description) values
   ('1e7d1e2e-1c3b-4b8e-9b1d-1e7d1e2e1c3b', 'Electronics', 'Electronic items'),
   ('2e7d1e2e-2c3b-4b8e-9b1d-2e7d1e2e2c3b', 'Books', 'Various kinds of books'),
   ('3e7d1e2e-3c3b-4b8e-9b1d-3e7d1e2e3c3b', 'Clothing', 'Men and women clothing'),
   ('4e7d1e2e-4c3b-4b8e-9b1d-4e7d1e2e4c3b', 'Furniture', 'Home and office furniture'),
   ('5e7d1e2e-5c3b-4b8e-9b1d-5e7d1e2e5c3b', 'Toys', 'Children toys');

insert into products (id, name, description, quantity, price, category_id) values
    ('1f7d1e2e-1c3b-4b8e-9b1d-1f7d1e2e1c3b', 'Laptop', 'High performance laptop', 10, 999.99, '1e7d1e2e-1c3b-4b8e-9b1d-1e7d1e2e1c3b'),
    ('2f7d1e2e-2c3b-4b8e-9b1d-2f7d1e2e2c3b', 'Smartphone', 'Latest model smartphone', 20, 699.99, '1e7d1e2e-1c3b-4b8e-9b1d-1e7d1e2e1c3b'),
    ('3f7d1e2e-3c3b-4b8e-9b1d-3f7d1e2e3c3b', 'Novel', 'Bestselling novel', 50, 19.99, '2e7d1e2e-2c3b-4b8e-9b1d-2e7d1e2e2c3b'),
    ('4f7d1e2e-4c3b-4b8e-9b1d-4f7d1e2e4c3b', 'T-shirt', 'Cotton t-shirt', 100, 9.99, '3e7d1e2e-3c3b-4b8e-9b1d-3e7d1e2e3c3b'),
    ('5f7d1e2e-5c3b-4b8e-9b1d-5f7d1e2e5c3b', 'Desk', 'Wooden office desk', 5, 199.99, '4e7d1e2e-4c3b-4b8e-9b1d-4e7d1e2e4c3b');