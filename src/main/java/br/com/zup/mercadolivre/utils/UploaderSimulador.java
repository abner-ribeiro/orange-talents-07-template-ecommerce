package br.com.zup.mercadolivre.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UploaderSimulador {

    public List<String> upload(List<MultipartFile> imagens){
        return imagens.stream().map(imagem -> "https://imagem.simulator.aws/"+imagem.getOriginalFilename()).collect(Collectors.toList());
    }
}
