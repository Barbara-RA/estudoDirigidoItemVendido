package br.edu.iftm.itemvendido.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iftm.itemvendido.model.ItemNotaFiscal;
import br.edu.iftm.itemvendido.repository.ItemNotaFiscalRepository;

import java.util.List;

@Service
public class ItemNotaFiscalService {

    @Autowired
    private ItemNotaFiscalRepository repository;

    public List<ItemNotaFiscal> findAll() {
        return repository.findAll();
    }

    public List<ItemNotaFiscal> findByDesconto(Double desconto) {
        return repository.findByDesconto(desconto);
    }

    public List<ItemNotaFiscal> findByDescontoGreaterThan(Double desconto) {
        return repository.findByDescontoGreaterThan(desconto);
    }

    public List<ItemNotaFiscal> findAllByOrderByValorUnitDesc() {
        return repository.findAllByOrderByValorUnitDesc();
    }

    public List<ItemNotaFiscal> findTopByOrderByQuantidadeDesc() {
        return repository.findTopByOrderByQuantidadeDesc();
    }

    public List<ItemNotaFiscal> findByQuantidadeGreaterThanEqual(Integer quantidade) {
        return repository.findByQuantidadeGreaterThanEqual(quantidade);
    }

    public List<Object[]> findNfWithTotalValueGreaterThan(Double valor) {
        return repository.findNfWithTotalValueGreaterThan(valor);
    }
}

