package app.beetlebug.db;

public class CustomerModel {

    private int id;
    private String name;
    private int age;
    private String flag;


    // constructor
    public CustomerModel(int id, String name, int age, String isFlag) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.flag = isFlag;
    }

    // non-parameterized constructor
    public CustomerModel() {
    }

    // toString method


    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", flag=" + flag +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String isFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}

