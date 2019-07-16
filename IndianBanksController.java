package IndianBanks;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
public class IndianBanksController {

    @RequestMapping(value = "/branch", method = RequestMethod.GET, produces = {"application/json; charset=UTF-8"})
    public Object getBranchDetails(@RequestParam(value = "ifsc", defaultValue = "") String ifsc, @RequestParam(value = "bank_name", defaultValue = "") String bank_name, @RequestParam(value = "city", defaultValue = "") String city){
        Object result = null;
        if(!bank_name.contentEquals("") && !city.contentEquals(""))
            result = IndianBanksDBService.getAllBankBranchesInCity(bank_name.toUpperCase(), city.toUpperCase());
        if(!ifsc.contentEquals(""))
            result = IndianBanksDBService.getBranchDetailsFromIfsc(ifsc.toUpperCase());
        return result;
    };

    @RequestMapping(value = "/bank", method = RequestMethod.GET, produces = {"application/json; charset=UTF-8"})
    public Object getBankNames(){
        return IndianBanksDBService.getAllBankNames();
    };
}
