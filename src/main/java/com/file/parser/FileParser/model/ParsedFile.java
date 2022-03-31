package com.file.parser.FileParser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * An encapsulation to the files parsed.
 *
 * @author ben-malik
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParsedFile {

    private String filename;
    private Long wordCount;
    private Long dotCount;
    private Long specialCharacterCount;

    /** This is a list becuase a file may contain more than one word having the maximum occurrence number. */
    private List<String> mostFrequentWords;
}
