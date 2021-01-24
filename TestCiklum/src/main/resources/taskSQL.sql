/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Sergio González
 * Created: 24 jan. 2021
 */

-- 1. Create a query to return the unique rows in a table

select distinct * 
from clients;

-- 2. Write a command to insert values into a table

insert into clients
(id, client_name, age)
values
(1, 'Sergio González', 41);

-- 3. Create a query that joins two tables together. Note, all rows from the 
-- first table must be in the result-set (e.g. given customer and order tables, return all customers and any orders for each customer)

select *
from clients c
left join clients_orders co on c.id = co.client_id;  

