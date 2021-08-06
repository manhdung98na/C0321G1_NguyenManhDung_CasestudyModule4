-- insert
insert into education_degree(education_degree_name) value ('Trung cấp'), ('Cao đẳng'), ('Đại học'), ('Sau đại học');
insert into `position`(position_name) value ('Lễ tân'), ('Phục vụ'), ('Chuyên viên'), ('Giám sát'), ('Quản lý'), ('Giám đốc');
insert into division(division_name) value ('Sale - Marketing'), ('Hành chính'), ('Phục vụ'), ('Quản lý');
insert into customer_type value (1, 'Diamond'), (2, 'Platinium'), (3, 'Gold'), (4, 'Silver'), (5, 'Member');
insert into rent_type(rent_type_name,rent_type_cost) value ('Năm', 35000000), ('Tháng', 300000), ('Ngày', 150000), ('Giờ', 80000);
insert into attach_service (attach_service_name,attach_service_cost, attach_service_unit, attach_service_status)
value ('massage', 500000, 1, 'available'),
    ('karaoke', 200000, 1, 'available'),
    ('thức ăn', 150000, 1, 'available'),
    ('nước uống', 20000, 1, 'available'),
    ('thuê xe di chuyển tham quan resort', 250000, 1, 'available');
insert into service_type(service_type_name) value ('Villa'), ('House'), ('Room');