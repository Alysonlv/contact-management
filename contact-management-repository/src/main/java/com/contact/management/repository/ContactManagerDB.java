package com.contact.management.repository;

import com.contact.management.domain.to.ContactManagementUser;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

/*
Class responsible for deal with the data, this app is using texts files, but could be easily changed to
a NoSQL database
 */
public class ContactManagerDB {

    private final String FILE_PATH;

    private static final String FILE_NAME = "address-book-";

    private static final String FILE_TYPE = ".json";

    public ContactManagerDB(String path) {
        this.FILE_PATH = path;
    }

    private String getDirectoryPath(String fileName) {
        return FILE_PATH + fileName + "/";
    }

    private String getFilePath(String fileName) {
        return getDirectoryPath(fileName) + FILE_NAME + fileName + FILE_TYPE;
    }

    private final File getFile(ContactManagementUser contactManagementUser) {
        return new File(getFilePath(contactManagementUser.getUserFileName()));
    }

    protected final void resetAllAddress() throws IOException {
        File clean = new File(FILE_PATH);
        Files.walk(clean.toPath())
                .map(Path::toFile)
                .forEach(File::delete);
        FileUtils.deleteDirectory(clean);
    }

    protected final void resetAllAddress(ContactManagementUser contactManagementUser) throws IOException {
        File clean = new File(getDirectoryPath(contactManagementUser.getUserFileName()));
        Files.walk(clean.toPath())
                .map(Path::toFile)
                .forEach(File::delete);
        FileUtils.deleteDirectory(clean);
    }

    protected final Optional<String> saveContact(ContactManagementUser contactManagementUser, String address) {
        try {
            File file = getFile(contactManagementUser);
            FileUtils.write(file, address, Charset.defaultCharset());
            return getAddressBook(contactManagementUser);
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    protected final boolean hasAddressBook(ContactManagementUser contactManagementUser) {
        return getFile(contactManagementUser).exists();
    }

    protected final Optional<String> getAddressBook(ContactManagementUser contactManagementUser) {
        try {
            File file = getFile(contactManagementUser);
            return Optional.ofNullable(FileUtils.readFileToString(file));
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }

    }


}
