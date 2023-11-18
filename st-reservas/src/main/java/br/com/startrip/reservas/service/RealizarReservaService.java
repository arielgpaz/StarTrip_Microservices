package br.com.startrip.reservas.service;

import br.com.startrip.reservas.domain.*;
import br.com.startrip.reservas.domain.request.CadastrarReservaRequest;
import br.com.startrip.reservas.domain.response.DadosAnuncioResponse;
import br.com.startrip.reservas.domain.response.DadosSolicitanteResponse;
import br.com.startrip.reservas.domain.response.InformacaoReservaResponse;
import br.com.startrip.reservas.exceptions.*;
import br.com.startrip.reservas.feign.ApiStAnuncios;
import br.com.startrip.reservas.feign.ApiStUsuarios;
import br.com.startrip.reservas.repository.ReservaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
public class RealizarReservaService {

    private final ApiStUsuarios apiStUsuarios;

    private final ApiStAnuncios apiStAnuncios;

    private final ReservaRepository reservaRepository;

    public InformacaoReservaResponse realizarUmaReserva(CadastrarReservaRequest cadastrarReservaRequest) {
        Usuario solicitante = this.buscarSolicitante(cadastrarReservaRequest.getCpfSolicitante());
        Anuncio anuncio = this.buscarAnuncio(cadastrarReservaRequest);

        LocalDate dataInicial = cadastrarReservaRequest.getPeriodo().getDataHoraInicial().toLocalDate();
        LocalDate dataFinal = cadastrarReservaRequest.getPeriodo().getDataHoraFinal().toLocalDate();

        this.verificarDisponibilidadeDoEstabelecimento(anuncio.getId(), dataInicial, dataFinal);

        LocalDateTime dataHoraInicioReserva = LocalDateTime.of(dataInicial, LocalTime.parse("14:00"));
        LocalDateTime dataHoraFimReserva = LocalDateTime.of(dataFinal, LocalTime.parse("12:00"));

        Periodo periodo = Periodo.builder()
                .dataHoraInicial(dataHoraInicioReserva)
                .dataHoraFinal(dataHoraFimReserva)
                .build();

        long quantidadeDiarias = this.calcularQuantidadeDiarias(dataInicial, dataFinal);
        Pagamento pagamento = this.definirPagamento(anuncio.getValorDiaria(), quantidadeDiarias);

        this.avaliarCondicoesMinimasDosEstabelecimentos(cadastrarReservaRequest.getQuantidadePessoas(),
                anuncio, quantidadeDiarias);

        Reserva reserva = this.salvarReserva(cadastrarReservaRequest.getQuantidadePessoas(), solicitante,
                anuncio, periodo, pagamento);

        return montarRetorno(reserva);
    }

    private Usuario buscarSolicitante(String cpfSolicitante) {
        return apiStUsuarios.buscarUsuarioPorCpf(cpfSolicitante)
                .orElseThrow(() -> new CpfUsuarioInexistenteException(cpfSolicitante));
    }

    private Anuncio buscarAnuncio(CadastrarReservaRequest cadastrarReservaRequest) {

        Anuncio anuncio = apiStAnuncios.buscarAnuncioPorId(cadastrarReservaRequest.getIdAnuncio())
                .orElseThrow(() -> new IdAnuncioNaoEncontradoException(cadastrarReservaRequest.getIdAnuncio()));

        if (anuncio.getAnunciante() != null
                && cadastrarReservaRequest.getCpfSolicitante().equals(anuncio.getAnunciante().getCpf())) {
            throw new SolicitanteMesmoAnuncianteException();
        }

        return anuncio;
    }

    private void verificarDisponibilidadeDoEstabelecimento(String idAnuncio, LocalDate dataInicial, LocalDate dataFinal) {

        List<Reserva> reservasExistentes = reservaRepository.findByAnuncioId(idAnuncio);

        for (Reserva reserva : reservasExistentes) {

            LocalDate dataInicioReservaExistente = reserva.getPeriodo().getDataHoraInicial().toLocalDate();
            LocalDate dataFinalReservaExistente = reserva.getPeriodo().getDataHoraFinal().toLocalDate();

            if ((dataInicial.isBefore(dataFinalReservaExistente) && dataFinal.isAfter(dataInicioReservaExistente))
                    && (reserva.getPagamento().getStatus().equals(StatusPagamento.PAGO) ||
                    reserva.getPagamento().getStatus().equals(StatusPagamento.PENDENTE))) {
                throw new AnuncioJaReservadoException();
            }
        }
    }

    private long calcularQuantidadeDiarias(LocalDate dataInicial, LocalDate dataFinal) {

        if (dataInicial.isAfter(dataFinal)) {
            throw new DataFinalMenorException();
        }

        long quantidadeDiarias = ChronoUnit.DAYS.between(dataInicial, dataFinal);

        if (quantidadeDiarias < 1) {
            throw new NumeroMinimoDiariasException();
        }

        return quantidadeDiarias;
    }

    private Pagamento definirPagamento(BigDecimal valorDiaria, long quantidadeDiarias) {
        return Pagamento.builder()
                .valorTotal(valorDiaria.multiply(BigDecimal.valueOf(quantidadeDiarias)))
                .status(StatusPagamento.PENDENTE)
                .build();
    }

    private void avaliarCondicoesMinimasDosEstabelecimentos(Integer quantidadePessoas,
                                                            Anuncio anuncio, long quantidadeDiarias) {

        if (anuncio.getImovel() != null
                && anuncio.getImovel().getTipoImovel() != null
                && TipoImovel.HOTEL.equals(anuncio.getImovel().getTipoImovel())
                && quantidadePessoas < 2) {
            throw new MinimoDuasPessoasParaHotelException();
        }

        if (anuncio.getImovel() != null
                && anuncio.getImovel().getTipoImovel() != null
                && TipoImovel.POUSADA.equals(anuncio.getImovel().getTipoImovel())
                && quantidadeDiarias < 5) {
            throw new MinimoDeDiariasParaPousadaException();
        }
    }

    private Reserva salvarReserva(Integer quantidadePessoas, Usuario solicitante,
                                  Anuncio anuncio, Periodo periodo, Pagamento pagamento) {
        return reservaRepository.save(Reserva.builder()
                .solicitante(solicitante)
                .anuncio(anuncio)
                .periodo(periodo)
                .quantidadePessoas(quantidadePessoas)
                .dataHoraReserva(LocalDateTime.now())
                .pagamento(pagamento)
                .build());
    }

    private static InformacaoReservaResponse montarRetorno(Reserva reserva) {
        DadosSolicitanteResponse dadosSolicitanteResponse = DadosSolicitanteResponse.builder()
                .id(reserva.getSolicitante().getId())
                .nome(reserva.getSolicitante().getNome())
                .build();

        DadosAnuncioResponse dadosAnuncioResponse = DadosAnuncioResponse.builder()
                .id(reserva.getAnuncio().getId())
                .imovel(reserva.getAnuncio().getImovel())
                .anunciante(reserva.getAnuncio().getAnunciante())
                .formasAceitas(reserva.getAnuncio().getFormasAceitas())
                .descricao(reserva.getAnuncio().getDescricao())
                .build();

        return InformacaoReservaResponse.builder()
                .idReserva(reserva.getId())
                .solicitante(dadosSolicitanteResponse)
                .quantidadePessoas(reserva.getQuantidadePessoas())
                .anuncio(dadosAnuncioResponse)
                .periodo(reserva.getPeriodo())
                .pagamento(reserva.getPagamento())
                .build();
    }
}
