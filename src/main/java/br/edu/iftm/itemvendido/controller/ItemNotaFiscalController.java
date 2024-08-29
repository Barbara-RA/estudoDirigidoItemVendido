package br.edu.iftm.itemvendido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.itemvendido.model.ItemNotaFiscal;
import br.edu.iftm.itemvendido.service.ItemNotaFiscalService;


@RestController
public class ItemNotaFiscalController {
    @Autowired
    private ItemNotaFiscalService service;

    @GetMapping("/itens/desconto-zero")
    public List<ItemNotaFiscal> getItensSemDesconto() {
        return service.findByDesconto(0.0);
    }

    @GetMapping("/itens/com-desconto")
    public List<ItemNotaFiscal> getItensComDesconto() {
        return service.findByDescontoGreaterThan(0.0);
    }

    @GetMapping("/itens/ordenados")
    public List<ItemNotaFiscal> getItensOrdenados() {
        return service.findAllByOrderByValorUnitDesc();
    }

    @GetMapping("/itens/mais-vendido")
    public List<ItemNotaFiscal> getProdutoMaisVendido() {
        return service.findTopByOrderByQuantidadeDesc();
    }

    @GetMapping("/nfs/mais-dez")
    public List<ItemNotaFiscal> getNfsMaisDezUnidades() {
        return service.findByQuantidadeGreaterThanEqual(10);
    }

    @GetMapping("/nfs/valor-maior")
    public List<Object[]> getNfsValorMaiorQue(@RequestParam Double valor) {
        return service.findNfWithTotalValueGreaterThan(valor);
    }
}
