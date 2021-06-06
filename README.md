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


    sections:
    
        left: image, right: title, content
        
        top: title, middle: content, bottom: cards
    
    cta[text:""<>button:"Contact Us"]
    
    about[img:"/cms1/img/about.jpg"<>title:"We produce or supply Goods & Services"<>text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque orci purus, sodales in est quis, blandit sollicitudin est. Nam ornare ipsum ac accumsan auctor. Donec consequat arcu et commodo interdum. Vivamus posuere lorem lacus.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque orci purus, sodales in est quis, blandit sollicitudin est. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque orci purus, sodales in est quis, blandit sollicitudin est. Nam ornare ipsum ac accumsan auctor. "<>sign-img:"/cms1/img/sign.png"<>sign-name:"Michael Smith"<>sing-title:"CEO Industrial Inc"]

https://developer.mozilla.org/en-US/docs/Web/API/HTML_Drag_and_Drop_API

Drag and drop: https://www.cssscript.com/drag-drop-dragonflyjs/


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