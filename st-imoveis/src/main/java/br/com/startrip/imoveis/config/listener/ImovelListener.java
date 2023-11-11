package br.com.startrip.imoveis.config.listener;

import br.com.startrip.imoveis.domain.Imovel;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

public class ImovelListener extends AbstractMongoEventListener<Imovel> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Imovel> event) {
        Imovel imovel = event.getSource();
        imovel.setDeleted(false);
    }
}
