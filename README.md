# File Parser API
## [Find the Problem Definition Here.](PROBLEM_DEFINITION.md)

### To run the app

``` 
 git clone https://github.com/Ben-Malik/file-parser-api.git
 cd file-parser-api
```

### To create necessary maven updates
```
mvn -N io.takari:maven:wrapper
```

### To clean and install
```
 ./mvnw clean install
```

### To run the app
```
 ./mvnw spring-boot:run
```

**Constraints:**

1. Only files having the allowed extensions are parsed. `"txt", "doc", "pdf", "md", "java", "py"`. This list can be extended from the `APIConstants.java` class.
2. The default path is set to be`/src/main/resources/data/` in the project folder. 

**Solution:**

> ***File Reading***
> ```The getAllFiles(String path)``` grabs all the valid files from the given path if exists, and throws `FileParserException` if not
> and then returns them.

 ***File Parsing***
> ```parseAllFiles(String path)``` invokes the `getAllFiles(String path)` and parses the files returned by the latter.
>

> ***File Moving***
After the parsing is completed, ```moveFile(File file)``` moves the newly parsed file into the 
> `processed` folder if the latter exists, and creates it beforehand in case it doesn't.


>  ***Data models*** 
```
ParsedFile(String filename, Long wordCount, Long dotCount, Long specialCharacterCount, List<String> mostFrequentWords)
```

>  ***Exceptions***
> ```FileParserException(String message)```

>  ***Tests***
> `FileParserManagerTest` tests all the methods. 
> Bear in mind that the method ```testParseAllFilesWhenGivenPathIsValid()``` is expected to fail
> while being run for the second time. 
> Merely because the file will `ben.txt` will have been moved to the `processed` folder by the first run.


**Environment:**

```
Java 11

Junit 

Maven
```