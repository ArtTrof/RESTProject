In this project I have created a REST API that takes data from the imaginary weather sensor and sends it via HTTP to the created REST API service.
The sensor is created with a specific request and in case of creating a sensor with the same name an error will be received.

1)adding non existing sensor

![scr1](https://user-images.githubusercontent.com/118562434/218767204-32ac252f-6c6a-4e8c-89f7-c143b9c71858.png)

2)adding already existing

![image](https://user-images.githubusercontent.com/118562434/218768174-cd57272b-d534-4073-82d4-8129de34a224.png)

Here what we ll have in dataBase

![image](https://user-images.githubusercontent.com/118562434/218768019-e2868e89-ffb4-4e24-83d9-987563dcfcd7.png)

To this sensors we can add measurements data that include temperature value , data if day is raining  and sensor which did this measurements

Posting measurement 

![image](https://user-images.githubusercontent.com/118562434/218768725-f997312a-cb2f-4483-a6e1-94515b27c0db.png)

Getting all measurements  

![image](https://user-images.githubusercontent.com/118562434/218768908-33e4e91e-6a88-45f6-a30a-086d98b1bd45.png)

Getting count of rainy days

![image](https://user-images.githubusercontent.com/118562434/218769061-d41eaed9-235c-4a6f-85d1-9a8383a65a28.png)

Here what we ll have in measurement database

![image](https://user-images.githubusercontent.com/118562434/218769217-6ae6ccbb-eae4-4146-9596-c4359267cf09.png)

Also added client where were tested 500 measurements insert andsensor insert
