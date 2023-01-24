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


    @GetMapping("")
    public List<ResponseAddressWithGroupDto> getAddressList(@PageableDefault(size=5, sort="id", direction = Sort.Direction.ASC) Pageable pageable){
        Long userId = 1L;
        return (addressService.getUserAddressList(userId,pageable));
    }

    @GetMapping("/filter")
    public List<ResponseAddressWithGroupDto> getAddressListWithNameFilter(@RequestParam HashMap<String,String> paramMap,@PageableDefault(size=5, sort="id", direction = Sort.Direction.ASC) Pageable pageable){
        Long userId = 1L;
        if(paramMap.get("name")!=null){
            return addressService.getUserAddressListFilterName(userId,paramMap.get("name"),pageable);
        }
        if(paramMap.get("phone")!=null){
            return addressService.getUserAddressListFilterPhone(userId,paramMap.get("phone"),pageable);
        }
        if(paramMap.get("email")!=null){
           return addressService.getUserAddressListFilterEmail(userId,paramMap.get("email"),pageable);
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
