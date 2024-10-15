import java.util.ArrayList;
import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class Prescription {
    private int prescID;
    private String firstName;
    private String lastName;
    private String address;
    private float sphere;
    private float axis;
    private float cylinder;
    private Date examinationDate;
    private String optometrist;
    private String[] remarkTypes = {"Client", "Optometrist"};
    private ArrayList<String> postRemarks = new ArrayList<>();

    // Constructor
    public Prescription(int prescID, String firstName, String lastName, String address,
                        float sphere, float axis, float cylinder, Date examinationDate,
                        String optometrist) {
        this.prescID = prescID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.sphere = sphere;
        this.axis = axis;
        this.cylinder = cylinder;
        this.examinationDate = examinationDate;
        this.optometrist = optometrist;
    }

    public boolean addPrescription() {
        if (!isValidName(firstName) || !isValidName(lastName)) {
            return false;
        }
        if (!isValidAddress(address)) {
            return false;
        }
        if (!isValidSphere(sphere) || !isValidCylinder(cylinder) || !isValidAxis(axis)) {
            return false;
        }
        if (!isValidOptometristName(optometrist)) {
            return false;
        }

        // If all conditions are met, write to file
        try (FileWriter writer = new FileWriter("presc.txt", true)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            String prescriptionInfo = String.format("%d,%s,%s,%s,%.2f,%.2f,%.2f,%s,%s\n",
                    prescID, firstName, lastName, address, sphere, cylinder, axis,
                    dateFormat.format(examinationDate), optometrist);
            writer.write(prescriptionInfo);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addRemark(String remark, String type) {
        if (!isValidRemark(remark) || !isValidRemarkType(type)) {
            return false;
        }
        if (postRemarks.size() >= 2) {
            return false;
        }

        try (FileWriter writer = new FileWriter("remark.txt", true)) {
            String remarkInfo = String.format("%d,%s,%s\n", prescID, type, remark);
            writer.write(remarkInfo);
            postRemarks.add(remark);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isValidName(String name) {
        return name.length() >= 4 && name.length() <= 15;
    }

    private boolean isValidAddress(String address) {
        return address.length() >= 20;
    }

    private boolean isValidSphere(float sphere) {
        return sphere >= -20.00f && sphere <= 20.00f;
    }

    private boolean isValidCylinder(float cylinder) {
        return cylinder >= -4.00f && cylinder <= 4.00f;
    }

    private boolean isValidAxis(float axis) {
        return axis >= 0 && axis <= 180;
    }

    private boolean isValidOptometristName(String name) {
        return name.length() >= 8 && name.length() <= 25;
    }

    private boolean isValidRemark(String remark) {
        String[] words = remark.split("\\s+");
        return words.length >= 6 && words.length <= 20 && Character.isUpperCase(remark.charAt(0));
    }

    private boolean isValidRemarkType(String type) {
        return type.equals("Client") || type.equals("Optometrist");
    }

    // Getters and Setters
    public int getPrescID() {
        return prescID;
    }

    public void setPrescID(int prescID) {
        this.prescID = prescID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getSphere() {
        return sphere;
    }

    public void setSphere(float sphere) {
        this.sphere = sphere;
    }

    public float getAxis() {
        return axis;
    }

    public void setAxis(float axis) {
        this.axis = axis;
    }

    public float getCylinder() {
        return cylinder;
    }

    public void setCylinder(float cylinder) {
        this.cylinder = cylinder;
    }

    public Date getExaminationDate() {
        return examinationDate;
    }

    public void setExaminationDate(Date examinationDate) {
        this.examinationDate = examinationDate;
    }

    public String getOptometrist() {
        return optometrist;
    }

    public void setOptometrist(String optometrist) {
        this.optometrist = optometrist;
    }

    public ArrayList<String> getPostRemarks() {
        return new ArrayList<>(postRemarks);
    }
}