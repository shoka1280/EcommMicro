INSERT INTO category (id, catename, description) VALUES
                                                     (1, 'Watches', 'All types of analog and digital watches'),
                                                     (2, 'Smart Watches', 'Wearable smart electronic watches'),
                                                     (3, 'Luxury Watches', 'High-end premium luxury watches'),
                                                     (4, 'Sports Watches', 'Watches designed for sports and fitness');
INSERT INTO product (
    id,
    prodname,
    description,
    available_quantity,
    price,
    category_id
) VALUES
      (101, 'Titan Neo', 'Stylish analog watch', 120, 3499.00, 1),
      (102, 'Fastrack Reflex', 'Digital sports watch', 80, 2599.00, 1),

      (201, 'Apple Watch Series 9', 'Latest Apple smartwatch', 50, 41999.00, 2),
      (202, 'Samsung Galaxy Watch 6', 'Fitness smartwatch', 45, 37999.00, 2),

      (301, 'Rolex Submariner', 'Luxury Swiss diving watch', 10, 850000.00, 3),
      (302, 'Omega Seamaster', 'Premium diving watch', 8, 620000.00, 3),

      (401, 'Garmin Forerunner 965', 'Advanced GPS sports watch', 30, 58999.00, 4),
      (402, 'Casio G-Shock', 'Shock resistant sports watch', 150, 8999.00, 4);
