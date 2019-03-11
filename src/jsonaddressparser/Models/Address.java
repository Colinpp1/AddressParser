package jsonaddressparser.Models;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter @Setter @NoArgsConstructor
public class Address {
        private int id;
	private Type type;
	private AddressLineDetail addressLineDetail;
	private ProvinceOrState provinceOrState;
	private String cityOrTown;
        private Country country;
        private  String postalCode;
        private String suburbOrDistrict;
        private String lastupdated;        
}


 