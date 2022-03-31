package com.file.parser.FileParser.constant;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * @author ben-malik
 */
public final class APIConstants {

    public static final List<String> ALLOWED_FILE_EXTENSIONS = Arrays.asList("txt", "doc", "pdf", "md", "java", "py");

    public static final String DEFAULT_DIRECTORY_PATH =  Paths.get("").toAbsolutePath() + "/src/main/resources/data/";
    public static final String DEFAULT_DIRECTORY_PATH_WITH_NO_VALID_FILE =  Paths.get("").toAbsolutePath() + "/src/main/resources/data/noValidFile";

    public static final String PROCESSED_FILES_DIRECTORY = "processed/";

    public static final String FILE_DOES_NOT_EXIST_ERROR_MESSAGE = "File not found.";

    public static final String INVALID_PATH_ERROR_MESSAGE = "Invalid path.";


    public static final String ERROR_GETTING_FILES = "An error has occurred when trying to get the files in the given folder.";

    public static final String WORD_SEPARATOR = " ";

    public static final String DOT = ".";

    public static final String CHARACTER_REGEX = "[^A-Za-z0-9]";

    public static final String FILE_SUCCESSFULLY_MOVED_MESSAGE = "File successfully move to the processed folder.";

    public static final String ERROR_MOVING_FILE_MESSAGE = "An error occurred while moving the file into the processed folder.";

    public static final String DIRECTORY_SUCCESSFULLY_CREATED = "Directory successfully created.";

    public static final String ERROR_CREATING_DIRECTORY = "An error has occurred while creating the directory.";

    public static final String BLANK_SPACE = "";
}
