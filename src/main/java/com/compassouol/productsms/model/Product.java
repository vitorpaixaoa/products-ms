package com.compassouol.productsms.model;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product implements AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message = "Fill all the required fields")
    @NotEmpty(message = "The fields can't be empty")
    @Column(nullable = false)
    private String name;

    @Lob
    @NotEmpty(message = "The fields can't be empty")
    @NotNull(message = "ill all the required fields")
    @Column(nullable = false, columnDefinition = "text")
    private String description;

    @NotNull(message = "The fields can't be empty")
    @Min(value = 0, message = "The price must be greater than zero")
    @Column(nullable = false)
    private Double price;


}
