package api.reqres.users;

public class SuccessUserUpdate {
    private String updatedAt;
    private String name;
    private String job;

    public SuccessUserUpdate() {
//        super(name, job);
//        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }
}
