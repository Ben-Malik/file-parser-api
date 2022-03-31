package com.file.parser.FileParser;

import com.file.parser.FileParser.constant.APIConstants;
import com.file.parser.FileParser.exception.FileParserException;
import com.file.parser.FileParser.model.ParsedFile;
import com.file.parser.FileParser.service.FileParserManager;
import com.file.parser.FileParser.service.FileParserManagerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * A class for the test of the methods of the {@linkplain com.file.parser.FileParser.service.FileParserManager} interface.
 *
 * @author ben-malik
 *
 */
public class FileParserManagerTest {

    private FileParserManager fileParserManager;

    @BeforeEach
    void setUp() {
        fileParserManager = new FileParserManagerImpl();
    }

    @Test
    void ensureThatFileParserExceptionIsThrownWhenTheGivenPathDoesNotExist() {
        var exception = Assertions.assertThrows(FileParserException.class, () ->
                fileParserManager.parseAllFiles(APIConstants.BLANK_SPACE));
        Assertions.assertEquals(APIConstants.INVALID_PATH_ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    void ensureThatNoFileIsParsedWhenTheGivenPathHasFilesButNoneOfTheirExtensionsAreAllowed() {
        var parsedFileList = fileParserManager.parseAllFiles(APIConstants.DEFAULT_DIRECTORY_PATH_WITH_NO_VALID_FILE);
        Assertions.assertEquals(0, parsedFileList.size());
    }

    @Test
    void testParseAllFilesWhenGivenPathIsValid() {
        var parsedFileList = fileParserManager.parseAllFiles(APIConstants.TEST_FILES);
        Assertions.assertEquals(1, parsedFileList.size());
        ParsedFile parsedFile = parsedFileList.get(0);
        Assertions.assertEquals(parsedFile.getFilename(), "ben.txt");
        Assertions.assertEquals(parsedFile.getMostFrequentWords().get(0), "HICX");
        Assertions.assertEquals(parsedFile.getDotCount(), 1);
        Assertions.assertEquals(parsedFile.getSpecialCharacterCount(), 4);

    }

}
