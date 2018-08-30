package ru.savkin;


import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileTest {

    private static DocumentReader documentReader;

    /**
     * Creates object {@DocumentReader}
     */
    @BeforeClass
    public static void initDeocumentReader() {
        documentReader = DocumentReader.getDocumentReader();
    }


    @Test
    public void addTermTest() {
        int oldCount = documentReader.getBookedDatas().size();
        documentReader.addData("MyDefinition", "MyDescription");
        int newCout = documentReader.getBookedDatas().size();
        assertEquals(oldCount + 1,newCout);
        oldCount =documentReader.getBookedDatas().size();
        Definition definition = new Definition("A", "B");
        documentReader.addData(definition);
        newCout = documentReader.getBookedDatas().size();
        assertEquals(oldCount + 1,newCout);
    }

    @Test
    public void removeTermTest() {
        int oldCount = documentReader.getBookedDatas().size();
        documentReader.removeDefinition("MyDefinition");
        int newCout = documentReader.getBookedDatas().size();
        assertEquals(oldCount - 1, newCout);
        assertFalse(documentReader.getBookedDatas().contains("MyDefinition"));
    }


    @Test
    public void combiTest1() {
        String desc = "blabla";
        int oldCount = documentReader.getBookedDatas().size();
        documentReader.addData("MyDefinition", "MyDescription");
        documentReader.editData("MyDefinition", desc);
        Definition def =  API.getSearchResult("MyDefinition");
        assertEquals(desc, def.getDescription());

    }

    @Test
    public void sameObjectTest() {
        DocumentReader d= DocumentReader.getDocumentReader();
        assertTrue(documentReader.hashCode() == d.hashCode()
        );
    }
}
