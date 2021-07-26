package uz.developers.appwarehouse.service.worehouseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.developers.appwarehouse.dto.ClientDto;
import uz.developers.appwarehouse.dto.SupplierDto;
import uz.developers.appwarehouse.entity.warehouseEntity.Client;
import uz.developers.appwarehouse.entity.warehouseEntity.Supplier;
import uz.developers.appwarehouse.repository.warehouse.ClientRepository;
import uz.developers.appwarehouse.repository.warehouse.SupplierRepository;
import uz.developers.appwarehouse.results.Result;

import java.util.Optional;

@Service
public class ClientService {


    @Autowired
    ClientRepository clientRepository;

    @Autowired
    Result result;

    public Result add(ClientDto clientDto){
        boolean b = clientRepository.existsByName(clientDto.getName());
        if (b){
            result.setMessage("Sorry !This client has ben saved");
            result.setSuccess(false);
            return result;
        }
        boolean b1 = clientRepository.existsByPhoneNumber(clientDto.getPhoneNumber());

        if (b1){
            result.setMessage("Sorry !This phone Number has ben saved");
            result.setSuccess(false);
            return result;
        }

       Client  client =new Client();
        client.setActive(true);
        client.setName(clientDto.getName());
        client.setPhoneNumber(clientDto.getPhoneNumber());



       clientRepository.save(client);
        result.setMessage("successfully saved");
        result.setSuccess(true);
        return result;

    }

    public Result edit(Long id, ClientDto clientDto){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent()){
            result.setMessage("this client not found");
            result.setSuccess(false);
            return result;
        }

        boolean b = clientRepository.existsByName(clientDto.getName());
        if (b){
            result.setMessage("Sorry ! This client has ben saved! ");
            result.setSuccess(false);
            return result;
        }

        boolean b1 =clientRepository.existsByPhoneNumber(clientDto.getPhoneNumber());

        if (b1){
            result.setMessage("Sorry !This phone Number has ben saved");
            result.setSuccess(false);
            return result;
        }
        Client client=optionalClient.get();
        //currency.setActive(true);
        client.setName(clientDto.getName());
        client.setPhoneNumber(clientDto.getPhoneNumber());


        clientRepository.save(client);
        result.setMessage("successfully edited");
        result.setSuccess(true);
        return result;
    }

    public Result active(Long id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent()){
            result.setMessage("this client not found");
            result.setSuccess(false);
            return result;
        }
      Client client = optionalClient.get();

        if (client.isActive()) {
            result.setMessage("this client active");
            result.setSuccess(false);
            return result;
        }
        client.setActive(true);

        clientRepository.save(client);
        result.setMessage("succesfully actived");
        result.setSuccess(true);
        return result;
    }

    public Result delete(Long id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent()){
            result.setMessage("this client not found");
            result.setSuccess(false);
            return result;
        }
        Client client= optionalClient.get();
        if (!client.isActive()) {
            result.setMessage("this client no active");
            result.setSuccess(false);
            return result;
        }
       client.setActive(false);
        clientRepository.save(client);
        result.setMessage("succesfully no actived");
        result.setSuccess(true);
        return result;
    }

    public Client getById(Long id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent()){
            return null;
        }
        return optionalClient.get();

    }

    public Page<Client> getAll(int page){
        Pageable pageable= PageRequest.of(page,10);
        return clientRepository.findAll(pageable);
    }


}
