package IndianBanks;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;



@JsonInclude(JsonInclude.Include.NON_NULL)
public class BranchDetails {
    private String bank_name;
    private String branch;
    private String ifsc;
    private String address;
    private String city;
    private String district;
    private String state;

    BranchDetails(HashMap<String, String> row) {
        this.bank_name = row.get("bank_name");
        this.branch = row.get("branch");
        this.ifsc = row.get("ifsc");
        this.address = row.get("address");
        this.city = row.get("city");
        this.district = row.get("district");
        this.state = row.get("state");
    }

    public String getBank_name() {
        return bank_name;
    }

    public String getBranch() {
        return branch;
    }

    public String getAddress() {
        return address;
    }

    public String getIfsc() {
        return ifsc;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

