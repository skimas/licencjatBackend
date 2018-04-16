package com.wlazlowski.licencjat;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/logs")
public class ModuleController {

    String lastTimeStamp = "ola";

    private final ModuleRepository moduleRepository;

    @Autowired
    public ModuleController(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    /*
    @RequestMapping(method = RequestMethod.POST)
    public void addRFIDModule(@RequestBody RFIDModule module){
        moduleRepository.save(module);
    }
    */

    @GetMapping("/all")
    public List<RFIDModule> getModules(){
        return moduleRepository.findAll();
    }

    @PutMapping("/add")
    public void insert(@RequestBody RFIDModule rfidModule){
        if(lastTimeStamp.equals("ola")) {
            lastTimeStamp=rfidModule.getTimestamp();
        } else {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                Date parsedDate = dateFormat.parse(lastTimeStamp);
                Timestamp PrevTimestamp = new java.sql.Timestamp(parsedDate.getTime());
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Long difference = timestamp.getTime() - PrevTimestamp.getTime();
                rfidModule.setTime(difference);
                lastTimeStamp = timestamp.toString();
            } catch(Exception e) { //this generic but you can control another types of exception
                // look the origin of excption
            }
        }
        this.moduleRepository.insert(rfidModule);
    }

    @PostMapping
    public void update(@RequestBody RFIDModule rfidModule){
        this.moduleRepository.save(rfidModule);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") String id){
        this.moduleRepository.deleteById(id);
    }

    @GetMapping("/find/{id}")
    public Optional<RFIDModule> getByID(@PathVariable("id") String id){
        return this.moduleRepository.findById(id);
    }

    @GetMapping("/time/{minTime}")
    List <RFIDModule> getByTime(@PathVariable("minTime") Long minTime){
        List<RFIDModule> modules = this.moduleRepository.findByTimeGreaterThan(minTime);

        return modules;
    }
}
