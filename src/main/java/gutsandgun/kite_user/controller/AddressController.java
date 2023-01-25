package gutsandgun.kite_user.controller;



import gutsandgun.kite_user.dto.addr.RequestAddressDto;
import gutsandgun.kite_user.dto.addr.RequestAddressListDto;
import gutsandgun.kite_user.dto.addr.ResponseAddressWithGroupDto;
import gutsandgun.kite_user.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    //all
    @GetMapping("")
    public List<ResponseAddressWithGroupDto> getAddressList(){
        Long userId = 1L;
        return (addressService.getUserAddressList(userId));
    }

    @GetMapping("/filter")
    public List<ResponseAddressWithGroupDto> getAddressListWithFilter(@RequestParam HashMap<String,String> paramMap){
        Long userId = 1L;
        if(paramMap.get("name")!=null){
            return addressService.getUserAddressListFilterName(userId,paramMap.get("name"));
        }
        if(paramMap.get("phone")!=null){
            return addressService.getUserAddressListFilterPhone(userId,paramMap.get("phone"));
        }
        if(paramMap.get("email")!=null){
            return addressService.getUserAddressListFilterEmail(userId,paramMap.get("email"));
        }
        return null;
    }

    //page
    @GetMapping("/page")
    public List<ResponseAddressWithGroupDto> getAddressPage(@PageableDefault(size=5, sort="id", direction = Sort.Direction.ASC) Pageable pageable){
        Long userId = 1L;
        return (addressService.getUserAddressPage(userId,pageable));
    }

    @GetMapping("/page/filter")
    public List<ResponseAddressWithGroupDto> getAddressPageWithFilter(@RequestParam HashMap<String,String> paramMap,@PageableDefault(size=5, sort="id", direction = Sort.Direction.ASC) Pageable pageable){
        Long userId = 1L;
        if(paramMap.get("name")!=null){
            return addressService.getUserAddressPageFilterName(userId,paramMap.get("name"),pageable);
        }
        if(paramMap.get("phone")!=null){
            return addressService.getUserAddressPageFilterPhone(userId,paramMap.get("phone"),pageable);
        }
        if(paramMap.get("email")!=null){
           return addressService.getUserAddressPageFilterEmail(userId,paramMap.get("email"),pageable);
        }
        return null;
    }


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

    @DeleteMapping("")
    public void deleteAddressList(@RequestBody List<Long> deleteList){
        Long userId = 1L;
        addressService.deleteUserAddress(userId,deleteList);
    }


}
