package hemraj.finalyr.inifinimind.Controller;

import hemraj.finalyr.inifinimind.Model.Register;
import hemraj.finalyr.inifinimind.Service.registerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/infinimindai")
public class registerController {
    @Autowired
    registerService registerService;

    @PostMapping("/registerUsers")
    public void register(@RequestBody Register registerData){
        registerService.registerUser(registerData);
    }
    @GetMapping("/getRegisterUsers")
    public ArrayList<Register> getUsers(){
        ArrayList<Register> ls= registerService.getRegisteredData();
        return ls;
    }

}
