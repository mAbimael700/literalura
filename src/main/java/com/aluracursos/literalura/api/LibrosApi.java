package com.aluracursos.literalura.api;

import com.aluracursos.literalura.dto.SearchResponseDTO;
import com.aluracursos.literalura.services.ApiClient;
import com.aluracursos.literalura.utils.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class LibrosApi {

    @Autowired
    private ApiClient apiClient;

    @Autowired
    private ConvierteDatos convierteDatos;

    public SearchResponseDTO searchBook(String title) {
        var request = apiClient.requestToApiClient("?search=" +
                title.replaceAll(" ", "%20"));

        if (request == null) {
            System.out.println("Error: la respuesta del API es nula.");
            return null;
        }

        return convierteDatos
                .converToSearchResponseEntity(request);

    }


}
