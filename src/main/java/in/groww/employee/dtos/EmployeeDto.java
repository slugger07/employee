package in.groww.employee.dtos;

import java.util.Date;

public class EmployeeDto {

    private Long id;

    private String name;

    private String role;

    private Date dob;

    private String gender;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(final String role) {
        this.role = role;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(final Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(final String gender) {
        this.gender = gender;
    }
}
