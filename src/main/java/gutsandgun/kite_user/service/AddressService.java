package gutsandgun.kite_user.service;

import gutsandgun.kite_user.repository.read.*;
import gutsandgun.kite_user.repository.write.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressService {
    //group
    private final ReadUserGroupRepository readUserGroupRepository;
    private final WriteUserGroupRepository writeUserGroupRepository;

    //group-adress
    private final ReadAddressGroupRepository readAddressGroupRepository;
    private final WriteAddressGroupRepository writeAddressGroupRepository;

    //address
    private final ReadUserAddressRepository readUserAddressRepository;
    private final WriteUserAddressRepository writeUserAddressRepository;

    private final ReadAddressPhoneRepository readAddressPhoneRepository;
    private final WriteAddressPhoneRepository writeAddressPhoneRepository;

    private final ReadAddressEmailRepository readAddressEmailRepository;
    private final WriteAddressEmailRepository writeAddressEmailRepository;
}
