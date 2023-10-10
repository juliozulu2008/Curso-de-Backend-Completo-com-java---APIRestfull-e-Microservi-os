package com.juliocdias.primeiroexemplo.repository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository; //2 dai o automatico importa o repository
import com.juliocdias.primeiroexemplo.model.Produto;

//3 dai o spring vai ter controle sobre o repository e com isso ele vai poder fazer a 
//injeção de dependencia e inversão de controle

@Repository // 1 esta é a 1 coisa ase fazer no repository
public class ProdutoRepository {
    // neste caso vou simular um banco de dados
    private List<Produto> produtos = new ArrayList<Produto>();
    private Integer ultimoId = 0;

    /**
     * Metodo para Retornar uma lista de produtos
     * 
     * @return
     */
    public List<Produto> obterTodos() {
        return produtos;
    }

    /**
     * Metodo que retono um produto especifico usando o id
     * 
     * @param id identificador do produto que sera localizado
     * @return Retorna um produto especifico caso ele seja encontrado
     */
    public Optional<Produto> obterPorId(Integer id) {
        return produtos.stream().filter(produto -> produto.getId() == id)
                .findFirst();
    }

    /**
     * Metodo que adiciona um produto na lista de produtos
     * 
     * @param produto que sera adicionado
     * @return retorna o produto que foi adicionado na lista
     */
    public Produto adicionar(Produto produto) {
        ultimoId++;
        produto.setId(ultimoId);
        produtos.add(produto);
        return produto;
    }

    /**
     * Metodo que atualiza um produto na lista de produtos
     * 
     * @param produto que sera atualizado
     * @return retorna o produto que foi atualizado na lista
     */
    public Produto atualizar(Produto produto) {
        Optional<Produto> produtoEncontrado = obterPorId(produto.getId());
        if (produtoEncontrado.isEmpty()) {
            throw new InputMismatchException("Produto nao encontrado");
        }
        deletar(produto.getId());
        produtos.add(produto);
        return produto;
    }

    /**
     * Metodo que remove um produto na lista de produtos
     * 
     * @param id id do produto que sera removido
     */
    public void deletar(Integer id) {
        produtos.removeIf(filter -> filter.getId() == id);
    }
}
