package jsonaddressparser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.List;
import jsonaddressparser.Models.Address;
/**
 *
 * @author Colin
 */

public class JsonAddressParser {

    public static void main(String[] args) { 
    try {
       printAddresses("C:\\Users\\Colin\\Documents\\NetBeansProjects\\JsonAddressParser\\src\\jsonaddressparser\\addresses.json");
    }catch(Exception e)
    {
        System.out.println(e);
    }
    
    }
    public static String prettyPrintAddress(Address address) {

        //First Validate address
        String pretty_address ="";
        if(ValidAddres(address)){
          pretty_address =  address.getType().getName()+" :"
                               + address.getAddressLineDetail().getLine1()+ "\n"
                               + address.getAddressLineDetail().getLine2()+"\n"
                               + address.getCityOrTown()+"\n"
                               + address.getPostalCode()+"\n"
                               + address.getCountry().getName()+"\n" ;
        }
          return pretty_address;
     }
    
    public static void printAddresses(String filePath) throws Exception
    {
        Gson gson =new Gson();
      
        String addressInfoJson= new String(Files.readAllBytes(Paths.get(filePath)));
        Type listType = new TypeToken<List<Address>>(){}.getType();
        List<Address> addresses = gson.fromJson(addressInfoJson, listType);
         
        for (Address address : addresses){
            System.out.println("NEW ADDRESS");
            System.out.println(prettyPrintAddress(address));
        }
        
    }
    
 public static void printTypeAddress(String Type,List<Address> Addresses){
     
     for(Address address:Addresses){
         if(address.getType().getName().equals(Type))
             prettyPrintAddress(address);
     }

    }
    
    public static boolean ValidAddres(Address address)
    {
        //Valid numerical postal code
        
      boolean Valid =true;
      String error_message ="";
      if (!(address.getPostalCode().matches("[0-9]+"))) {
            Valid =false;
            error_message = "Invalid PostalCode\n";
        }
     
      //Validate Country      
      if(address.getCountry().getCode().equals(""))
        {
            Valid =false;
            error_message ="Country field is blank\n";
        }
      
      if (address.getCountry().getCode().equals("ZA")){
           if(address.getProvinceOrState()==null)
           {
               Valid= false;
               error_message ="Province needs to exist for South Africa\n";
           }       
      }
      
    if(address.getAddressLineDetail()==null){
        Valid= false;
        error_message ="Address does not exist\n";
        
    } else if(address.getAddressLineDetail().getLine1().equals("")&& 
            address.getAddressLineDetail().getLine2().equals(""))
    {
        Valid= false;
        error_message ="Address is empty\n";
    }
    
      
      System.out.println(error_message);
      return Valid;
    }
    
   
}
