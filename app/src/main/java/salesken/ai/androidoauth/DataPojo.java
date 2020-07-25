package salesken.ai.androidoauth;

public class DataPojo {
    private String name;
    private String age;

    public DataPojo() {
    }

    public DataPojo(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
