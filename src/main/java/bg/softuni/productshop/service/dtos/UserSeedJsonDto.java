package bg.softuni.productshop.service.dtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

public class UserSeedJsonDto {
    @Expose
    private String firstName;
    @Expose
    @Length(min=3)
    private String lastName;
    @Expose
    private int age;

    public UserSeedJsonDto() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
