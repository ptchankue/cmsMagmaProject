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

INSERT INTO PAGE(id, title, body, site_id, online, url)
SELECT 3, 'About Us', 'xxx', 1, true, 'about_us'
WHERE NOT EXISTS (SELECT id FROM PAGE WHERE site_id=1 AND url='about_us');
-- Page Sections
--home page
INSERT INTO PAGE_SECTION(id, title, html, page_id)
SELECT 1, 'Home_Services', '<section></section>', 1
WHERE NOT EXISTS (SELECT id FROM PAGE_SECTION WHERE page_id=1 AND title='Home_Services');

INSERT INTO PAGE_SECTION(id, title, html, page_id)
SELECT 2, 'Home_Features', '<section></section>', 1
WHERE NOT EXISTS (SELECT id FROM PAGE_SECTION WHERE page_id=1 AND id=2);

INSERT INTO PAGE_SECTION(id, title, html, page_id)
SELECT 3, 'Home_Clients', '<section></section>', 1
WHERE NOT EXISTS (SELECT id FROM PAGE_SECTION WHERE page_id=1 AND id=3);

INSERT INTO PAGE_SECTION(id, title, html, page_id)
SELECT 4, 'Home_Testimonials', '<section></section>', 1
WHERE NOT EXISTS (SELECT id FROM PAGE_SECTION WHERE page_id=1 AND id=4);

INSERT INTO PAGE_SECTION(id, title, html, page_id)
SELECT 5, 'Home_Call_To_Action', '<section></section>', 1
WHERE NOT EXISTS (SELECT id FROM PAGE_SECTION WHERE page_id=1 AND id=5);

INSERT INTO PAGE_SECTION(id, title, html, page_id)
SELECT 6, 'Home_Video', '<section></section>', 1
WHERE NOT EXISTS (SELECT id FROM PAGE_SECTION WHERE page_id=1 AND id=6);
-- about us page
INSERT INTO PAGE_SECTION(id, title, html, page_id, position)
SELECT 7, 'About_Top', '<section></section>', 3, 1
WHERE NOT EXISTS (SELECT id FROM PAGE_SECTION WHERE page_id=3 AND id=7);
-- contact page

-- Menu
INSERT INTO LINK(title, header, footer, page_id)
SELECT 'Home', true, true, 1
WHERE NOT EXISTS (SELECT id FROM LINK WHERE page_id=1);

INSERT INTO LINK(title, header, footer, page_id)
SELECT 'Contact', true, true, 2
WHERE NOT EXISTS (SELECT id FROM LINK WHERE page_id=2);

INSERT INTO LINK(title, header, footer, page_id)
SELECT 'About Us', true, true, 3
WHERE NOT EXISTS (SELECT id FROM LINK WHERE page_id=3);
