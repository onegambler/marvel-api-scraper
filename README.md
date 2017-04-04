# marvel-api-scraper
Marvel API Characters Scraper

## How to build and run

Clone the project from github.

Open the file `application.properties` and add correct keys - both public and private.

Install maven and run the following command.

```
mvn clean install
```

to run

```
java -jar marvel-scraper-1.0-SNAPSHOT.jar
```

## Problems

The API seems a bit unstable, I haven't been able to download the whole set of comics without errors. 
I added a simple retry mechanism but the fetching takes ages.
For this reason there's a setting in `application.properties` to enable/disable the comics fetching: `download.comics`.
In case it's disabled, it will create a `comic` list based on the information contained in the characters data.
I'm aware it only contains a subset of the existing comics and that results will be affected. But that's the best
I could do.