package br.edu.iftm.itemvendido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.iftm.itemvendido.model.ItemNotaFiscal;

import java.util.List;

@Repository
public interface ItemNotaFiscalRepository extends JpaRepository<ItemNotaFiscal, Long> {

    // A - Retorna todos os itens que foram vendidos sem desconto.
    List<ItemNotaFiscal> findByDesconto(Double desconto);

    // B - Retorna todos os itens que foram vendidos com desconto.
    List<ItemNotaFiscal> findByDescontoGreaterThan(Double desconto);

    // C - Retorna todos os itens e ordene o resultado do maior valor para o menor.
    List<ItemNotaFiscal> findAllByOrderByValorUnitDesc();

    // D - Retorna o produto que mais vendeu.
    List<ItemNotaFiscal> findTopByOrderByQuantidadeDesc();

    // E - Consulte as NF que foram vendidas mais de 10 unidades de pelo menos um
    // produto.
    List<ItemNotaFiscal> findByQuantidadeGreaterThanEqual(Integer quantidade);

    // F - Pesquise o valor total das NF, onde esse valor seja maior que 500, e
    // ordene o resultado do maior valor para o menor.
    @Query("SELECT i.idNf, ROUND(SUM(i.valorUnit * i.quantidade * (1 - i.desconto / 100.0)), 2) AS total " +
            "FROM ItemNotaFiscal i " +
            "GROUP BY i.idNf " +
            "HAVING ROUND(SUM(i.valorUnit * i.quantidade * (1 - i.desconto / 100.0)), 2) > :valor " +
            "ORDER BY total DESC")
    List<Object[]> findNfWithTotalValueGreaterThan(@Param("valor") Double valor);

}
