How to run:
1) go to project folder 
2) run from command line 'gradle clean build'
3) execute from command line 'java com.axondevgroup.reviews.food.FoodReviewsApplication' with command line arguments :
    file=<fileName>|<fullPathToFile> - csv file with data, not required, if not defined - file PATH_TO_DEFAULT_FILE will be used
    translate=true                   - flag which determines need to execute translation or no (true = need), not required, default = false



Some answers for the questions:
2) Regarding duplications - we can use method distinct() in com/axondevgroup/reviews/food/SparkFoodReviewsAnalyzer.java:69
4) Properties spark.driver.memory and spark.executor.memory (file spark.properties). For monitoring we can use http://192.168.0.17:4040 (for local spark)
5) Properties in the file spark.properties, local[4] spark master, minPartitions = 4