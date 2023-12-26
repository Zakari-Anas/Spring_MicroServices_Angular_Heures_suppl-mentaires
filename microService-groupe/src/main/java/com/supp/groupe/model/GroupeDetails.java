package com.supp.groupe.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class GroupeDetails {
	
	   	private Long id;
	    private String name;
	    private String count;
	    private Prof prof;
	    
}
