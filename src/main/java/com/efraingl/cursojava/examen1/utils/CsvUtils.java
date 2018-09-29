package com.efraingl.cursojava.examen1.utils;

import com.efraingl.cursojava.examen1.models.StockRegistry;
import org.supercsv.cellprocessor.ParseBigDecimal;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Paths;
import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.Reader;
import java.nio.file.Files;

public class CsvUtils {

    public static List<StockRegistry> readStockRegistryCsvFileFromResources(String fileName)
    {
        List<StockRegistry> csvData = null;

        try {

            URI uri = ClassLoader.getSystemClassLoader().getResource(fileName).toURI();
            CsvUtils.setFileSystem(uri);
            Reader reader = Files.newBufferedReader(Paths.get(uri));

            final CsvPreference PIPE_DELIMITED = new CsvPreference.Builder(' ', ';', "\n").build();
            ICsvBeanReader beanReader = new CsvBeanReader(reader, PIPE_DELIMITED);

            final String[] header = beanReader.getHeader(true);
            final CellProcessor[] processors = getProcessors();

            StockRegistry stockRegistry;
            csvData = new ArrayList<StockRegistry>();

            while ((stockRegistry = beanReader.read(StockRegistry.class, header, processors)) != null) {
                csvData.add(stockRegistry);
            }

        } catch (IOException e) {
            //log.error(e.getMessage());
            throw new ExceptionInInitializerError(e);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return csvData;
    }

    private static void setFileSystem(URI uri) throws IOException {
        if("jar".equals(uri.getScheme())){
            for (FileSystemProvider provider: FileSystemProvider.installedProviders()) {
                if (provider.getScheme().equalsIgnoreCase("jar")) {
                    try {
                        provider.getFileSystem(uri);
                    } catch (FileSystemNotFoundException e) {
                        provider.newFileSystem(uri, Collections.emptyMap());
                    }
                }
            }
        }
    }

    private static CellProcessor[] getProcessors(){
        return new CellProcessor[] {
                new ParseDate("dd-MM-yyyy"),
                new ParseBigDecimal(),
                new ParseBigDecimal()

        };
    }
}