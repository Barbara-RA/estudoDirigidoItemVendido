package br.edu.iftm.itemvendido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.itemvendido.model.ItemNotaFiscal;
import br.edu.iftm.itemvendido.service.ItemNotaFiscalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/itens")
@Tag(name = "ItemNotaFiscal", description = "API consulta itens de notas fiscais")
public class ItemNotaFiscalController {
    @Autowired
    private ItemNotaFiscalService service;

    @GetMapping("/itens/desconto-zero")
    @Operation(summary = "Retorna todos os itens sem desconto", description = "Retorna todos os itens de nota fiscal que não possuem desconto", tags = {
            "ItemNotaFiscal" }, responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Itens retornados com sucesso"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Nenhum item encontrado")
            })
    public List<ItemNotaFiscal> getItensSemDesconto() {
        return service.findByDesconto(0.0);
    }

    @GetMapping("/itens/com-desconto")
    @Operation(summary = "Retorna todos os itens com desconto", description = "Retorna todos os itens de nota fiscal que possuem desconto", tags = {
            "ItemNotaFiscal" }, responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Itens retornados com sucesso"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Nenhum item encontrado")
            })
    public List<ItemNotaFiscal> getItensComDesconto() {
        return service.findByDescontoGreaterThan(0.0);
    }

    @GetMapping("/itens/ordenados")
    @Operation(summary = "Retorna todos os itens ordenados por valor unitário", description = "Retorna todos os itens de nota fiscal ordenados por valor unitário", tags = {
            "ItemNotaFiscal" }, responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Itens retornados com sucesso"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Nenhum item encontrado")
            })
    public List<ItemNotaFiscal> getItensOrdenados() {
        return service.findAllByOrderByValorUnitDesc();
    }

    @GetMapping("/itens/mais-vendido")
    @Operation(summary = "Retorna o produto mais vendido", description = "Retorna o código do produto mais vendido", tags = {
            "ItemNotaFiscal" }, responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Produto retornado com sucesso"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Nenhum produto encontrado")
            })
    public List<ItemNotaFiscal> getProdutoMaisVendido() {
        return service.findTopByOrderByQuantidadeDesc();
    }

    @GetMapping("/nfs/mais-dez")
    @Operation(summary = "Retorna as notas fiscais com mais de 10 unidades", description = "Retorna os códigos das notas fiscais que possuem mais de 10 unidades", tags = {
            "ItemNotaFiscal" }, responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Notas fiscais retornadas com sucesso"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Nenhuma nota fiscal encontrada")
            })
    public List<ItemNotaFiscal> getNfsMaisDezUnidades() {
        return service.findByQuantidadeGreaterThanEqual(10);
    }

    @GetMapping("/nfs/valor-maior")
    @Operation(summary = "Retorna as notas fiscais com valor total maior que 500", description = "Retorna os códigos das notas fiscais que possuem valor total maior que 500", tags = {
            "ItemNotaFiscal" }, responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Notas fiscais retornadas com sucesso"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Nenhuma nota fiscal encontrada")
            })
    public List<Object[]> getNfsValorMaiorQue(@RequestParam Double valor) {
        return service.findNfWithTotalValueGreaterThan(valor);
    }
}
