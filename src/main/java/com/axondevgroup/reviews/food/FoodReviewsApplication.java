package com.axondevgroup.reviews.food;

import com.axondevgroup.reviews.food.exception.InvalidCommandLineArgumentException;

import java.io.IOException;

/**
 * Entry point to the application.
 *
 * @author Denys Storozhenko
 */
public class FoodReviewsApplication {
    private static final String HADOOP_HOME_PROPERTY_NAME = "hadoop.home.dir";
    private static final String HADOOP_HOME_PROPERTY_VALUE = "/winutils";
    private static final String PATH_TO_DEFAULT_FILE = "src/main/resources/Reviews1.csv";
    private static final String FILE_COMMAND_LINE_PARAMETER_NAME = "file";
    private static final String TRANSLATE_COMMAND_LINE_PARAMETER_NAME = "translate";
    private static final String COMMAND_LINE_PARAMETER_SEPARATOR = "=";

    private static String pathToFile = PATH_TO_DEFAULT_FILE;
    private static boolean needTranslate = false;

    // command line arguments :
    // file=<fileName>|<fullPathToFile> - csv file with data, not required, if not defined - file PATH_TO_DEFAULT_FILE will be used
    // translate=true                   - flag which determines need to execute translation or no (true = need), not required, default = false
    public static void main(String[] args) throws InvalidCommandLineArgumentException, IOException {
        System.setProperty(HADOOP_HOME_PROPERTY_NAME, HADOOP_HOME_PROPERTY_VALUE);

        if (args.length > 0) {
            for (String arg: args) {
                parseCommandLineArgument(arg);
            }
        }

        new SparkFoodReviewsAnalyzer().analyze(pathToFile, needTranslate);

        System.out.println(1);
    }

    private static void parseCommandLineArgument(String argument) throws InvalidCommandLineArgumentException {
        String[] splitted = argument.split(COMMAND_LINE_PARAMETER_SEPARATOR);
        if (splitted[0].equalsIgnoreCase(FILE_COMMAND_LINE_PARAMETER_NAME)) {
            pathToFile = splitted[1];
        } else if (splitted[0].equalsIgnoreCase(TRANSLATE_COMMAND_LINE_PARAMETER_NAME)) {
            needTranslate = Boolean.valueOf(splitted[1]);
        } else {
            //commented to allow other parameters
            //throw new InvalidCommandLineArgumentException(splitted[0]);
        }
    }
}
