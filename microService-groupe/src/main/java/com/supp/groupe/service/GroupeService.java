package com.supp.groupe.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.supp.groupe.model.Groupe;
import com.supp.groupe.model.GroupeDetails;
import com.supp.groupe.model.Prof;
import com.supp.groupe.repository.GroupeRepository;

@Service
public class GroupeService {

	
	@Autowired
	private GroupeRepository groupeRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	private final String URLPROF="http://localhost:8888/SERVICE-PROF";
	
	public List<GroupeDetails> findAll(){
		
		List<Groupe> groupes=groupeRepository.findAll();
		ResponseEntity<Prof[]> response=restTemplate.getForEntity(this.URLPROF+"/api/Employees/", Prof[].class);
		Prof[]profs=response.getBody();
		
		return groupes.stream().map((Groupe groupe)->
		  mapToGroupResponse(groupe,profs)
                ).toList();
				
		
		}

		public GroupeDetails findGroupByID(Long id) throws Exception{
			Groupe groupe=groupeRepository.findById(id).orElseThrow(()->new Exception("Group Invalid"));
			Prof prof =restTemplate.getForObject(this.URLPROF+"/api/Employees/"+groupe.getId_prof(),Prof.class);
			return GroupeDetails.builder()
					.id(groupe.getId())
					.name(groupe.getName())
					.count(groupe.getCount())
					.prof(prof)
					.build();

		}
	private GroupeDetails mapToGroupResponse(Groupe group,Prof[] profs){


		Prof foundProf=(Prof) Arrays.stream(profs)
						.filter(prof->prof.getId().equals(group.getId_prof())).findFirst()
						.orElse(null);

		return GroupeDetails.builder().id(group.getId())
				.name(group.getName())
				.count(group.getCount())
				.prof(foundProf)
				.build();
		
	}
	
}