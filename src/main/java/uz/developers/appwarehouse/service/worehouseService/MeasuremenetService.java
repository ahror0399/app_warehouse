package uz.developers.appwarehouse.service.worehouseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.developers.appwarehouse.dto.MeasurementDto;

import uz.developers.appwarehouse.entity.warehouseEntity.Measurement;

import uz.developers.appwarehouse.repository.warehouse.MeasurementRepository;

import uz.developers.appwarehouse.results.Result;

import java.util.Optional;

@Service
public class MeasuremenetService {


    @Autowired
    MeasurementRepository measurementRepository;

    @Autowired
    Result result;

    public Result add(MeasurementDto measurementDto){
        boolean b = measurementRepository.existsByName(measurementDto.getName());
        if (b){
            result.setMessage("Sorry !This Measuremenet has ben saved");
            result.setSuccess(false);
            return result;
        }
        Measurement measurement=new Measurement();
       measurement.setActive(true);
       measurement.setName(measurementDto.getName());
        measurementRepository.save(measurement);
        result.setMessage("successfully saved");
        result.setSuccess(true);
        return result;

    }

    public Result edit(Long id, MeasurementDto measurementDto){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent()){
            result.setMessage("this measurement not found");
            result.setSuccess(false);
            return result;
        }

        boolean b = measurementRepository.existsByName(measurementDto.getName());
        if (b){
            result.setMessage("Sorry ! This measurement has ben saved! ");
            result.setSuccess(false);
            return result;
        }
        Measurement measurement= optionalMeasurement.get();
      // measurement.setActive(true);
       measurement.setName(measurementDto.getName());
        measurementRepository.save(measurement);
        result.setMessage("successfully edited");
        result.setSuccess(true);
        return result;
    }

    public Result active(Long id){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent()){
            result.setMessage("this measurement not found");
            result.setSuccess(false);
            return result;
        }
        Measurement measurement = optionalMeasurement.get();

        if (measurement.isActive()) {
            result.setMessage("this measurement active");
            result.setSuccess(false);
            return result;
        }
        measurement.setActive(true);

        measurementRepository.save(measurement);
        result.setMessage("succesfully actived");
        result.setSuccess(true);
        return result;
    }

    public Result delete(Long id){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent()){
            result.setMessage("this measurement not found");
            result.setSuccess(false);
            return result;
        }
        Measurement measurement= optionalMeasurement.get();
        if (!measurement.isActive()) {
            result.setMessage("this measurement no active");
            result.setSuccess(false);
            return result;
        }
        measurement.setActive(false);
       measurementRepository.save(measurement);
        result.setMessage("succesfully deleted");
        result.setSuccess(true);
        return result;
    }

    public Measurement getById(Long id){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent()){
            return null;
        }
        return optionalMeasurement.get();

    }

    public Page<Measurement> getAll(int page){
        Pageable pageable= PageRequest.of(page,10);
        return measurementRepository.findAll(pageable);
    }

}
