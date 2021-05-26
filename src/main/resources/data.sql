INSERT INTO PERSON(name,message) SELECT 'Pat','Hey this is Pat' WHERE NOT EXISTS (SELECT name FROM PERSON WHERE name='Pat');
INSERT INTO PERSON(name,message) SELECT 'Joe','Hey this is Joe' WHERE NOT EXISTS (SELECT name FROM PERSON WHERE name='Joe');
--Default User
INSERT INTO PERSON(name,firstname,lastname,aboutme,email_address,phone)
SELECT 'JohnW','John', 'Wick','A very courageous human','ptchankue@gmail.com','+27768354159' WHERE NOT EXISTS (SELECT name FROM PERSON WHERE name='JohnW');
-- Sites
INSERT INTO SITE(id, name, phone, email, host, language, template)
SELECT 1, 'Default CMS', '+27768354159', 'ptchankue@gmail.com', '', 'en', 'cms1'
WHERE NOT EXISTS (SELECT name FROM SITE WHERE id=1);

-- Pages
INSERT INTO PAGE(id, title, body, site_id, online, url)
SELECT 1, 'Home', 'xxx', 1, true, 'home'
WHERE NOT EXISTS (SELECT id FROM PAGE WHERE site_id=1 AND url='home');

INSERT INTO PAGE(id, title, body, site_id, online, url)
SELECT 2, 'Contact', 'xxx', 1, true, 'contact'
WHERE NOT EXISTS (SELECT id FROM PAGE WHERE site_id=1 AND url='contact');
-- Menu
INSERT INTO LINK(title, header, footer, page_id)
SELECT 'Home', true, true, 1
WHERE NOT EXISTS (SELECT id FROM LINK WHERE page_id=1);

INSERT INTO LINK(title, header, footer, page_id)
SELECT 'Contact', true, true, 2
WHERE NOT EXISTS (SELECT id FROM LINK WHERE page_id=2);
