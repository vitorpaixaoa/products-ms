package com.compassouol.productsms.exception;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BadRequestDetails {
    private String message;
    private int status;
}
