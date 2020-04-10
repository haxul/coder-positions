package com.search.hh.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.search.hh.entities.PositionEntity;
import com.search.hh.entities.embedded.*;
import com.search.hh.models.City;
import com.search.hh.models.response.headhunter.HeadhunterSearchResponse;
import com.search.hh.models.response.headhunter.HhData;
import com.search.hh.models.response.headhunter.Phone;
import com.search.hh.repositories.PositionRepository;
import kong.unirest.Unirest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    @Value("${headhunter.api.url}")
    private String BASE_URL;

    private final PositionRepository positionRepository;

    @Override
    public HeadhunterSearchResponse findPositions(String searchText, int areaId) throws JsonProcessingException {
        String responseBody = Unirest.get(BASE_URL + "vacancies/?area=" + areaId + "&text=" + searchText)
                .header("Accept", "application/json")
                .asString().getBody();
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(responseBody, HeadhunterSearchResponse.class);
    }

    @Override
    public HeadhunterSearchResponse findPositions(String searchText) throws JsonProcessingException {
        return findPositions(searchText, City.SAMARA.getId());
    }

    @Override
    public int savePosition(City city, String position) throws JsonProcessingException {
        HeadhunterSearchResponse actualPositions = findPositions(position, city.getId());
        if (actualPositions.getItems().isEmpty()) return 0;
        List<PositionEntity> currentSavedPositions = positionRepository.findAll();
        List<PositionEntity> positionsToSave = actualPositions.getItems().stream().
                filter(p -> !isExistInList(p, currentSavedPositions)).
                map(p -> {
                    PositionEntity positionEntity = new PositionEntity();
                    positionEntity.setHeadHunterId(p.getId());
                    positionEntity.setTitle(p.getName());
                    positionEntity.setArea(new AreaEntity(p.getArea().getId(), p.getArea().getName()));
                    SalaryEntity salaryEntity =  (p.getSalary() != null) ?
                            new SalaryEntity(p.getSalary().getFrom(), p.getSalary().getTo(), p.getSalary().getCurrency())
                            : null;
                    positionEntity.setSalary(salaryEntity);
                    positionEntity.setOpen(p.getType().getId().equals("open"));
                    positionEntity.setEmployer(new EmployerEntity(p.getEmployer().getName(), p.getEmployer().getUrl()));
                    positionEntity.setPublished(p.getPublished_at());
                    positionEntity.setRequirement(p.getSnippet().getRequirement());
                    positionEntity.setResponsibility(p.getSnippet().getResponsibility());

                    ContactEntity contact = new ContactEntity();

                    if (p.getContacts() != null) {
                        contact.setContactEmail(p.getContacts().getEmail());
                        contact.setContactName(p.getContacts().getName());
                    }

                    if (p.getContacts() != null && !p.getContacts().getPhones().isEmpty()) {
                        Phone firstPhoneInList = p.getContacts().getPhones().get(0);
                        PhoneEntity phoneEntity = new PhoneEntity();
                        phoneEntity.setCountry(firstPhoneInList.getCountry());
                        phoneEntity.setPhoneCity(firstPhoneInList.getCity());
                        phoneEntity.setPhoneComment(firstPhoneInList.getComment());
                        phoneEntity.setPhoneNumber(firstPhoneInList.getNumber());
                        contact.setContactPhone(phoneEntity);
                    }
                    positionEntity.setContact(contact);
                    return positionEntity;
                }).collect(Collectors.toList());
        positionRepository.saveAll(positionsToSave);
        currentSavedPositions.clear();
        return positionsToSave.size();
    }

    public boolean isExistInList(HhData hhData, List<PositionEntity> currentSavedPositions) {
        for (PositionEntity position : currentSavedPositions) {
            if (position.getId() == hhData.getId()) return true;
        }
        return false;
    }

}
