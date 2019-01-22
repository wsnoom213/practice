package com.se.team00.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import lombok.*;

import javax.persistence.SequenceGenerator;
import java.lang.annotation.Annotation;
import javax.validation.constraints.*;

@Data
@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Student {
    @Id
    @NotNull @Pattern(regexp="[BMD]\\d{7}")
    private String studentId;
    @NotNull
    private  String firstName;
    @NotNull
    private  String lastName;


} 