import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class PrescriptionTest {
    private Prescription prescription;

    @BeforeEach
    void setUp() {
        prescription = new Prescription(
                1, "John", "Doe", "123 Main St, Suburb, 1234, Country",
                1.5f, 90f, 0.5f, new Date(), "Dr. Jane Smith"
        );
    }

    @Test
    void testAddPrescriptionValid() {
        assertTrue(prescription.addPrescription());
    }

    @Test
    void testAddPrescriptionInvalidFirstName() {
        prescription = new Prescription(
                1, "Jo", "Doe", "123 Main St, Suburb, 1234, Country",
                1.5f, 90f, 0.5f, new Date(), "Dr. Jane Smith"
        );
        assertFalse(prescription.addPrescription());
    }

    @Test
    void testAddPrescriptionInvalidAddress() {
        prescription = new Prescription(
                1, "John", "Doe", "123 Main St",
                1.5f, 90f, 0.5f, new Date(), "Dr. Jane Smith"
        );
        assertFalse(prescription.addPrescription());
    }

    @Test
    void testAddPrescriptionInvalidSphere() {
        prescription = new Prescription(
                1, "John", "Doe", "123 Main St, Suburb, 1234, Country",
                25f, 90f, 0.5f, new Date(), "Dr. Jane Smith"
        );
        assertFalse(prescription.addPrescription());
    }

    @Test
    void testAddRemarkValid() {
        assertTrue(prescription.addRemark("This is a valid remark for the client.", "Client"));
    }

    @Test
    void testAddRemarkInvalidWordCount() {
        assertFalse(prescription.addRemark("Short", "Client"));
    }

    @Test
    void testAddRemarkInvalidType() {
        assertFalse(prescription.addRemark("This is a valid remark, but invalid type.", "Invalid"));
    }

    @Test
    void testAddRemarkExceedLimit() {
        assertTrue(prescription.addRemark("This is the first valid remark.", "Client"));
        assertTrue(prescription.addRemark("This is the second valid remark.", "Optometrist"));
        assertFalse(prescription.addRemark("This is the third remark, which should fail.", "Client"));
    }
}
