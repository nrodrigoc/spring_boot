package io.github.nrodrigoc.domain.repository;


import io.github.nrodrigoc.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clientes {

    private static String INSERT = "insert into cliente (nome) values (?)";
    private static String SELECT_ALL = "select * from cliente";
    private static String UPDATE = "update cliente set nome = ? where id = ?";
    private static String DELETE = "delete from cliente where id = ?";
    private static String SELECT_POR_NOME = "select * from cliente where nome like ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente salvar(Cliente cliente) {
        //O array de objetos no parâmetro é necessário para instanciar a Entidade.
        jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()}); // Salva um cliente na base de dados
        return cliente;
    }

    public Cliente atualizar(Cliente cliente) {
        jdbcTemplate.update(UPDATE, new Object[]{cliente.getNome(), cliente.getId()});
        return cliente;
    }

    public void deletar(Cliente cliente) {
        deletar(cliente.getId());
    }

    public void deletar(Integer clienteID) {
        jdbcTemplate.update(DELETE, new Object[]{clienteID});
    }

    public List<Cliente> buscaPorNome(String nome) {
        return jdbcTemplate.query(
                SELECT_POR_NOME,
                new Object[]{"%" + nome + "%"},
                getClienteMapper()
        );
    }

    public List<Cliente> obterTodos() {
        // o RowMapper mapeia o resultado do BD pra uma classe (para a classe Cliente, no caso)
        return jdbcTemplate.query(SELECT_ALL, getClienteMapper());
    }


    private RowMapper<Cliente> getClienteMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
                // O rs.getString() retorna a string da coluna *id* da entidade
                Integer id = rs.getInt("id");
                // O rs.getString() retorna a string da coluna *nome* da entidade
                String nome = rs.getString("nome");

                return new Cliente(id, nome);
            }
        };
    }

}
