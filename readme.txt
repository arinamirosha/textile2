
// WORK IS CARRIED OUT WITH CONFIGURATION FILES IN THE ROOT OF THE ARCHIVE (not needed -D parameter)
// IF YOU DELETE THEM, THEY WILL BE TAKEN FROM src/main/resources/

// lab1

java -jar textile.jar dbinfo

// lab2

java -jar textile.jar testentity create name1 description 1 country city 123456
java -jar textile.jar testentity create name2 description 1 country city 123456
java -jar textile.jar testentity read 1
java -jar textile.jar testentity update 1 name11 description 0 country city 654321
java -jar textile.jar testentity delete 2

// lab3
// Here are examples for 'joined' command. You can change it to 'mapped', 'single' or 'perclass'

java -jar textile.jar joined add 123-a name1 100 30 socks 38-40
java -jar textile.jar joined add 234-b name2 800 10 pillow 60 30 5
java -jar textile.jar joined add 345-c name3 1200 15 bathrobe M
java -jar textile.jar joined add 456-d name4 2000 10 mattress 200 160 10
java -jar textile.jar joined getbyid 3
java -jar textile.jar joined getbyarticle 345-c
java -jar textile.jar joined delbyid 3
java -jar textile.jar joined delbyarticle 234-b
java -jar textile.jar joined change 123-a name1name 150 20 socks 38-40
java -jar textile.jar joined change 456-d name4name 1800 5 mattress 200 160 10
java -jar textile.jar joined doc 123-a 6 posting
java -jar textile.jar joined doc 456-d 5 sale
java -jar textile.jar joined showall
java -jar textile.jar joined showzero
java -jar textile.jar joined showbyname name1

// lab4
// Here are examples for 'list' command. You can change it to 'map' or 'set'

java -jar textile.jar list add 123-a name1 100 30 socks
java -jar textile.jar list add 234-b name2 1200 15 bathrobe
java -jar textile.jar list add 345-c name3 300 20 pillowcase
java -jar textile.jar list add 567-d name4 2000 10 mattress
java -jar textile.jar list getbyarticle 123-a
java -jar textile.jar list change 234-b name1name 150 35 socks
java -jar textile.jar list sale 123-a
java -jar textile.jar list sale 234-b,345-c,567-d
java -jar textile.jar list getsale 2
java -jar textile.jar list changesale 345-c,567-d 2
java -jar textile.jar list delsale 1

// lab5

java -jar textile.jar manyone add 123-a name 2000 15 mattress
java -jar textile.jar manyone getbyarticle 123-a
java -jar textile.jar manyone doc 123-a 5,posting 2,writeoff 7,sale
java -jar textile.jar manyone runtime

java -jar textile.jar oneone add 123-a name 2000 200 150 10
java -jar textile.jar oneone runtime

