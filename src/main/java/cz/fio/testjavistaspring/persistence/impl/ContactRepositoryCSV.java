package cz.fio.testjavistaspring.persistence.impl;

import com.opencsv.exceptions.CsvException;
import cz.fio.testjavistaspring.entity.ContactEntity;
import cz.fio.testjavistaspring.persistence.IContactRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVWriter;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepositoryCSV implements IContactRepository {

    private static final String FILE_NAME = "contacts.csv";

    @Override
    public String saveContact(ContactEntity newContactEntity) {
        File csvFile = createCsvFile();

        if (csvFile == null) {
            return "Unable to save new record... Error during creating CSV file.";
        }

        List<ContactEntity> existingContacts = getExistingContacts(csvFile);

        if (existingContacts == null) {
            return "Unable to load CSV file.";
        }

        if (existingContacts.contains(newContactEntity)) {
            return "Entered contact already exists in CSV file... Skipping...";
        }

        if (!writeNewContactIntoCsv(csvFile, newContactEntity)) {
            return "Unable to save new record into CSV File.";
        }

        return "New contact has been written to CSV file successfully!";
    }

    private File createCsvFile() {
        String tempDir = System.getProperty("java.io.tmpdir");

        File csvFile = new File(tempDir + FILE_NAME);

        if (!csvFile.exists()) {
            try {
                csvFile.createNewFile();
            }
            catch (IOException e) {
                return null;
            }
        }

        return csvFile;
    }

    private boolean writeNewContactIntoCsv(File csvFile, ContactEntity newContactEntity) {
        FileWriter fileWriter;
        CSVWriter csvWriter;
        String[] fields;

        try {
            fileWriter = new FileWriter(csvFile, true);
            csvWriter = new CSVWriter(fileWriter);

            fields = convertEntityIntoStrArr(newContactEntity);

            csvWriter.writeNext(fields);

            csvWriter.close();
            fileWriter.close();

            return true;
        }
        catch (IOException e) {
            return false;
        }
    }

    private String[] convertEntityIntoStrArr(ContactEntity newContactEntity) {
        String[] newContactEntityArr = new String[3];

        newContactEntityArr[0] = newContactEntity.getFirstName();
        newContactEntityArr[1] = newContactEntity.getLastName();
        newContactEntityArr[2] = newContactEntity.getEmail();

        return newContactEntityArr;
    }

    private List<ContactEntity> getExistingContacts(File csvFile) {
        CSVReader csvReader;
        List<String[]> existingData;

        try {
            csvReader = new CSVReader(new FileReader(csvFile));
            existingData = csvReader.readAll();
            csvReader.close();

            return convertStrToContact(existingData);
        }
        catch (IOException | CsvException e) {
            return null;
        }
    }

    private List<ContactEntity> convertStrToContact(List<String[]> strDataList) {
        List<ContactEntity> contactEntities = new ArrayList<>(strDataList.size());

        for (String[] currentStrData : strDataList) {
            contactEntities.add(new ContactEntity(
                    currentStrData[0],
                    currentStrData[1],
                    currentStrData[2]
            ));
        }

        return contactEntities;
    }
}
