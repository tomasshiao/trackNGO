package com.example.trackNGO.Model

import net.minidev.json.annotate.JsonIgnore
import org.hibernate.annotations.GenericGenerator

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class OrganizationEvents {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id
    private Date createdDate

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="org_id")
    private Organization organization

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="event_id")
    private Event event

    @JsonIgnore
    Organization getOrganization(){
        this.organization
    }

    @JsonIgnore
    Event getEvent(){
        this.event
    }

    OrganizationEvents(){}

    OrganizationEvents(Organization organization, Event event){
        this.organization = organization
        this.event = event
        this.createdDate = new Date()
    }

    Long getId(){
        this.id
    }

    Date getCreatedDate(){
        this.createdDate
    }

    Map<String, Object> toDTO(){
        [
                "orgEventId": this.getId(),
                "event": this.getEvent().toDTO(),
                "createdDate": this.getCreatedDate(),
                "organization": this.getOrganization().toDTO()
        ] as Map<String, Object>
    }
}
