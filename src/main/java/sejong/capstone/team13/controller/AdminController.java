package sejong.capstone.team13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import sejong.capstone.team13.model.request.ChangePassword;
import sejong.capstone.team13.model.request.PasswordRequest;
import sejong.capstone.team13.model.response.LoginResponse;
import sejong.capstone.team13.repository.PasswordRepository;

@Controller
public class AdminController {

    PasswordRepository passwordRepository; @Autowired
    AdminController(PasswordRepository passwordRepository){
        this.passwordRepository = passwordRepository;
    }

    @PostMapping("/login")
    @ResponseBody
    public LoginResponse login(@RequestBody PasswordRequest pwdReq){
        String pwd = pwdReq.getPwd();
        if(pwd==null || pwd.isEmpty() || pwd.isBlank()){
            return new LoginResponse(false, "Nothing Entered");
        }if(!pwd.equals(passwordRepository.getPwd())){
            return new LoginResponse(false, "Wrong Password");
        }else{
            return new LoginResponse(true, "Success");
        }
    }

    @PostMapping("/change-password")
    public void changePwd(@RequestBody ChangePassword cp){
        System.out.println(cp);
        // 검증 과정


        // 변경 과정
        passwordRepository.updatePwd(cp.getAfter());
    }
}
