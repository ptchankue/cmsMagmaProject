# CMS Project


`curl --location --request POST 'localhost:9082/api/person' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "Patrick",
"message": "Bienvenue"
}'`


`curl --location --request POST 'localhost:9082/api/blogpost' \
--header 'Content-Type: application/json' \
--data-raw '{
"title": "Recitation: Mon papayer",
"body": "Dans un coin de ma cour, il ya un papayer, c\'est moi qui l\'ai seme",
"person_id": 1
}'`

`curl localhost:9082/api/graph/routes?from=yaounde&to=bafoussam
`

`curl --location --request POST 'localhost:9082/api/blogpost' \
--header 'Content-Type: application/json' \
--data-raw '{
"title": "Paramilitary",
"body": "TolProduct Product "
}'`


create site: generates HomePage, ContactPage

pages can be used to create Links

http://localhost:9082/admin/site/1?page=2

```
<li th:each="menu: ${parameters['headerMenu']}"
    th:class="(${menu.page.id} == ${parameters['page'].id}) ? 'active' : '' ">
    <a th:href="@{|/admin/site/${parameters['site'].id}?page=${menu.page.id}|}"
       th:text="${menu.title}">About us</a>
</li>
```


primary color: #ffc107

secondary color: #08162

## CMS design


    Site:
        Header
            Menu
        Content:
            - HomePage
                - Slides
            - ContactPage
            - GenericPage
        Footer
            Menu (Links)
    
A website has a template, pages

    Template:
        - id
        - 


CMS Walrus