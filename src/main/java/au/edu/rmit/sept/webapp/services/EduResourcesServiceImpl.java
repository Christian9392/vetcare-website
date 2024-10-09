package au.edu.rmit.sept.webapp.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.edu.rmit.sept.webapp.models.EduResources;
import au.edu.rmit.sept.webapp.repositories.EduResourcesRepository;

import java.util.List;

@Service
public class EduResourcesServiceImpl implements EduResourcesService{

    private final EduResourcesRepository repo;
    
    @Autowired
    public EduResourcesServiceImpl(EduResourcesRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<EduResources> findAllVideos() {
        List<EduResources> videos = repo.findAllVideos("Video");
        return (videos);
    }



}
