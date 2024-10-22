import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Create a new Prescription object
        Prescription prescription = new Prescription(
                1, "John", "Doel", "123 Main St, Suburb, 1234, Country",
                1.5f, 90f, 0.5f, new Date(), "Dr. Jane Smith"
        );

        // Add the prescription
        boolean addedPrescription = prescription.addPrescription();
        System.out.println("Prescription added: " + addedPrescription);

        // Add remarks
        boolean addedRemark1 = prescription.addRemark("This is a valid remark for the client.", "Client");
        System.out.println("First remark added: " + addedRemark1);

        boolean addedRemark2 = prescription.addRemark("This is a second valid remark from the optometrist.", "Optometrist");
        System.out.println("Second remark added: " + addedRemark2);

        // Try to add a third remark (should fail)
        boolean addedRemark3 = prescription.addRemark("This third remark should not be added.", "Client");
        System.out.println("Third remark added: " + addedRemark3);

        // Print out some information
        System.out.println("Prescription ID: " + prescription.getPrescID());
        System.out.println("Patient Name: " + prescription.getFirstName() + " " + prescription.getLastName());
        System.out.println("Remarks: " + prescription.getPostRemarks());
    }
}