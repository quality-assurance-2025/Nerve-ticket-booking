package org.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	 private static Properties prop;

	    public static void loadProperties() throws IOException {
	        FileInputStream fis = new FileInputStream("E:\\Selenium\\CLSSNervedemo\\src\\test\\resources\\TestData\\TestData.properties");
	        prop = new Properties();
	        prop.load(fis);
	    }

	    public static String get(String key) {
	        return prop.getProperty(key);
	    }
}
