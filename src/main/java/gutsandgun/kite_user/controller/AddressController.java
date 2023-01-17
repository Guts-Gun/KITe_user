package gutsandgun.kite_user.controller;



import gutsandgun.kite_user.dto.addr.RequestAddressDto;
import gutsandgun.kite_user.dto.addr.RequestAddressListDto;
import gutsandgun.kite_user.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/address")
public class AddressController {

    @Autowired
    AddressService addressService;

  /*
      @GetMapping("")
      public ResponseAddressDto getUserAddress(){

      }
      @GetMapping("")
      public List<ResponseAddressDto> getUserAddressList(){

      }
      @GetMapping("/group/{groupId}")
      public List<ResponseAddressDto> getUserAddressListByGroupId(){

      }
  */
    @PostMapping("")
    public void createAddress(@RequestBody RequestAddressDto requestAddressDto){
        Long userId = 1L;
        addressService.createUserAddressOne(userId,requestAddressDto);
    }
    @PostMapping("/list")
    public void createAddressList(@RequestBody RequestAddressListDto requestAddressDtoList){
        Long userId = 1L;
        addressService.createUserAddressList(userId,requestAddressDtoList);
    }

/*  @PutMapping("/{groupId}")
    public Long moveGroup() {

    }

    @DeleteMapping("/")
    public String deleteAddressList(){
        return("");
    }

    @DeleteMapping("/")
    public String deleteAddress(){
        return("?");
    }*/




}