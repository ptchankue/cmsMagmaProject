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

