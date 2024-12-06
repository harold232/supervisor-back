package com.example.Conectar.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.codec.multipart.FilePart;
@Getter
@RequiredArgsConstructor
@Setter
@Data
public class CargarArchivoDTO {
    private FilePart archivo;
}
