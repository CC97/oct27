package com.server.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.server.Repository.UpdateRepo;
import com.server.Request.UpdateRequest;
import com.server.Model.UpdateModel;

@Service
public class UpdateService {
    @Autowired
    UpdateRepo updateRepo;

    public List<UpdateModel> getUpdateList(){
        return updateRepo.findAll();
    }

    public UpdateModel getUpdateById(Long id) throws Exception{
        return updateRepo.findById(id).orElseThrow(() -> new Exception("No update found."));
    }


    public UpdateModel createUpdate(UpdateRequest updateRequest) throws Exception{
        try {
            UpdateModel updateModel = new UpdateModel();

            if(updateRequest.getUid() != null && !updateRequest.getUid().equals("")){
                updateModel.setUid(updateRequest.getUid());
            }
            if(updateRequest.getEmail() != null && !updateRequest.getEmail().equals("")){
                updateModel.setEmail(updateRequest.getEmail());
            }
            if(updateRequest.getPassword() != null && !updateRequest.getPassword().equals("")){
                updateModel.setPassword(updateRequest.getPassword());
            }
            if(updateRequest.getName() != null && !updateRequest.getName().equals("")){
                updateModel.setName(updateRequest.getName());
            }
            if(updateRequest.getPhone() != null && !updateRequest.getPhone().equals("")){
                updateModel.setPhone(updateRequest.getPhone());
            }

            updateRepo.save(updateModel);

            return updateModel;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public UpdateModel deleteUpdate(Long id) throws Exception{
        try {
            UpdateModel updateModel = updateRepo.findById(id).orElseThrow(() -> new Exception("No Update Found."));
            updateRepo.delete(updateModel);
            return (updateModel);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
