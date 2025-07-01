package br.mil.mar.saudenaval.senpe.repositories;

import br.mil.mar.saudenaval.senpe.entities.Solicitacoes;
import br.mil.mar.saudenaval.senpe.enums.Status;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface SolicitacoesRepository extends JpaRepository<Solicitacoes,String>, JpaSpecificationExecutor<Solicitacoes> {

    default List<Solicitacoes> findAllByUserId(String userId){
        Specification<Solicitacoes> specs = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userId"),userId);
        return findAll(specs);
    }

    default List<Solicitacoes> findAllByTypeOrStatus(String tipo, Status status){
        Specification<Solicitacoes> specs = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

        if(!tipo.isBlank()){
            Specification<Solicitacoes> tipoEqual = (root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.lower(root.get("tipo")),tipo.toLowerCase());
            specs = specs.and(tipoEqual);
        }


        if(!status.name().equals("QUALQUER")){
            Specification<Solicitacoes> statusEqual = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("situacao"),status.name());
            specs = specs.and(statusEqual);
        }

        return findAll(specs);
    }

    default List<Solicitacoes> findByProtocol(String protocolo){
        Specification<Solicitacoes> specs = (root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.lower(root.get("protocolo")),protocolo.toLowerCase());
        return findAll(specs);
    }

}
