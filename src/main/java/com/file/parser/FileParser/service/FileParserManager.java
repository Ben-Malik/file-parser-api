package com.file.parser.FileParser.service;

import com.file.parser.FileParser.exception.FileParserException;
import com.file.parser.FileParser.model.ParsedFile;

import java.io.File;
import java.util.List;

/**
 * The interface for the parse file service.
 *
 * @author ben-malik
 */
public interface FileParserManager {

    /**
     * Grabs all files available in the given path and matching the allowed file extensions.
     * @param path The path where files are to be grabbed.
     * @return a list of files.
     * @throws FileParserException if the path is malformed or not existing.
     */
    List<File> getAllFiles(String path) throws FileParserException;

    /**
     * Parses all files valid files available in the given path into {@linkplain ParsedFile}.
     * @param path The path whose files are to be parsed.
     * @return a list of {@linkplain ParsedFile}s.
     * @throws FileParserException if the given path doesn't exist or is malformed.
     */
    List<ParsedFile> parseAllFiles(String path)  throws FileParserException;

    /**
     * Moves the given file into the processed folder and deletes it from its previous location.
     * @param file The file to be moved.
     * @throws FileParserException in case the given file is null or doesn't exist.
     */
    void moveFileToProcessed(File file)  throws FileParserException;
}
