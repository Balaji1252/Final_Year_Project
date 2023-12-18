package hemraj.finalyr.inifinimind.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "Register")
public class Register {
    @Id
    private String emailid;
    private String name;
    private long  mobileno;
    private String password;

    public Register() {
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMobileno() {
        return mobileno;
    }

    public void setMobileno(long mobileno) {
        this.mobileno = mobileno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Register(String name, String emailid, long mobileno, String password) {
        this.name = name;
        this.emailid = emailid;
        this.mobileno = mobileno;
        this.password = password;
    }
}
