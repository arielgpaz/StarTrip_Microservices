package br.com.startrip.anuncios.config.listener;


import br.com.startrip.anuncios.domain.Anuncio;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

public class AnuncioListener extends AbstractMongoEventListener<Anuncio> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Anuncio> event) {
        Anuncio anuncio = event.getSource();
        anuncio.setDeleted(false);
    }
}
