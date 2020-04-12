package com.search.hh.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.search.hh.dto.PositionDto;
import com.search.hh.entities.PositionEntity;
import com.search.hh.entities.SkillEntity;
import com.search.hh.entities.embedded.*;
import com.search.hh.exceptions.PositionNotFound;
import com.search.hh.models.City;
import com.search.hh.models.response.headhunter.*;
import com.search.hh.repositories.PositionRepository;
import com.search.hh.repositories.SkillRepository;
import kong.unirest.Unirest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    @Value("${headhunter.api.url}")
    private String BASE_URL;

    private final SkillRepository skillRepository;
    private final PositionRepository positionRepository;
    private final ModelMapper modelMapper;

    @Override
    public HeadhunterSearchResponse findPositionsInHH(String searchText, int areaId, int page) throws JsonProcessingException {
        StringBuilder requestUrl = new StringBuilder(BASE_URL);
        requestUrl.append("vacancies/?area=").
                append(areaId).
                append("&text=").
                append(searchText).
                append("&per_page=100").
                append("&page=").
                append(page);

        String responseBody = Unirest.get(requestUrl.toString())
                .header("Accept", "application/json")
                .asString().getBody();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(responseBody, HeadhunterSearchResponse.class);
    }


    @Override
    public HeadhunterSearchResponse findPositionsInHH(String searchText, int page) throws JsonProcessingException {
        return findPositionsInHH(searchText, City.SAMARA.getId(), page);
    }

    @Override
    public int savePositions(City city, String searchedPosition, int page) throws JsonProcessingException {
        HeadhunterSearchResponse headHunterResponse = findPositionsInHH(searchedPosition, city.getId(), page);
        if (headHunterResponse.getItems().isEmpty()) return 0;
        List<PositionEntity> currentSavedPositions = positionRepository.findAll();
        int amountNewPositionsInHH = (int) headHunterResponse.getItems().stream().
                filter(headHunterPosition -> !isExistInList(headHunterPosition, currentSavedPositions)).
                map(headHunterPosition -> {

                    PositionEntity positionEntity = new PositionEntity();
                    positionEntity.setHeadHunterId(headHunterPosition.getId());
                    positionEntity.setTitle(headHunterPosition.getName());
                    positionEntity.setArea(new AreaEntity(headHunterPosition.getArea().getId(), headHunterPosition.getArea().getName()));
                    SalaryEntity salaryEntity = (headHunterPosition.getSalary() != null) ?
                            new SalaryEntity(headHunterPosition.getSalary().getFrom(), headHunterPosition.getSalary().getTo(), headHunterPosition.getSalary().getCurrency())
                            : null;
                    positionEntity.setSalary(salaryEntity);
                    positionEntity.setOpen(headHunterPosition.getType().getId().equals("open"));
                    positionEntity.setEmployer(new EmployerEntity(headHunterPosition.getEmployer().getName(), headHunterPosition.getEmployer().getUrl()));
                    positionEntity.setPublished(headHunterPosition.getPublished_at());

                    ContactEntity contact = new ContactEntity();

                    if (headHunterPosition.getContacts() != null) {
                        contact.setContactEmail(headHunterPosition.getContacts().getEmail());
                        contact.setContactName(headHunterPosition.getContacts().getName());
                    }

                    if (headHunterPosition.getContacts() != null && !headHunterPosition.getContacts().getPhones().isEmpty()) {
                        Phone firstPhoneInList = headHunterPosition.getContacts().getPhones().get(0);
                        PhoneEntity phoneEntity = new PhoneEntity();
                        phoneEntity.setCountry(firstPhoneInList.getCountry());
                        phoneEntity.setPhoneCity(firstPhoneInList.getCity());
                        phoneEntity.setPhoneComment(firstPhoneInList.getComment());
                        phoneEntity.setPhoneNumber(firstPhoneInList.getNumber());
                        contact.setContactPhone(phoneEntity);
                    }
                    positionEntity.setContact(contact);
                    positionRepository.save(positionEntity);
                    return new WeakReference<>(positionEntity);
                }).count();
        currentSavedPositions.clear();
        if (headHunterResponse.getPages() == (page + 1)) return amountNewPositionsInHH;
        return amountNewPositionsInHH + savePositions(city, searchedPosition, page + 1);
    }

    @Override
    public PositionDto findPositionById(int id) throws JsonProcessingException {
        PositionEntity position = positionRepository.findById(id).orElseThrow(PositionNotFound::new);
        if (position.getDescription() != null) return modelMapper.map(position, PositionDto.class);
        HeadHunterPositionByIdResponse response = findPositionByIdInHH(position.getHeadHunterId());
        position.setDescription(response.getDescription());
        position.setExperience(response.getExperience().getName());

        List<SkillEntity> skillEntities = new ArrayList<>();

        response.getKeySkills().forEach(element -> {
            SkillEntity skill = skillRepository.findSkillEntitiesByName(element.getName());
            if (skill == null) skillRepository.save(new SkillEntity(element.getName()));
            skillEntities.add(skill);
        });

        position.setSkills(skillEntities);
        positionRepository.save(position);
        return modelMapper.map(position, PositionDto.class);
    }

    private HeadHunterPositionByIdResponse findPositionByIdInHH(int headHunterId) throws JsonProcessingException {
        StringBuilder requestUrl = new StringBuilder(BASE_URL);
        requestUrl.append("vacancies/").append(headHunterId);
        String responseBody = Unirest.get(requestUrl.toString())
                .header("Accept", "application/json")
                .asString().getBody();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(responseBody, HeadHunterPositionByIdResponse.class);
    }

    private boolean isExistInList(HhData hhData, List<PositionEntity> currentSavedPositions) {
        for (PositionEntity position : currentSavedPositions) {
            if (position.getHeadHunterId() == hhData.getId()) return true;
        }
        return false;
    }
}
