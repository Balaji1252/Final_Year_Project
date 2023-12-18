package hemraj.finalyr.inifinimind.Service;

import hemraj.finalyr.inifinimind.Model.Input;
import hemraj.finalyr.inifinimind.Model.Register;

import java.util.ArrayList;

public interface serviceImplementation {
    public String registerUser(Register register);
    public ArrayList<Register> getRegisteredData();
    public void saveInputData(Input i);
}
