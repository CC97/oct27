package com.server.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.server.Service.UpdateService;
import com.server.Model.UpdateModel;
import com.server.Request.UpdateRequest;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("updates")
public class UpdateController {
    @Autowired
    UpdateService updateService;

    @GetMapping
    public ResponseEntity<?> getUpdateList(){
        List<UpdateModel> updateList = updateService.getUpdateList();
        return ResponseEntity.ok(updateList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUpdateById(@PathVariable Long id) throws Exception{
        UpdateModel updateModel = updateService.getUpdateById(id);
        return ResponseEntity.ok(updateModel);
    }

    @PostMapping
    public ResponseEntity<?> createUpdate(@RequestBody UpdateRequest updateRequest){
        try {
            UpdateModel updateModel = updateService.createUpdate(updateRequest);
            return ResponseEntity.ok(updateModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUpdate(@PathVariable Long id) throws Exception{
        UpdateModel updateModel = updateService.deleteUpdate(id);
        return ResponseEntity.ok(updateModel);
    }
}
