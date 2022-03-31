## The Story
Imagine you have a need to build a big data system to do some analytics. Initially, the source of the data should come from local files but this will change in the future as well as the supported file types and analytics functions.

## Simple File Parser
This technical assessment involves designing and building the following JAVA application:

1. A console application takes a directory path as an argument.
2. The application will then monitor the folder for existing files, as well new files, with the supported file extensions (e.g. txt, pdf, doc...).
3. For each supported file, the application needs to process the file and print statistics on the console (e.g. number of special characters, number of words...).
4. After that, each processed file should be moved to a "processed" sub-folder inside the directory that was passed as an argument.
5. Even though this is a simpler exercise treat it like a production situation and write the code that you would expect to deliver for a live system 

## Implementation
1. you can use any IDE or editor you want
2. you must use Java and can use any framework, library that you think will be helpful for you
3. it's recommended that you add tests
4. you can pack the application with maven
5. Implement the solution using a simple console-based application
6. You only need to support the processing of plain text file
7. You only need to provide support for calculating the number of words, number of dots, and the most used word in the file content.
