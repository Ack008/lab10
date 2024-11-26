package it.unibo.mvc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import it.unibo.mvc.Configuration.Builder;;

/**
 * A class that gives you the config trought a file
 */
public class ConfigFileParser {
    private final Configuration config;
    private final Builder builder;
    /**
     * 
     * @param filename
     */
    public ConfigFileParser(final String filename){
        builder = new Builder();
        try (final BufferedReader in = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = in.readLine()) != null) {
                final String[] parts = line.split(":");
                final String fields = parts[0];
                final String value = parts[1];
                switch (fields) {
                    case "minimum":
                        builder.setMin(Integer.parseInt(value.substring(1)));
                        break;
                    case "maximum":
                        builder.setMax(Integer.parseInt(value.substring(1)));
                        break;
                    case "attempts":
                        builder.setAttempts(Integer.parseInt(value.substring(1)));
                    break;
                    default:
                        break;
                }
            }
        config = builder.build();
        in.close();
        } catch (IOException e){
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 
     * @return
     */
    public Configuration getConfig(){
        return config;
    }
    /**
     * 
     * @return
     */
    public Builder getBuilder(){
        return builder;
    }

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        ConfigFileParser par = new ConfigFileParser("src/main/resources/config.yml");
        var conf = par.getConfig();
        System.out.println(conf.getMin() + " " + conf.getMax() + " " + conf.getAttempts());
    }
}
