package com.hikru.assessment.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "position")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "title can not be null")
    @Size(max = 100, message = "title must be less than 100 characters long.")
    private String title;

    @NotNull(message = "description can not be null")
    @Size(max = 1000, message = "description must be less than 1000 characters long.")
    private String description;

    @NotNull(message = "location can not be null")
    private String location;

    @NotNull(message = "status can not be null")
    @Pattern(regexp = "^(draft|open|closed|archived)$", message = "status must be 'open', 'draft', 'closed' or 'archived'")
    private String status;

    @NotNull(message = "budget can not be null")
    private Double budget;

    @Column(name = "closing_date")
    private Date closingDate;

    @ManyToOne
    @JoinColumn(name = "recruiter_id")
    private Recruiter recruiter;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", status='" + status + '\'' +
                ", budget=" + budget +
                ", closingDate=" + closingDate +
                '}';
    }
}
