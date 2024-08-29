package br.edu.iftm.itemvendido.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item_nota_fiscal")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemNotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idNf;
    private Long idItem;
    private Long codProd;
    private Double valorUnit;
    private Integer quantidade;
    private Double desconto;
}
