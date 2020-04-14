package com.pavel.kalegin.string.encoder;

import com.pavel.kalegin.string.encoder.service.StringEncoder;
import com.pavel.kalegin.string.encoder.service.impl.CellPhoneKeyboardLayoutEncoder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

@SpringBootTest
class GoogleTaskTest {

    @ParameterizedTest
    @CsvSource(value =
            {
                    "test_small.in:test_small.out",
                    "test_large.in:test_large.out"
            },
            delimiter = ':')
    void writeToFileOnSite(String inputFile, String outputFile) {
        StringEncoder stringEncoder = new CellPhoneKeyboardLayoutEncoder();
        try (Scanner scanner =
                     new Scanner(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(inputFile)))) {
            int numberOfExamples = Integer.parseInt(scanner.nextLine());
            try (BufferedWriter writer =
                         new BufferedWriter(new FileWriter(getClass().getClassLoader().getResource(outputFile).getPath()))) {
				for(int i = 1; i <=numberOfExamples; i++) {
				    String val = scanner.nextLine();
                    System.out.println(val);
					writer.write("Case #" + i + ": " + stringEncoder.encode(val));
					writer.newLine();
				}
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
