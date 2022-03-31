package com.file.parser.FileParser.service;

import com.file.parser.FileParser.constant.APIConstants;
import com.file.parser.FileParser.exception.FileParserException;
import com.file.parser.FileParser.model.ParsedFile;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

/**
 * An implementation to the {@linkplain FileParserManager} interface
 *
 * @author ben-malik
 */
@Service
public class FileParserManagerImpl implements FileParserManager {

    private static final Logger logger = LoggerFactory.getLogger(FileParserManagerImpl.class);

    @Override
    public List<File> getAllFiles(String path) throws FileParserException {
        List<File> allFiles = new ArrayList<>();

        logger.info("Started reading files at {}", path);
        if (path.isEmpty()) {
            throw new FileParserException(APIConstants.INVALID_PATH_ERROR_MESSAGE);
        }
        File folder = new File(path);
        if (!folder.exists()) {
            throw new FileParserException(APIConstants.FILE_DOES_NOT_EXIST_ERROR_MESSAGE + ": " + path);
        }

        File[] files = folder.listFiles();
        if (files == null) {
            throw new FileParserException(APIConstants.ERROR_GETTING_FILES);
        }

        for (File file: files) {
            if (isFileValid(file.getName())) {
                allFiles.add(file);
            }
        }
        logger.info("Success: {} valid files found at the folder {}", allFiles.size(), path);
        return allFiles;
    }

    @Override
    public List<ParsedFile> parseAllFiles(String path) throws FileParserException {
        List<File> availableFiles = getAllFiles(path);
        List<ParsedFile> result = new ArrayList<>();

        if (!CollectionUtils.isEmpty(availableFiles)) {
           createDirectory(path + APIConstants.PROCESSED_FILES_DIRECTORY);
        }

        logger.info("Started parsing files at path: {}", path);
        for (File file: availableFiles) {
            result.add(parse(file));
            moveFileToProcessed(file);
        }
        logger.info("Successfully parsed {} files", result.size());
        return result;
    }

    @Override
    public void moveFileToProcessed(File file) throws FileParserException {

        StringBuilder newPath = new StringBuilder(file.getPath().replace(file.getName(), APIConstants.BLANK_SPACE));
        newPath.append(APIConstants.PROCESSED_FILES_DIRECTORY)
                .append(file.getName());

        if(file.renameTo(new File(newPath.toString())))
        {
            if (file.delete()) {
                logger.info(APIConstants.FILE_SUCCESSFULLY_MOVED_MESSAGE);
            }
        }
        else
        {
            throw new FileParserException(APIConstants.ERROR_MOVING_FILE_MESSAGE);
        }
    }

    /**
     * A helper method to check whether a given file is valid meaning has an extension allowed.
     * @param filename The name of the file to be checked.
     * @return True if allowed and false otherwise.
     */
    private boolean isFileValid(String filename) {
        return APIConstants.ALLOWED_FILE_EXTENSIONS.contains(Files.getFileExtension(filename).toLowerCase(Locale.ROOT));
    }

    /**
     * Parses the given file.
     * @param file The file to be parsed.
     * @return a {@linkplain ParsedFile}.
     */
    private ParsedFile parse(File file) {
        ParsedFile parsedFile = new ParsedFile();
        int wordCount = 0, dotCount = 0, specialCharacterCount = 0;

        Map<String, Integer> wordOccurrenceMap = new HashMap<>();
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String[] words = sc.nextLine().split(APIConstants.WORD_SEPARATOR);
                for (String word: words) {
                    wordCount++;
                    if (word.endsWith(APIConstants.DOT)) {
                        dotCount++;
                    }
                    specialCharacterCount += countSpecialCharacters(word);

                    Integer thisWord = wordOccurrenceMap.get(word);
                    if (thisWord != null) {
                        thisWord++;
                    } else {
                        thisWord = 1;
                    }
                    wordOccurrenceMap.put(word, thisWord);
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileParserException(e.getMessage());
        }

        parsedFile.setFilename(file.getName());
        parsedFile.setDotCount((long) dotCount);
        parsedFile.setSpecialCharacterCount((long) specialCharacterCount);
        parsedFile.setWordCount((long) wordCount);
        parsedFile.setMostFrequentWords(getMaxOccurringWords(wordOccurrenceMap));
        return parsedFile;
    }

    /**
     * A helper method to count the number of special characters given a word.
     * @param word The  word to be checked.
     * @return an integer value.
     */
    private  int countSpecialCharacters(String word) {
        int countSpecial = 0;
        if (word == null || word.trim().isEmpty()) {
            return countSpecial;
        }

        for (int i = 0; i < word.length(); i++) {
            if (word.substring(i, i+1).matches(APIConstants.CHARACTER_REGEX)) {
                countSpecial++;
            }
        }
        return countSpecial;
    }

    /**
     * A helper method to get the words with maximum occurrence.
     * @param words The words to be checked.
     * @return a list of words.
     */
    private List<String> getMaxOccurringWords(Map<String, Integer> words)
    {
        List<String> result = new ArrayList<>();

        int max = 1;

        for (Map.Entry<String, Integer> word :
                words.entrySet()) {

            if (word.getValue() > max) {
                max = word.getValue();
            }
        }

        for (Map.Entry<String, Integer> word :
                words.entrySet()) {

            if (word.getValue() == max) {
                result.add(word.getKey());
            }
        }

        return result;
    }

    /**
     * Creates a directory with the given path if one doesn't exist yet.
     * @param path the path of the new directory to be created.
     */
    private void createDirectory(String path) {
        File newDir = new File(path);
        if (!newDir.exists()){
            if (newDir.mkdirs()) {
                logger.info(APIConstants.DIRECTORY_SUCCESSFULLY_CREATED);
            } else {
                throw new FileParserException(APIConstants.ERROR_CREATING_DIRECTORY);
            }
        }

    }
}
