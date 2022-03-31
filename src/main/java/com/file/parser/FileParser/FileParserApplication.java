package com.file.parser.FileParser;

import com.file.parser.FileParser.constant.APIConstants;
import com.file.parser.FileParser.model.ParsedFile;
import com.file.parser.FileParser.service.FileParserManager;
import com.file.parser.FileParser.service.FileParserManagerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class FileParserApplication {

	private static final Logger logger = LoggerFactory.getLogger(FileParserApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FileParserApplication.class, args);
		starter();
	}

	public static void starter() {
		FileParserManager fileParserManager = new FileParserManagerImpl();
		List<ParsedFile> parsedFileList = fileParserManager.parseAllFiles(APIConstants.DEFAULT_DIRECTORY_PATH);
		logger.info("Files parsed:");
		parsedFileList.forEach(parsedFile -> logger.info(parsedFile.toString()));
	}

}
