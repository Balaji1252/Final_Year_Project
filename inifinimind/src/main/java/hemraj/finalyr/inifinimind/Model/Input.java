package hemraj.finalyr.inifinimind.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "input")
public class Input {
    @Id
    private String emailId;
    @Column(nullable = false)
    private String inputText;

    public Input() {}

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public Input(String emailId, String inputText) {
        this.emailId = emailId;
        this.inputText = inputText;
    }
}
