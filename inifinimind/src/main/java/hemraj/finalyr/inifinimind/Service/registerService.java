package hemraj.finalyr.inifinimind.Service;

import hemraj.finalyr.inifinimind.Model.Input;
import hemraj.finalyr.inifinimind.Model.Register;
import hemraj.finalyr.inifinimind.Repository.InputRepository;
import hemraj.finalyr.inifinimind.Repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class registerService implements serviceImplementation{
    @Autowired
    InputRepository inputRepo;
    @Autowired
    RegisterRepository regRepo;
    @Override
    public String registerUser(Register register) {
        regRepo.save(register);
        return "User Registered Successfully";
    }
    @Override
    public ArrayList<Register> getRegisteredData(){
        ArrayList<Register> ls= (ArrayList<Register>) regRepo.findAll();
        return ls;
    }
    static String prev="";
    @Override
    public void saveInputData(Input i) {
        String userInput=i.getInputText();
    }
}
