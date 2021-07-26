package uz.developers.appwarehouse.controller.warehouse;

import org.hibernate.dialect.identity.CUBRIDIdentityColumnSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.developers.appwarehouse.dto.CurrencyDto;
import uz.developers.appwarehouse.dto.WarehouseDto;
import uz.developers.appwarehouse.entity.warehouseEntity.Currency;
import uz.developers.appwarehouse.entity.warehouseEntity.Warehouse;
import uz.developers.appwarehouse.results.Result;
import uz.developers.appwarehouse.service.worehouseService.CurrencyService;
import uz.developers.appwarehouse.service.worehouseService.WarehouseService;


@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @PostMapping
    public Result add(@RequestBody CurrencyDto currencyDto) {
        return currencyService.add(currencyDto);
    }

    @DeleteMapping("/{id}")
    public Result noActive(@PathVariable Long id) {
        return currencyService.delete(id);
    }

    @PostMapping("/active/{id}")
    public Result active(@PathVariable Long id) {
        return currencyService.active(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Long id, @RequestBody CurrencyDto currencyDto) {
        return currencyService.edit(id, currencyDto);
    }

    @GetMapping("/{id}")
    public Currency getbyId(@PathVariable Long id) {
        return currencyService.getById(id);
    }

    @GetMapping("/all")
    public Page<Currency> getAll(@RequestParam int page) {
        return currencyService.getAll(page);
    }


}
