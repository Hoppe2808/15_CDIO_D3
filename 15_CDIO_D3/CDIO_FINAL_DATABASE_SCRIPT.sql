/* must be dropped in this order to avoid constraint violations */
DROP TABLE IF EXISTS produktbatchkomponent;
DROP TABLE IF EXISTS produktbatch;
DROP TABLE IF EXISTS operatoer;
DROP TABLE IF EXISTS receptkomponent;
DROP TABLE IF EXISTS recept;
DROP TABLE IF EXISTS raavarebatch;
DROP TABLE IF EXISTS raavare;

CREATE TABLE operatoer(opr_id INT PRIMARY KEY AUTO_INCREMENT, opr_navn varchar(40), ini varchar(4), cpr varchar(11), password varchar(20), admin INT) ENGINE=innoDB;
 
CREATE TABLE raavare(raavare_id INT PRIMARY KEY , raavare_navn varchar(40), leverandoer varchar(40)) ENGINE=innoDB;

CREATE TABLE raavarebatch(rb_id INT PRIMARY KEY , raavare_id INT, maengde REAL, 
   FOREIGN KEY (raavare_id) REFERENCES raavare(raavare_id)) ENGINE=innoDB;

CREATE TABLE recept(recept_id INT PRIMARY KEY , recept_navn varchar(20)) ENGINE=innoDB;

CREATE TABLE receptkomponent(recept_id INT AUTO_INCREMENT, raavare_id INT, nom_netto DOUBLE(4,2), tolerance DOUBLE(3,1), 
   PRIMARY KEY (recept_id, raavare_id), 
   FOREIGN KEY (recept_id) REFERENCES recept(recept_id), 
   FOREIGN KEY (raavare_id) REFERENCES raavare(raavare_id)) ENGINE=innoDB;

CREATE TABLE produktbatch(pb_id INT PRIMARY KEY AUTO_INCREMENT, status INT, recept_id INT, 
   FOREIGN KEY (recept_id) REFERENCES recept(recept_id)) ENGINE=innoDB;

CREATE TABLE produktbatchkomponent(pb_id INT AUTO_INCREMENT, rb_id INT, tara REAL, netto REAL, opr_id INT, 
   PRIMARY KEY (pb_id, rb_id), 
   FOREIGN KEY (pb_id) REFERENCES produktbatch(pb_id), 
   FOREIGN KEY (rb_id) REFERENCES raavarebatch(rb_id), 
   FOREIGN KEY (opr_id) REFERENCES operatoer(opr_id)) ENGINE=innoDB;


INSERT INTO operatoer(opr_navn, ini, cpr, password, admin) VALUES
('Lars Quaade', 'LQ', '070770-7007', 'password', 1),
('Magnus Haakonson', 'MH', '080880-8008', 'password', 2),
('William Wiberg', 'WB', '090990-9009', 'password', 3),
('Tanja Pajlina', 'TP', '123456-7890', 'password', 4);

INSERT INTO raavare(raavare_id, raavare_navn, leverandoer) VALUES
(1, 'vand', 'Vand A/S'),
(2, 'magnesium', 'Miner'),
(3, 'jern', 'Miner'),
(4, 'salt', 'Haribo'),
(5, 'gelatine', 'Haribo'),
(6, 'c-vitamin', 'Vitamin A/S'),
(7, 'glukose', 'KemiFolket');

INSERT INTO raavarebatch(rb_id, raavare_id, maengde) VALUES
(1, 1, 1000),
(2, 2, 300),
(3, 3, 300),
(4, 5, 100),
(5, 5, 100), 
(6, 6, 100),
(7, 7, 100);

INSERT INTO recept(recept_navn) VALUES
(1, 'Gummi-Vitamin'),
(2, 'Oral-Vitamin'),
(3, 'Creme-de-la-creme');


INSERT INTO receptkomponent(recept_id, raavare_id, nom_netto, tolerance) VALUES
(1, 1, 10.0, 0.1),
(1, 2, 2.0, 0.1),
(1, 5, 2.0, 0.1),

(2, 1, 10.0, 0.1),
(2, 3, 2.0, 0.1),  
(2, 5, 1.5, 0.1),
(2, 6, 1.5, 0.1),

(3, 1, 10.0, 0.1),
(3, 4, 1.5, 0.1),
(3, 5, 1.5, 0.1),
(3, 6, 1.0, 0.1),
(3, 7, 1.0, 0.1);

INSERT INTO produktbatch(recept_id, status) VALUES
(1, 2), 
(1, 2),
(2, 2),
(3, 1),
(3, 0);


INSERT INTO produktbatchkomponent(pb_id, rb_id, tara, netto, opr_id) VALUES
(1, 1, 0.5, 10.05, 1),
(1, 2, 0.5, 2.03, 1),
(1, 4, 0.5, 1.98, 1),

(2, 1, 0.5, 10.01, 2),
(2, 2, 0.5, 1.99, 2),
(2, 5, 0.5, 1.47, 2),

(3, 1, 0.5, 10.07, 1),
(3, 3, 0.5, 2.06, 2),
(3, 4, 0.5, 1.55, 1),
(3, 6, 0.5, 1.53, 2),

(4, 1, 0.5, 10.02, 3),
(4, 5, 0.5, 1.57, 3),
(4, 6, 0.5, 1.03, 3),
(4, 7, 0.5, 0.99, 3);


 