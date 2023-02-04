package hh;

public class Resume {
    private final String gender;
    private final int age;
    private final String town;
    private final Boolean readyDislocated;
    private final Boolean confirmedPhone;

    public Resume(String gender, int age, String town, Boolean readyDislocated, Boolean confirmedPhone) {
        this.gender = gender;
        this.age = age;
        this.town = town;
        this.readyDislocated = readyDislocated;
        this.confirmedPhone = confirmedPhone;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getTown() {
        return town;
    }

    public Boolean getReadyDislocated() {
        return readyDislocated;
    }

    public Boolean getConfirmedPhone() {
        return confirmedPhone;
    }
}
